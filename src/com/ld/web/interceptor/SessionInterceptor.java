package com.ld.web.interceptor;

import org.apache.log4j.MDC;

import com.ld.web.action.BaseAction;
import com.ld.web.bean.model.Manager;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 
 * <p>Title: SessionInterceptor</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-21
 */
public class SessionInterceptor extends MethodFilterInterceptor {

    private static final long serialVersionUID = -4873322877077133583L;

    /**
     * Check session whether or not hava login manager, if not, return Action.login
     * 
     */
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        ActionContext ctx = invocation.getInvocationContext();
        Manager manager = (Manager) ctx.getSession().get(BaseAction.SESSION_USER);
        if (null == manager) {
            return Action.LOGIN;
        }
        MDC.put("uid", manager.getId());
        MDC.put("username", manager.getUsername());
        return invocation.invoke();
    }
}
