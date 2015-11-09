package com.ld.web.enumeration;

/**
 * 
 *<p>Title: ExceptionType</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: 异常类型</p>
 *
 *@author LD
 *
 *@date 2015-09-22
 */
public enum ExceptionType {

    /**
     * MANAGER:管理员 USER:用户 SYSTEM:系统
     */
    SYSTEM(0), MANAGER(1), USER(2), ;

    private int value = 0;

    public int value() {
        return this.value;
    }

    public static ExceptionType get(int value) {
        for (ExceptionType i : ExceptionType.values()) {
            if (value == i.value) {
                return i;
            }
        }
        return SYSTEM;
    }

    private ExceptionType(int value) {
        this.value = value;
    }

    public static final String UID = "uid";
    public static final String USERNAME = "username";
    public static final String EXCEPTION_TYPE = "exceptionType";
}
