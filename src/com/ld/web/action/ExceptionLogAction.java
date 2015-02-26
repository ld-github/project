package com.ld.web.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.ld.web.bean.ExceptionLog;
import com.ld.web.bean.Page;
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

    private static final Logger log = Logger.getLogger(UserAction.class);

    @Resource
    private ExceptionLogBiz exceptionLogBiz;

    private Page<ExceptionLog> page;

    @Override
    public String getPageRecords() throws Exception {
        try {
            super.putResult("page", exceptionLogBiz.getPage(page));
        } catch (Exception e) {
            super.putResult("page", null);
            log.error("Get ExceptionLog page error!");
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
