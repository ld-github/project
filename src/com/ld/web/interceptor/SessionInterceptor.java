package com.ld.web.interceptor;

import com.ld.web.action.BaseAction;
import com.ld.web.bean.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class SessionInterceptor extends MethodFilterInterceptor {

    private static final long serialVersionUID = -4873322877077133583L;

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        ActionContext ctx = invocation.getInvocationContext();
        User user = (User) ctx.getSession().get(BaseAction.SESSION_USER);
        if (null == user) {
            return Action.LOGIN;
        }
        return invocation.invoke();
    }
}
