package com.ld.web.dao;

import com.ld.web.bean.User;

/**
 * 
 * <p>Title: UserBiz</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-8
 */
public interface UserDao extends BaseDao<User> {
    /**
     * Query user by username and password
     * 
     * @param user
     * @return
     */
    User query(String username, String password) throws Exception;
}
