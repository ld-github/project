package com.ld.web.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ld.web.dao.BaseDao;

/**
 * 
 * <p>Title: BaseDaoImpl</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-8
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

    @Resource
    private SessionFactory sf;

    public Session getSession() {
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
            this.getSession().save(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getList(String hql, Object... params) {
        Query q = this.getSession().createQuery(hql);
        setParams(q, params);
        return q.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getUniqueResult(String hql, Object... params) {
        Query q = this.getSession().createQuery(hql);
        setParams(q, params);
        return (T) q.uniqueResult();
    }

    /**
     * Query set setParameter
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
        this.getSession().update(t);
    }

    @Override
    public void delete(Long primaryKey) {
        String hql = "delete " + getClassName() + " o where o.id = ?";
        Query q = this.getSession().createQuery(hql);
        setParams(q, primaryKey);
        q.executeUpdate();
    }

    @Override
    public void delete(T t) {
        this.getSession().delete(t);
    }

}
