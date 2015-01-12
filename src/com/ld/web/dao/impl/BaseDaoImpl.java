package com.ld.web.dao.impl;

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

    @Override
    public boolean save(T t) {
        try {
            getSession().save(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getList(String hql, Object... params) {
        Query q = getSession().createQuery(hql);
        setParams(q, params);
        return q.list();
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

    @SuppressWarnings("unchecked")
    @Override
    public T getUniqueResult(String hql, Object... params) {
        Query q = getSession().createQuery(hql);
        setParams(q, params);
        return (T) q.uniqueResult();
    }

}
