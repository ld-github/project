package com.ld.web.dao;

import com.ld.web.bean.Page;
import com.ld.web.bean.model.Manager;

/**
 * 
 * <p>Title: ManagerDao</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
public interface ManagerDao extends BaseDao<Manager> {
    /**
     * Query manager by username and password
     * 
     * @param user
     * @return
     */
    Manager query(String username, String password) throws Exception;

    /**
     * Get page Manager
     * 
     * @param exceptMid
     * @param username
     * @param available
     * @param page
     * @return
     */
    Page<Manager> getPage(Long exceptMid, String username, Boolean available, Page<Manager> page);

    /**
     * Get total by username
     * 
     * @param username
     * @return
     */
    long getTotal(String username);
}
