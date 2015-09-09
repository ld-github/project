package com.ld.web.action.server;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.google.code.kaptcha.Constants;
import com.ld.web.action.ServerAction;
import com.ld.web.bean.model.Manager;
import com.ld.web.biz.ManagerBiz;
import com.ld.web.util.CharacterTool;

/**
 * 
 * <p>Title: ManagerAction</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
@Action(value = "manager")
public class ManagerAction extends ServerAction {

    private static final long serialVersionUID = -4369317987413706899L;

    private static final Logger logger = Logger.getLogger(ManagerAction.class);

    @Resource
    private ManagerBiz managerBiz;

    // The front was introduced into object
    private Manager manager;

    private String kaptcha;

    /**
     * Manager login
     * 
     * @return
     * @throws Exception
     */
    public String login() throws Exception {
        logger.info(String.format("Username %s request login...", manager.getUsername()));

        String kaptcha = (String) super.takeSession().get(Constants.KAPTCHA_SESSION_KEY);
        if (!kaptcha.equals(this.kaptcha)) {
            super.putResult(false, "验证码输入错误");
            logger.info(String.format("Username %s login verification code error...", manager.getUsername()));
            return SUCCESS;
        }
        Manager u = managerBiz.login(manager.getUsername(), CharacterTool.sha(CharacterTool.base64Decode(manager.getPassword())));
        boolean success = null != u;
        if (success) {
            super.putSessionManager(u);
        }
        super.putResult(success, success ? "用户登录成功" : "用户名或密码错误");
        logger.info(String.format("Username %s login %s...", manager.getUsername(), success ? "success" : "failed"));
        return SUCCESS;
    }

    /**
     * Take login manager info
     * 
     * @return
     * @throws Exception
     */
    public String takeLoginManager() throws Exception {
        Manager manager = super.takeSessionManager();
        super.putResult("manager", manager);
        return SUCCESS;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha;
    }

}
