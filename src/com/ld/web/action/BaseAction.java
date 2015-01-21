package com.ld.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.ld.web.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

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
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Results({ @Result(type = "json", name = ActionSupport.SUCCESS, params = { "root", "result" }) })
public class BaseAction extends ActionSupport {
    private static final long serialVersionUID = 624599246438196900L;

    private final String KEY_SESSION_USER = "sessionUser";

    private final String RESULT_CODE = "code";
    private final String RESULT_SUCCESS = "success";
    private final String RESULT_MESSAGE = "message";

    private Map<String, Object> result = new HashMap<String, Object>();

    public Map<String, Object> getResult() {
        return this.result;
    }

    public void putResult(String key, Object obj) {
        this.result.put(key, obj);
    }

    public void putResult(boolean success) {
        this.putResult(RESULT_SUCCESS, success);
    }

    public void putResult(boolean success, String message) {
        this.putResult(success);
        this.putResult(RESULT_MESSAGE, message);
    }

    public void putResult(boolean success, String message, String code) {
        this.putResult(success, message);
        this.putResult(RESULT_CODE, code);
    }

    /**
     * Take session
     * 
     * @return
     */
    public Map<String, Object> takeSession() {
        ActionContext context = ActionContext.getContext();
        return context.getSession();
    }

    /**
     * Put user to session
     * 
     * @param u
     */
    public void putSessionUser(User u) {
        takeSession().put(KEY_SESSION_USER, u);
    }

    /**
     * Take user from session
     * 
     * @return
     */
    public User takeSessionUser() {
        return takeSession().containsKey(KEY_SESSION_USER) ? (User) takeSession().get(KEY_SESSION_USER) : null;
    }
}
