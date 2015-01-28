package com.ld.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
    @Resource
    private UserBiz userBiz;

    // The front was introduced into object
    private User user;

    private String kaptcha;

    public static final String SAVE = "save";
    public static final String TAKE_LOGIN_USER = "takeLoginUser";

    public String save() throws Exception {
        try {
            User u1 = new User("Tom1", "TomC1");
            User u2 = new User("Tom2", "TomC2");
            boolean flag = userBiz.saveUser(u1, u2);
            if (flag) {
                List<User> users = new ArrayList<User>();
                users.add(u1);
                users.add(u2);
                super.putResult("users", users);
                super.putResult("user", u1);
            }
            super.putResult(flag);
        } catch (Exception e) {
            e.printStackTrace();
            super.putResult(false);
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
        System.out.println(user.getUsername());
        System.out.println(Util.base64Decode(user.getPassword()));
        super.putSessionUser(user);
        super.putResult(true);
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

    /**
     * Check login user info
     * 
     * @return
     * @throws Exception
     */
    public String checkSessionUser() throws Exception {
        super.putResult(null == super.takeSessionUser());
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
