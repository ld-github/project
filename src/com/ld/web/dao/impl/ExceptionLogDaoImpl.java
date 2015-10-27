package com.ld.web.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ld.web.bean.Page;
import com.ld.web.bean.model.ExceptionLog;
import com.ld.web.dao.ExceptionLogDao;

/**
 * 
 * <p>Title: ExceptionLogDaoImpl</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-02-26
 */
@Repository
public class ExceptionLogDaoImpl extends BaseDaoImpl<ExceptionLog> implements ExceptionLogDao {

    @Override
    public Page<ExceptionLog> getPage(Page<ExceptionLog> page, Date beginDate, Date endDate) {
        String where = "where 1=1 ";
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != beginDate) {
            where += "and o.createDatetime >=:beginDate ";
            params.put("beginDate", beginDate);
        }
        if (null != endDate) {
            where += "and o.createDatetime <=:endDate ";
            params.put("endDate", endDate);
        }
        LinkedHashMap<String, String> orders = new LinkedHashMap<String, String>();
        orders.put("o.id", "desc");
        return super.getPage(where, params, orders, page);
    }

}
