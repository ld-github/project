package com.ld.web.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;

import com.ld.web.bean.User;
import com.ld.web.biz.UserBiz;
import com.ld.web.util.Util;

/**
 * 
 * <p>Title: SystemAction</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-3-11
 */
@Action(value = "system")
public class SystemAction extends BaseAction {

    private static final long serialVersionUID = -9049812879882243081L;

    // The front was introduced into object
    private User user;

    @Resource
    private UserBiz userBiz;

    public String checkSystem() throws Exception {
        super.putResult(userBiz.getUserCount() == 0);
        return SUCCESS;
    }

    public String initSystem() throws Exception {
        user.setPassword(User.sha(Util.base64Decode(user.getPassword())));
        super.putResult(userBiz.saveUser(user));
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
