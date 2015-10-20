package com.ld.web.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * <p>Title: CharacterTool</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:字符串工具类</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
public class CharacterTool {

    private static final Logger logger = Logger.getLogger(CharacterTool.class);

    private static final String MD5 = "md5";
    private static final String SHA256 = "SHA-256";

    /**
     * Base64 decode
     * 
     * @param str
     * @return
     */
    public static String base64Decode(String str) {
        return null == str ? null : new String(Base64.decodeBase64(str));
    }

    /**
     * Base64 encode
     * 
     * @param str
     * @return
     */
    public static String base64Encode(String str) {
        return Base64.encodeBase64String(str.getBytes());
    }

    /**
     * Base64 decode buffer
     * 
     * @param str
     * @return
     * @throws IOException
     */
    public static byte[] base64DecodeBuffer(String str) throws IOException {
        return new BASE64Decoder().decodeBuffer(str);
    }

    /**
     * Base64 encode buffer
     * 
     * @param bytes
     * @return
     */
    public static String base64EecodeBuffer(byte[] bytes) {
        return new BASE64Encoder().encodeBuffer(bytes);
    }

    /**
     * Md5加密
     * 
     * @param str
     * @return
     */
    public static String toMd5Way1(String plainText) {
        try {
            String md5 = "";
            MessageDigest md = MessageDigest.getInstance(MD5);
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
        } catch (Exception e) {
            logger.error(String.format("MD5 to encryption error: %s", e.getMessage()), e);
            // This should not happen!
            throw new RuntimeException(e);
        }
    }

    /**
     * Md5加密
     * 
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String toMd5Way2(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            String md5 = "";
            for (byte b : md.digest(str.getBytes())) {
                String temp = Integer.toHexString(b & 0xff);
                md5 += (temp.length() == 1 ? "0" + temp : temp);
            }
            return md5;
        } catch (Exception e) {
            logger.error(String.format("MD5 to encryption error: %s", e.getMessage()), e);
            // This should not happen!
            throw new RuntimeException(e);
        }
    }

    /**
     * Sha256 to encryption
     * 
     * @param input
     * @return
     */
    public static String sha(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA256);
            md.update(input.getBytes("UTF-8"));
            byte[] data = md.digest();
            StringBuffer result = new StringBuffer(data.length * 2);
            for (int i = 0; i < data.length; i++) {
                result.append(Integer.toHexString(data[i] & 0xff));
            }
            return result.toString();
        } catch (Exception e) {
            logger.error(String.format("SHA256 to encryption error: %s", e.getMessage()), e);
            // This should not happen!
            throw new RuntimeException(e);
        }
    }
}
