package com.ld.web.dao.impl;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Repository;

import com.ld.web.bean.ExceptionLog;
import com.ld.web.bean.Page;
import com.ld.web.dao.ExceptionLogDao;

/**
 * 
 * <p>Title: ExceptionLogDaoImpl</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-2-26
 */
@Repository
public class ExceptionLogDaoImpl extends BaseDaoImpl<ExceptionLog> implements ExceptionLogDao {

    @Override
    public Page<ExceptionLog> getPage(Page<ExceptionLog> page) {
        LinkedHashMap<String, String> orders = new LinkedHashMap<String, String>();
        orders.put("o.id", "desc");
        return super.getPage(null, null, orders, page);
    }

}
