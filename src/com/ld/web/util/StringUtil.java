package com.ld.web.util;

/**
 * 
 * <p> Title: StringUtil </p>
 * <p> Copyright: Copyright (c) 2015 </p>
 * <p>Description: 字符串数据处理工具类 </p>
 * 
 * @author LD
 * 
 * @date 2015-08-06
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return null == str || str.trim().length() == 0;
    }

}
