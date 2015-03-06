package com.ld.web.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;

import com.ld.web.biz.UserBiz;

/**
 * 
 * <p>Title: SystemAction</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-3-6
 */
@Action(value = "system")
public class SystemAction extends BaseAction {

    private static final long serialVersionUID = -9049812879882243081L;

    @Resource
    private UserBiz userBiz;

    public String checkSystem() throws Exception {
        super.putResult(userBiz.getUserCount() == 0);
        return SUCCESS;
    }
}
