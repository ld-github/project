package com.ld.web.dao;

import com.ld.web.bean.ExceptionLog;
import com.ld.web.bean.Page;

/**
 * 
 * <p>Title: ExceptionLogDao</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-2-26
 */
public interface ExceptionLogDao extends BaseDao<ExceptionLog> {
    /**
     * Query ExceptionLog data to page
     * 
     * @param page
     * @return
     */
    Page<ExceptionLog> getPage(Page<ExceptionLog> page);
}
