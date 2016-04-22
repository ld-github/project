package com.ld.web.dao;

import java.util.Date;

import com.ld.web.bean.Page;
import com.ld.web.bean.model.ExceptionLog;

/**
 * 
 * <p>Title: ExceptionLogDao</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-02-26
 */
public interface ExceptionLogDao extends BaseDao<ExceptionLog> {
    /**
     * Query ExceptionLog records to page
     * 
     * @param page
     * @param endDate
     * @param beginDate
     * @param keyword
     * @return
     */
    Page<ExceptionLog> getPage(Page<ExceptionLog> page, Date beginDate, Date endDate, String keyword);
}
