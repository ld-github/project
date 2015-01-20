package com.ld.web.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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

    private final String KEY_CODE = "code";
    private final String KEY_FLAG = "success";
    private final String KEY_MESSAGE = "message";

    private Map<String, Object> result = new HashMap<String, Object>();

    public Map<String, Object> getResult() {
        return result;
    }

    public void putResult(String key, Object obj) {
        result.put(key, obj);
    }

    public void putResult(boolean success) {
        result.put(KEY_FLAG, success);
    }

    public void putResult(boolean success, String message) {
        putResult(success);
        result.put(KEY_MESSAGE, message);
    }

    public void putResult(boolean success, String message, String code) {
        putResult(success, message);
        result.put(KEY_CODE, code);
    }

    /**
     * Take session
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
        if (!takeSession().containsKey(KEY_SESSION_USER)) {
            takeSession().put(KEY_SESSION_USER, u);
            return;
        }
        for (Entry<String, Object> entry : takeSession().entrySet()) {
            if (entry.getKey().equals(KEY_SESSION_USER)) {
                entry.setValue(u);
            }
        }
    }
    
    /**
     * Take user from session
     * @return
     */
    public User takeSessionUser(){
        if(takeSession().containsKey(KEY_SESSION_USER)){
            return (User) takeSession().get(KEY_SESSION_USER);
        }
        return null;
    }
}
