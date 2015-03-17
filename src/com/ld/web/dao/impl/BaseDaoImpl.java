package com.ld.web.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public void save(T t) {
        this.getCurrentSession().save(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getList(String where, Map<String, Object> params) {
        where = where == null ? "" : where;
        String hql = "from " + this.getClassName() + " o " + where;
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        return q.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getList(String where, Map<String, Object> params, LinkedHashMap<String, String> orders) {
        where = where == null ? "" : where;
        String hql = "from " + this.getClassName() + " o " + where + this.getOrder(orders);
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        return q.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getUniqueResult(String where, Map<String, Object> params) {
        where = where == null ? "" : where;
        String hql = "from " + this.getClassName() + " o " + where;
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        return (T) q.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getUniqueResult(final Long primaryKey) {
        String hql = "from " + this.getClassName() + " o where o.id=:id";
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, new HashMap<String, Object>() {
            private static final long serialVersionUID = 8568462066745554547L;
            {
                put("id", primaryKey);
            }
        });
        return (T) q.uniqueResult();
    }

    @Override
    public void update(T t) {
        this.getCurrentSession().update(t);
    }

    @Override
    public int update(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        return q.executeUpdate();
    }

    @Override
    public void delete(final Long primaryKey) {
        String hql = "delete " + this.getClassName() + " o where o.id=:id";
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, new HashMap<String, Object>() {
            private static final long serialVersionUID = -4864915463081917504L;
            {
                put("id", primaryKey);
            }
        });
        q.executeUpdate();
    }

    @Override
    public void delete(T t) {
        this.getCurrentSession().delete(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<T> getPage(String where, Map<String, Object> params, LinkedHashMap<String, String> orders, Page<T> page) {
        where = where == null ? "" : where;
        String hql = "from " + this.getClassName() + " o " + where + this.getOrder(orders);
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        q.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
        q.setMaxResults(page.getPageSize());
        List<T> records = q.list();
        long total = this.getTotal(where, params);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public long getTotal(String where, Map<String, Object> params) {
        where = where == null ? "" : where;
        String hql = "select count(o) from " + this.getClassName() + " o " + where;
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        return (Long) q.uniqueResult();
    }

    /**
     * Convert LinkedHashMap to string like 'order by xx asc, bb desc '
     * 
     * @param orders
     * @return
     */
    private String getOrder(LinkedHashMap<String, String> orders) {
        if (null == orders || orders.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer("order by");
        for (String key : orders.keySet()) {
            sb.append(" ").append(key).append(" ").append(orders.get(key)).append(",");
        }
        return sb.delete(sb.toString().lastIndexOf(","), sb.toString().length()).toString();
    }

    /**
     * Query setParameter
     * 
     * @param q
     * @param params
     */
    private void setParams(Query q, Map<String, Object> params) {
        if (null == params || params.isEmpty()) {
            return;
        }
        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));
        }
    }
}
