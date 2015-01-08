package com.ld.web.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ld.web.dao.BaseDao;

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

    @Override
    public List<T> getList(String hql, Object... params) {
        Query q = getSession().createQuery(hql);
        setParams(q, params);
        return null;
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

}
