package com.ld.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.ld.web.bean.User;
import com.ld.web.biz.UserBiz;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@ParentPackage("json-default")
@Action(value = "user")
@Results({ @Result(type = "json",
                                    name = UserAction.RESULT_SAVE,
                                    params = {"root", "data",
                                                       "excludeProperties", "users\\[\\d+\\].(password|username){1}, "
                                                                                      + "user.(id|password){1}"
                                                      }),
                    @Result(type = "json",
                                    name = UserAction.SUCCESS, 
                                    params = {"root", "data"})
})
public class UserAction extends ActionSupport {

    private static final long serialVersionUID = -4369317987413706899L;
    @Resource
    private UserBiz userBiz;

    public final static String RESULT_SAVE = "SAVE";

    private Map<String, Object> data;

    public String save() throws Exception {
        data = new HashMap<String, Object>();
        try {
            User u1 = new User("Tom1", "TomC1");
            User u2 = new User("Tom2", "TomC2");
            boolean result = userBiz.saveUser(u1, u2);
            if (result) {
                List<User> users = new ArrayList<User>();
                users.add(u1);
                users.add(u2);
                data.put("users", users);
                data.put("user", u1);
            }
            data.put("flag", result);
        } catch (Exception e) {
            e.printStackTrace();
            data.put("flag", false);
        }
        return RESULT_SAVE;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
