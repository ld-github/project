package com.ld.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.ld.web.bean.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Result is status default json, result excludeProperties example:
 * @Result(type = "json",
 *                   name = UserAction.SAVE,
 *                   params = {"root", "result", "excludeProperties", "users\\[\\d+\\].(password|username){1}, user.(id|password){1}"
 * })
 *
 * <p>Title: BaseAction</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-8
 */
@Controller
@Namespace("/")
@ParentPackage("base-default")
@Results({ @Result(type = "json", name = Action.SUCCESS, params = { "root", "result" }) })
public class BaseAction extends ActionSupport {

    private static final long serialVersionUID = 624599246438196900L;

    public static final String SESSION_USER = "sessionUser";

    private final String RESULT_CODE = "code";
    private final String RESULT_STATUS = "status";
    private final String RESULT_MESSAGE = "message";

    private Map<String, Object> result = new HashMap<String, Object>();

    public Map<String, Object> getResult() {
        return this.result;
    }

    /**
     * Put key and object to result
     * 
     * @param key
     * @param obj
     */
    public void putResult(String key, Object obj) {
        this.result.put(key, obj);
    }

    /**
     * Put code to result
     * 
     * @param code
     */
    public void putResult(Integer code) {
        this.putResult(RESULT_CODE, code);
    }

    /**
     * Put status to result
     * 
     * @param status
     */
    public void putResult(boolean status) {
        this.putResult(RESULT_STATUS, status);
    }

    /**
     * Put message to result
     * 
     * @param message
     */
    public void putResult(String message) {
        this.putResult(RESULT_MESSAGE, message);
    }

    /**
     * Put code and message to result
     * 
     * @param code
     * @param message
     */
    public void putResult(Integer code, String message) {
        this.putResult(code);
        this.putResult(message);
    }

    /**
     * Put status and message to result
     * 
     * @param status
     * @param message
     */
    public void putResult(boolean status, String message) {
        this.putResult(status);
        this.putResult(message);
    }

    /**
     * Put status and message and code to result
     * 
     * @param status
     * @param message
     * @param code
     */
    public void putResult(boolean status, String message, Integer code) {
        this.putResult(status, message);
        this.putResult(code);
    }

    /**
     * Take request
     * 
     * @return
     */
    public HttpServletRequest takeRequest() {
        return ServletActionContext.getRequest();
    }

    /**
     * Take response
     * 
     * @return
     */
    public HttpServletResponse takeResponse() {
        return ServletActionContext.getResponse();
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
        takeSession().put(SESSION_USER, u);
    }

    /**
     * Take user from session
     * 
     * @return
     */
    public User takeSessionUser() {
        return takeSession().containsKey(SESSION_USER) ? (User) takeSession().get(SESSION_USER) : null;
    }

    public String save() throws Exception {
        throw new Exception("Subclass does not implement");
    }

    public String get() throws Exception {
        throw new Exception("Subclass does not implement");
    }

    public String update() throws Exception {
        throw new Exception("Subclass does not implement");
    }

    public String delete() throws Exception {
        throw new Exception("Subclass does not implement");
    }

    public String getPageRecords() throws Exception {
        throw new Exception("Subclass does not implement");
    }
}
