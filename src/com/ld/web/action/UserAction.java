package com.ld.web.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.google.code.kaptcha.Constants;
import com.ld.web.bean.User;
import com.ld.web.biz.UserBiz;
import com.ld.web.util.Util;

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
@Results({ 
    @Result(type = "json",
                    name = UserAction.SAVE,
                    params = {"root", "result", "excludeProperties", "users\\[\\d+\\].(password|username){1}, user.(id|password){1}"
      }), 
    @Result(type = "json",
                    name = UserAction.TAKE_LOGIN_USER,
                    params = {"root", "result", "excludeProperties", "user.(id|password){1}"
      })
})
public class UserAction extends BaseAction {

    private static final long serialVersionUID = -4369317987413706899L;

    private static final Logger log = Logger.getLogger(UserAction.class);

    @Resource
    private UserBiz userBiz;

    // The front was introduced into object
    private User user;

    private String kaptcha;

    public static final String SAVE = "save";
    public static final String TAKE_LOGIN_USER = "takeLoginUser";

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
            log.error("Save user error!");
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
        User u = userBiz.login(user.getUsername(), User.sha(Util.base64Decode(user.getPassword())));
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
        return TAKE_LOGIN_USER;
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
