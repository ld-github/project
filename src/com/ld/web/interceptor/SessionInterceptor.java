package com.ld.web.interceptor;

import com.ld.web.action.BaseAction;
import com.ld.web.bean.User;
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
 * @date 2015-1-21
 */
public class SessionInterceptor extends MethodFilterInterceptor {

    private static final long serialVersionUID = -4873322877077133583L;

    /**
     * Check session whether or not hava login user, if not, return NONE
     * 
     */
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        ActionContext ctx = invocation.getInvocationContext();
        User user = (User) ctx.getSession().get(BaseAction.SESSION_USER);
        if (null == user) {
            return Action.NONE;
        }
        return invocation.invoke();
    }
}
