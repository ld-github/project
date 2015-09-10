package com.ld.web.biz.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.bean.Page;
import com.ld.web.bean.model.Manager;
import com.ld.web.biz.ManagerBiz;
import com.ld.web.dao.ManagerDao;
import com.ld.web.util.StringUtil;

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
        manager.setCreateTime(new Date());
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
        String where = "where 1=1 ";
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != exceptMid) {
            where += "and o.id !=:exceptMid ";
            params.put("exceptMid", exceptMid);
        }
        if (!StringUtil.isEmpty(username)) {
            where += "and o.username like :username ";
            params.put("username", "%" + username + "%");
        }
        if (null != available) {
            where += "and o.available =:available ";
            params.put("available", available);
        }
        LinkedHashMap<String, String> orders = new LinkedHashMap<String, String>();
        orders.put("o.id", "asc");
        return managerDao.getPage(where, params, orders, page);
    }

}
