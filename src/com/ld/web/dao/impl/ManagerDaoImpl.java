package com.ld.web.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ld.web.bean.Page;
import com.ld.web.bean.model.Manager;
import com.ld.web.dao.ManagerDao;
import com.ld.web.util.StringUtil;

/**
 * 
 * <p>Title: ManagerDaoImpl</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
@Repository
public class ManagerDaoImpl extends BaseDaoImpl<Manager> implements ManagerDao {

    @Override
    public Manager query(final String username, final String password) throws Exception {
        String where = "where o.username=:username and o.password=:password";
        return super.getUniqueResult(where, new HashMap<String, Object>() {
            private static final long serialVersionUID = -6854809444774680421L;
            {
                put("username", username);
                put("password", password);
            }
        });
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
        return super.getPage(where, params, orders, page);
    }
}
