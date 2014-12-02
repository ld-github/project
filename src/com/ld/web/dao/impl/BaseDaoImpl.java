package com.ld.web.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ld.web.dao.BaseDao;


public class BaseDaoImpl<T> implements BaseDao<T> {

    @Resource
    private SessionFactory sf;

    private Session getSession() {
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

}
