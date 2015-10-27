package com.ld.web.biz;

import java.util.Date;

import com.ld.web.bean.Page;
import com.ld.web.bean.model.ExceptionLog;

/**
 * 
 * <p>Title: ExceptionLogBiz</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-02-26
 */
public interface ExceptionLogBiz {
    /**
     * Query ExceptionLog records to page
     * 
     * @param page
     * @param beginDate
     * @param endDate
     * @return
     */
    Page<ExceptionLog> getPage(Page<ExceptionLog> page, Date beginDate, Date endDate);
}
