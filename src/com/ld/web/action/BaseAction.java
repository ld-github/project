package com.ld.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

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
@Results({ @Result(type = "json", name = UserAction.SUCCESS, params = { "root", "result" }) })
public class BaseAction extends ActionSupport {
    private static final long serialVersionUID = 624599246438196900L;

    public static final String KEY_CODE = "code";
    public static final String KEY_FLAG = "success";
    public static final String KEY_MESSAGE = "message";

    private Map<String, Object> result = new HashMap<String, Object>();

    public Map<String, Object> takeResult() {
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

    public Map<String, Object> takeSession() {
        ActionContext context = ActionContext.getContext();
        return context.getSession();
    }
}
