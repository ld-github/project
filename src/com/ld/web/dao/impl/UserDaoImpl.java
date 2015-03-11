package com.ld.web.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.ld.web.bean.User;
import com.ld.web.dao.UserDao;

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
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User query(final String username, final String password) throws Exception {
        String where = "where o.username=:username and o.password=:password";
        return super.getUniqueResult(where, new HashMap<String, Object>() {
            private static final long serialVersionUID = -6854809444774680421L;
            {
                put("username", username);
                put("password", password);
            }
        });
    }
}
