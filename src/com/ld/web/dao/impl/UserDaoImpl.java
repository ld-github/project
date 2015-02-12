package com.ld.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.ld.web.bean.User;
import com.ld.web.dao.UserDao;

/**
 * 
 * <p>Title: UserDaoImpl</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-8
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User query(String username, String password) {
        String where = "where u.username = ? and u.password = ?";
        return super.getUniqueResult(where, username, password);
    }
}
