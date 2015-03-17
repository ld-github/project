package com.ld.web.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ld.web.bean.Page;

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
public interface BaseDao<T> {
    /**
     * Save T t
     * 
     * @param t
     * 
     */
    void save(T t);

    /**
     * Get List
     * 
     * @param where
     * @param params
     * @return
     */
    List<T> getList(String where, Map<String, Object> params);

    /**
     * Get Page by where and params and orders
     * 
     * @param where
     * @param params
     * @param orders
     * @return
     */
    List<T> getList(String where, Map<String, Object> params, LinkedHashMap<String, String> orders);

    /**
     * Get unique result
     * 
     * @param where
     * @param params
     * @return
     */
    T getUniqueResult(String where, Map<String, Object> params);

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
     * 
     * @param hql
     * @param params
     * @return
     */
    int update(String hql, Map<String, Object> params);

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
    Page<T> getPage(String where, Map<String, Object> params, LinkedHashMap<String, String> orders, Page<T> page);

    /**
     * Get Total by where and params
     * 
     * @param where
     * @param params
     * @return
     */
    long getTotal(String where, Map<String, Object> params);
}
