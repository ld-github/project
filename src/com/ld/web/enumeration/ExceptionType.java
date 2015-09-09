package com.ld.web.enumeration;

public enum ExceptionType {

    /**
     * MANAGER:管理员 USER:用户
     */
    MANAGER(0), USER(1);

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
        return null;
    }

    private ExceptionType(int value) {
        this.value = value;
    }
}
