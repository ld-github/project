package com.ld.web.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.ld.web.config.Config;

/**
*
*<p>Title: SignUtil</p>
*<p>Copyright: Copyright (c) 2015</p>
*<p>Description: MD5根据json和key签名</p>
*
*@author LD
*
*@date 2015-11-06
*/
public class SignUtil {

    private static Logger logger = Logger.getLogger(SignUtil.class);

    /**
     * Sign by json and key
     * 
     * @param json
     * @param key
     * @return
     */
    public static String sign(String json, String key) {
        String signature = DigestUtils.md5Hex(json + key);
        logger.info(String.format("Sign result: %s by json: %s, key: %s", signature, json, key));
        return signature;
    }

    /**
     * Sign Json by json and key
     * 
     * @param obj
     * @param key
     * @return
     */
    public static String signJson(String json, String key) {
        String signature = sign(json, key);
        StringBuffer sb = new StringBuffer(Config.REQ_PARAMS_JSON + "=");
        sb.append(json);
        sb.append("&");
        sb.append(Config.REQ_PARAMS_SIGNATURE + "=");
        sb.append(signature);
        String signJson = sb.toString();
        logger.info(String.format("Sign json result: %s", signJson));
        return signJson;
    }

    /**
     * Check sign
     * 
     * @param json
     * @param signature
     * @return
     */
    public static boolean signCheck(String json, String key, String signature) {
        boolean status = sign(json, key).equals(signature);
        logger.info("Sign check status: " + status);
        return status;
    }
}
