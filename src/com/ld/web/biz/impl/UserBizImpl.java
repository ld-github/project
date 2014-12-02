package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.bean.User;
import com.ld.web.biz.UserBiz;
import com.ld.web.dao.UserDao;

@Service
@Transactional
public class UserBizImpl implements UserBiz {
    @Resource
    private UserDao userDao;

    @Override
    public boolean saveUser(User u1, User u2) {
        return userDao.save(u1) && userDao.save(u2);
    }

}
