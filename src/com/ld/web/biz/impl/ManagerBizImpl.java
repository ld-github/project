package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.bean.model.Manager;
import com.ld.web.biz.ManagerBiz;
import com.ld.web.dao.ManagerDao;

/**
 * 
 * <p>Title: ManagerBizImpl</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
@Service
@Transactional
public class ManagerBizImpl implements ManagerBiz {

    private static final Logger logger = Logger.getLogger(ManagerBizImpl.class);

    @Resource
    private ManagerDao managerDao;

    @Override
    public void saveUser(Manager manager) {
        managerDao.save(manager);
    }

    @Override
    public long getUserCount() {
        return managerDao.getTotal(null, null);
    }

    @Override
    public Manager login(String username, String password) {
        try {
            return managerDao.query(username, password);
        } catch (Exception e) {
            logger.error(String.format("User login error: %s", e.getMessage()), e);
            return null;
        }
    }

}
