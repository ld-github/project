package com.ld.web.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.ld.web.bean.model.Manager;
import com.ld.web.dao.ManagerDao;

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
}
