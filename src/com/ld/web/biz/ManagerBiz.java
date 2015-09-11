package com.ld.web.biz;

import com.ld.web.bean.Page;
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
     * Save manager
     * 
     * @param manager
     * 
     */
    void saveUser(Manager manager);

    /**
     * Get manager count
     * 
     * @return
     */
    long getUserCount();

    /**
     * Manager login
     * 
     * @param username
     * @param password
     * @return
     */
    Manager login(String username, String password);

    /**
     * Get page manager
     * 
     * @param exceptMid
     * @param username
     * @param available
     * @param page
     * @return
     */
    Page<Manager> getPage(Long exceptMid, String username, Boolean available, Page<Manager> page);

    /**
     * Change manager available
     * 
     * @param manager
     * @return
     */
    Manager changeAvailable(Manager manager);
}
