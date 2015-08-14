package com.ld.web.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.google.code.kaptcha.Constants;
import com.ld.web.bean.model.User;
import com.ld.web.biz.UserBiz;
import com.ld.web.util.CharacterTool;

/**
 * 
 * <p>Title: UserAction</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-8
 */
@Action(value = "user")
public class UserAction extends BaseAction {

    private static final long serialVersionUID = -4369317987413706899L;

    private static final Logger logger = Logger.getLogger(UserAction.class);

    @Resource
    private UserBiz userBiz;

    // The front was introduced into object
    private User user;

    private String kaptcha;

    /**
     * Save user
     */
    @Override
    public String save() throws Exception {
        try {
            userBiz.saveUser(user);
            super.putResult(true);
        } catch (Exception e) {
            super.putResult(false);
            e.printStackTrace();
            logger.error(String.format("Save user error by %s", e.getMessage()));
        }
        return SUCCESS;
    }

    /**
     * User login
     * 
     * @return
     * @throws Exception
     */
    public String login() throws Exception {
        String kaptcha = (String) super.takeSession().get(Constants.KAPTCHA_SESSION_KEY);
        if (!kaptcha.equals(this.kaptcha)) {
            super.putResult(false, "验证码输入错误");
            return SUCCESS;
        }
        User u = userBiz.login(user.getUsername(), CharacterTool.sha(CharacterTool.base64Decode(user.getPassword())));
        if (null != u) {
            super.putSessionUser(u);
        }
        super.putResult(null != u, null != u ? "用户登录成功" : "用户名或密码错误");
        return SUCCESS;
    }

    /**
     * Take login user info
     * 
     * @return
     * @throws Exception
     */
    public String takeLoginUser() throws Exception {
        User u = super.takeSessionUser();
        super.putResult("user", u);
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha;
    }

}
