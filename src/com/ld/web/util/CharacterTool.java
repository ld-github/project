package com.ld.web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import it.sauronsoftware.base64.Base64;

/**
 * 
 * <p>Title: CharacterTool</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:字符串工具类</p>
 *
 * @author LD
 *
 * @date 2015-1-8
 */
public class CharacterTool {
    /**
     * Base64 decode
     * 
     * @param str
     * @return
     */
    public static String base64Decode(String str) {
        return null == str ? null : Base64.decode(str, "UTF-8");
    }

    /**
     * Base64 encode
     * 
     * @param str
     * @return
     */
    public static String base64Encode(String str) {
        return Base64.encode(str, "UTF-8");
    }

    /**
     * Md5加密
     * 
     * @param str
     * @return
     */
    public static String toMd5_1(String plainText) throws NoSuchAlgorithmException {
        String md5 = "";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        md5 = buf.toString();
        return md5;
    }

    /**
     * md5加密
     * 
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String toMd5_2(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("md5");
        String md5 = "";
        for (byte b : md.digest(str.getBytes())) {
            String temp = Integer.toHexString(b & 0xff);
            md5 += (temp.length() == 1 ? "0" + temp : temp);
        }
        return md5;
    }

    /**
     * Sha256 to encryption user.password
     * 
     * @param input
     * @return
     */
    public static String sha(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes("UTF-8"));
            byte[] data = md.digest();
            StringBuffer result = new StringBuffer(data.length * 2);
            for (int i = 0; i < data.length; i++) {
                result.append(Integer.toHexString(data[i] & 0xff));
            }
            return result.toString();
        } catch (Exception e) {
            // This should not happen!
            throw new RuntimeException(e);
        }
    }
}
