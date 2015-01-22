package com.ld.web.util;

import it.sauronsoftware.base64.Base64;

/**
 * 
 * <p>Title: Util</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-8
 */
public class Util {
    /**
     * base64
     * 
     * @param input
     * @return
     */
    public static String base64Decode(String str) {
        return null == str ? null : Base64.decode(str, "UTF-8");
    }
}
