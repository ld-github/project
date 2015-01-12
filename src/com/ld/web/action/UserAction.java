package com.ld.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ld.web.bean.User;
import com.ld.web.biz.UserBiz;

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
@Results({ @Result(type = "json",
                                    name = UserAction.RESULT_SAVE,
                                    params = {"root", "result",
                                                       "excludeProperties", "users\\[\\d+\\].(password|username){1}, "
                                                                                      + "user.(id|password){1}"
                                                      }),
})
public class UserAction extends BaseAction {

    private static final long serialVersionUID = -4369317987413706899L;
    @Resource
    private UserBiz userBiz;

    public final static String RESULT_SAVE = "SAVE";

    public String save() throws Exception {
        Map<String, Object> result = getResult();
        try {
            User u1 = new User("Tom1", "TomC1");
            User u2 = new User("Tom2", "TomC2");
            boolean flag = userBiz.saveUser(u1, u2);
            if (flag) {
                List<User> users = new ArrayList<User>();
                users.add(u1);
                users.add(u2);
                result.put("users", users);
                result.put("user", u1);
            }
            result.put("flag", result);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("flag", false);
        }
        return RESULT_SAVE;
    }
}
