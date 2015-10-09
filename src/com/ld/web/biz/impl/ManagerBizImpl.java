package com.ld.web.biz.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.bean.Page;
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
        manager.setCreateDatetime(new Date());
        manager.setAvailable(true);
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

    @Override
    public Page<Manager> getPage(Long exceptMid, String username, Boolean available, Page<Manager> page) {
        return managerDao.getPage(exceptMid, username, available, page);
    }

    @Override
    public Manager changeAvailable(Manager manager) {
        Manager m = managerDao.getUniqueResult(manager.getId());
        m.setAvailable(manager.getAvailable());
        managerDao.update(m);
        return m;
    }

    @Override
    public Manager get(Long id) {
        return managerDao.getUniqueResult(id);
    }

}
