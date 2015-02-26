package com.ld.web.biz;

import com.ld.web.bean.ExceptionLog;
import com.ld.web.bean.Page;

/**
 * 
 * <p>Title: ExceptionLogBiz</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-2-26
 */
public interface ExceptionLogBiz {
    /**
     * Query ExceptionLog records to page
     * 
     * @param page
     * @return
     */
    Page<ExceptionLog> getPage(Page<ExceptionLog> page);
}
