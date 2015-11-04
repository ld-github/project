package com.ld.web.interceptor;

import org.apache.log4j.MDC;

import com.ld.web.enumeration.ExceptionType;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 
 * <p>Title: ServerMDCInterceptor</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-21
 */
public class ServerMDCInterceptor extends MethodFilterInterceptor {

    private static final long serialVersionUID = 5686124529690894598L;

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        MDC.put(ExceptionType.EXCEPTION_TYPE, ExceptionType.MANAGER.value());
        return invocation.invoke();
    }

}
