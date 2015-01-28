package com.ld.web.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.ld.web.bean.Page;

/**
 * 
 * <p>Title: BaseDao</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-8
 */
public interface BaseDao<T> {
    /**
     * Save T t
     * 
     * @param t
     * @return
     */
    boolean save(T t);

    /**
     * Get List
     * 
     * @param hql
     * @param order
     * @param params
     * @return
     */
    List<T> getList(String hql, Object... params);

    /**
     * Get unique result
     * 
     * @param hql
     * @param params
     * @return
     */
    T getUniqueResult(String hql, Object... params);

    /**
     * Get unique result
     * 
     * @param primaryKey
     * @return
     */
    T getUniqueResult(Long primaryKey);

    /**
     * Update T t
     * 
     * @param t
     * @return
     */
    void update(T t);

    /**
     * Delete T by primaryKey
     * 
     * @param key
     */
    void delete(Long primaryKey);

    /**
     * Delete T by t
     * 
     * @param t
     */
    void delete(T t);

    /**
     * Get Page by where and params and orders
     * 
     * @param where
     * @param params
     * @param orders
     * @param page
     * @return
     */
    Page<T> getPage(String where, List<?> params, LinkedHashMap<String, String> orders, Page<T> page);

    /**
     * Get Total by where and params
     * 
     * @param where
     * @param params
     * @return
     */
    long getTotal(String where, List<?> params);
}
