package com.ld.web.dao;

import org.springframework.stereotype.Repository;

import com.ld.web.bean.User;

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
