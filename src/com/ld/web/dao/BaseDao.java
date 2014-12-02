package com.ld.web.dao;

public interface BaseDao<T> {
    /**
     * save
     * 
     * @param t
     * @return
     */
    boolean save(T t);
}
