package com.ld.web.util;

/**
 * 
 * <p> Title: StringUtils </p>
 * <p> Copyright: Copyright (c) 2015 </p>
 * <p>Description: 字符串数据处理工具类 </p>
 * 
 * @author LD
 * 
 * @date 2015-08-06
 */
public class StringUtils {

    public static boolean isEmpty(String json) {
        return null == json || json.trim().length() == 0;
    }

}
