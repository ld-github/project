package com.ld.web.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ld.web.bean.User;
import com.ld.web.dao.UserDao;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User login(User user) {
        String hql = "from User u where u.username = ? and u.password = ?";
        List<User> users = super.getList(hql, user.getUsername(), user.getPassword());
        return users.size() > 0 ? users.get(0) : null;
    }
}
