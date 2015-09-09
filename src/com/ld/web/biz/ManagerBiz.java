package com.ld.web.biz;

import com.ld.web.bean.model.Manager;

/**
 * 
 * <p>Title: ManagerBiz</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
public interface ManagerBiz {
    /**
     * Save user
     * 
     * @param u
     * 
     */
    void saveUser(Manager manager);

    /**
     * Get user count
     * 
     * @return
     */
    long getUserCount();

    /**
     * User login
     * 
     * @return
     */
    Manager login(String username, String password);
}
