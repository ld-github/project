package com.ld.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@ParentPackage("json-default")
@Results({ @Result(type = "json", name = UserAction.SUCCESS, params = { "root", "result" }) })
public class BaseAction extends ActionSupport {
    private static final long serialVersionUID = 624599246438196900L;

    private Map<String, Object> result = new HashMap<String, Object>();

    public Map<String, Object> getResult() {
        return result;
    }

}
