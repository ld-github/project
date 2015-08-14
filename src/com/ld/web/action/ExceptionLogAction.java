package com.ld.web.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.ld.web.bean.Page;
import com.ld.web.bean.model.ExceptionLog;
import com.ld.web.biz.ExceptionLogBiz;

/**
 * 
 * <p>Title: ExceptionLogAction</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-2-26
 */
@Action(value = "exceptionLog")
public class ExceptionLogAction extends BaseAction {

    private static final long serialVersionUID = -1590401916736665659L;

    private static final Logger logger = Logger.getLogger(ExceptionLogAction.class);

    @Resource
    private ExceptionLogBiz exceptionLogBiz;

    private Page<ExceptionLog> page;

    /**
     * Get page Records ExceptionLog
     */
    @Override
    public String getPageRecords() throws Exception {
        try {
            super.putResult("page", exceptionLogBiz.getPage(page));
        } catch (Exception e) {
            super.putResult("page", null);
            logger.error(String.format("Get ExceptionLog page error by %s", e.getMessage()));
        }
        return SUCCESS;
    }

    public void setPage(Page<ExceptionLog> page) {
        this.page = page;
    }

    public Page<ExceptionLog> getPage() {
        return page;
    }

}
