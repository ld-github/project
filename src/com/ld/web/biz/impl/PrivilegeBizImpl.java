package com.ld.web.biz.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ld.web.bean.model.Privilege;
import com.ld.web.biz.PrivilegeBiz;
import com.ld.web.dao.PrivilegeDao;
import com.ld.web.util.JsonMapper;

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

    private final String PRIVILEGE_INIT_FILE = "privilege.init.txt";

    @Override
    public void init() throws Exception {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(PRIVILEGE_INIT_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();

        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }
        } catch (IOException e) {
            logger.error(String.format("Load privilege.init.txt file error: %s", e.getMessage()));
            throw new Exception("加载权限配置文件失败");
        }

        String data = sb.toString();
        logger.info(String.format("Load privilege.init.txt file content: %s", data));
        List<Privilege> privileges = JsonMapper.getInstance().toObject(data, new TypeReference<ArrayList<Privilege>>() {
        });
        for (Privilege p : privileges) {
            System.out.println(JsonMapper.getInstance().toJson(p));
            saveOrUpdate(p);
        }
    }

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
