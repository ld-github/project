package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.bean.model.User;
import com.ld.web.biz.UserBiz;
import com.ld.web.dao.UserDao;

/**
 * 
 * <p>Title: UserBizImpl</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-8
 */
@Service
@Transactional
public class UserBizImpl implements UserBiz {

    private static final Logger logger = Logger.getLogger(UserBizImpl.class);

    @Resource
    private UserDao userDao;

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public long getUserCount() {
        return userDao.getTotal(null, null);
    }

    @Override
    public User login(String username, String password) {
        try {
            return userDao.query(username, password);
        } catch (Exception e) {
            logger.error("User login error by %s" + e.getMessage());
            return null;
        }
    }

}
