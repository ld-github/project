package com.ld.web.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ld.web.bean.Page;
import com.ld.web.dao.BaseDao;

/**
 * 
 * <p>Title: BaseDaoImpl</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 * 
 * @param <T>
 *
 * @date 2015-1-8
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

    @Resource
    private SessionFactory sf;

    protected Session getCurrentSession() {
        return sf.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    private Class<T> getClazz() {
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String getClassName() {
        return this.getClazz().getSimpleName();
    }

    @Override
    public boolean save(T t) {
        try {
            this.getCurrentSession().save(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getList(String hql, Object... params) {
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        return q.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getUniqueResult(String hql, Object... params) {
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        return (T) q.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getUniqueResult(Long primaryKey) {
        String hql = "from " + this.getClassName() + " o where o.id = ?";
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, primaryKey);
        return (T) q.uniqueResult();
    }

    /**
     * Query setParameter
     * 
     * @param q
     * @param params
     */
    private void setParams(Query q, Object... params) {
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i, params[i]);
        }
    }

    @Override
    public void update(T t) {
        this.getCurrentSession().update(t);
    }

    @Override
    public int update(String hql, Object... params) {
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        return q.executeUpdate();
    }

    @Override
    public void delete(Long primaryKey) {
        String hql = "delete " + this.getClassName() + " o where o.id = ?";
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, primaryKey);
        q.executeUpdate();
    }

    @Override
    public void delete(T t) {
        this.getCurrentSession().delete(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<T> getPage(String where, List<?> params, LinkedHashMap<String, String> orders, Page<T> page) {
        String hql = "from " + this.getClassName() + " o " + where + this.getOrder(orders);
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params.toArray());
        q.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
        q.setMaxResults(page.getPageSize());
        List<T> records = q.list();
        long total = this.getTotal(where, params);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public long getTotal(String where, List<?> params) {
        String hql = "select count(o) from " + this.getClassName() + " o " + where;
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params.toArray());
        return (Long) q.uniqueResult();
    }

    /**
     * Convert LinkedHashMap to str like 'order by xx asc, bb desc '
     * 
     * @param orders
     * @return
     */
    private String getOrder(LinkedHashMap<String, String> orders) {
        if (null == orders || orders.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer(" order by");
        for (String key : orders.keySet()) {
            sb.append(" ").append(key).append(" ").append(orders.get(key)).append(",");
        }
        return sb.delete(sb.toString().lastIndexOf(","), sb.toString().length()).toString();
    }

}
