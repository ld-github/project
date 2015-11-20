package com.ld.web.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.bean.model.Privilege;
import com.ld.web.biz.PrivilegeBiz;
import com.ld.web.dao.PrivilegeDao;

/**
 * 
 *<p>Title: PrivilegeBizImpl</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: 权限业务逻辑处理</p>
 *
 *@author LD
 *
 *@date 2015-11-04
 */
@Service
@Transactional
public class PrivilegeBizImpl implements PrivilegeBiz {

    private static final Logger logger = Logger.getLogger(PrivilegeBizImpl.class);

    @Resource
    private PrivilegeDao privilegeDao;

    @Override
    public boolean saveOrUpdate(Privilege privilege) {
        try {
            privilegeDao.saveOrUpdate(privilege);
            return true;
        } catch (Exception e) {
            logger.error(String.format("Save or update privilege error by: %s", e.getMessage(), e));
            return false;
        }
    }

    @Override
    public List<Privilege> getAll() {
        return privilegeDao.getList();
    }

    @Override
    public Privilege get(Long id) {
        return privilegeDao.getUniqueResult(id);
    }

    @Override
    public void truncate() {
        privilegeDao.truncate();
    }

    @Override
    public boolean delete(Privilege privilege) {
        try {
            privilegeDao.delete(privilege);
            return true;
        } catch (Exception e) {
            logger.error(String.format("Delete privilege error by: %s", e.getMessage()), e);
            return false;
        }
    }

}
