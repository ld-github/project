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
    public User query(User user) {
        if (null == user || null == user.getUsername() || null == user.getPassword()) {
            return null;
        }
        String hql = "from User u where u.username = ? and u.password = ?";
        return super.getUniqueResult(hql, user.getUsername(), user.getPassword());
    }
}
