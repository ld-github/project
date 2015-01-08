package com.ld.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.ld.web.bean.User;
import com.ld.web.dao.UserDao;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User login(User user) {
        String hql = "from User u where u.username = ? and u.password = ?";
        return super.getUniqueResult(hql, user.getUsername(), user.getPassword());
    }
}
