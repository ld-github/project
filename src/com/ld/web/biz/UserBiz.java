package com.ld.web.biz;

import com.ld.web.bean.model.User;

/**
 * 
 * <p>Title: UserBiz</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
public interface UserBiz {
    /**
     * Save user
     * 
     * @param u
     * 
     */
    void saveUser(User user);

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
    User login(String username, String password);
}
