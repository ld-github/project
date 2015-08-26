package com.ld.web.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.ld.web.bean.model.User;
import com.ld.web.biz.UserBiz;
import com.ld.web.util.CharacterTool;

/**
 * 
 * <p>Title: SystemAction</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-03-11
 */
@Action(value = "system")
public class SystemAction extends BaseAction {

    private static final Logger logger = Logger.getLogger(SystemAction.class);

    private static final long serialVersionUID = -9049812879882243081L;

    // The front was introduced into object
    private User user;

    @Resource
    private UserBiz userBiz;

    /**
     * Whether you need check system initialization
     * 
     * @return
     * @throws Exception
     */
    public String checkSystem() throws Exception {
        super.putResult(userBiz.getUserCount() == 0);
        return SUCCESS;
    }

    /**
     * System initialization
     * 
     * @return
     * @throws Exception
     */
    public String initSystem() throws Exception {
        try {
            user.setPassword(CharacterTool.sha(CharacterTool.base64Decode(user.getPassword())));
            userBiz.saveUser(user);
            super.putResult(true);
        } catch (Exception e) {
            super.putResult(false);
            logger.error(String.format("System initialization error: %s", e.getMessage()), e);
        }
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
