package com.ld.web.dao;

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
}
