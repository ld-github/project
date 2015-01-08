package com.ld.web.dao;

import java.util.List;

public interface BaseDao<T> {
    /**
     * save
     * 
     * @param t
     * @return
     */
    boolean save(T t);

    /**
     * 
     * @param hql
     * @param order
     * @param params
     * @return
     */
    List<T> getList(String hql, Object... params);

    /**
     * get uniqueResult
     * 
     * @param hql
     * @param params
     * @return
     */
    T getUniqueResult(String hql, Object... params);
}
