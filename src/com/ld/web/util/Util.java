package com.ld.web.util;

import it.sauronsoftware.base64.Base64;

/**
 * 
 * <p>Title: User</p>
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
    public static String base64(String input) {
        return null == input ? null : Base64.decode(input, "UTF-8");
    }
}
