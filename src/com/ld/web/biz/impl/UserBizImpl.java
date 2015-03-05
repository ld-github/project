package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.bean.User;
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
    @Resource
    private UserDao userDao;

    @Override
    public boolean saveUser(User u1, User u2) {
        return userDao.save(u1) && userDao.save(u2);
    }

    @Override
    public long getUserCount() {
        return userDao.getTotal(null, null);
    }

}
