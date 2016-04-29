package com.ld.web.action.server;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.ld.web.action.ServerAction;
import com.ld.web.bean.Page;
import com.ld.web.bean.model.ExceptionLog;
import com.ld.web.biz.ExceptionLogBiz;
import com.ld.web.util.DateUtil;
import com.ld.web.util.StringUtil;

/**
 * 
 * <p>Title: ExceptionLogAction</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-02-26
 */
@Action(value = "exceptionLog")
public class ExceptionLogAction extends ServerAction {

    private static final long serialVersionUID = -1590401916736665659L;

    private static final Logger logger = Logger.getLogger(ExceptionLogAction.class);

    @Resource
    private ExceptionLogBiz exceptionLogBiz;

    private Page<ExceptionLog> page;

    private String beginDate; // 起始时间

    private String endDate; // 结束时间

    private String keyword; // 关键字

    /**
     * Get page Records ExceptionLog
     */
    @Override
    public String getPageRecords() throws Exception {
        try {
            String pattern = DateUtil.TEMPORALTYPE_DATE;
            Date beginDate = StringUtil.isEmpty(this.beginDate) ? null : DateUtil.parse(this.beginDate, pattern);
            Date endDate = StringUtil.isEmpty(this.endDate) ? null : DateUtil.parse(this.endDate, pattern);

            super.putResult(exceptionLogBiz.getPage(page, beginDate, endDate, keyword));
        } catch (Exception e) {
            super.putResult(super.RESULT_PAGE, null);
            logger.error(String.format("Get ExceptionLog page error: %s", e.getMessage()), e);
        }
        return SUCCESS;
    }

    public void setPage(Page<ExceptionLog> page) {
        this.page = page;
    }

    public Page<ExceptionLog> getPage() {
        return page;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
