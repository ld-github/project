package com.ld.web.dao;

import org.springframework.stereotype.Repository;

import com.ld.web.bean.User;

/**
 * 
 * <p>Title: UserDao</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-8
 */
@Repository
public interface UserDao extends BaseDao<User> {
    /**
     * user login
     * 
     * @param user
     * @return
     */
    User login(User user);
}
