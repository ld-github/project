package com.ld.web.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 
 * <p>Title: DESUtil</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description: DES加密工具类</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
public class DESUtil {

    private static final String DES = "DES";

    /**
     * Des加密
     * 
     * @param key
     * @param source
     * @return
     * @throws Exception
     */
    public static String encryptOutHex(String source, String key) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("utf-8"));
        SecretKeyFactory skf = SecretKeyFactory.getInstance(DES);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, skf.generateSecret(desKeySpec));
        return toHex(cipher.doFinal(source.getBytes("utf-8"))).toUpperCase();
    }

    /**
     * Byte转换为16进制数据
     * 
     * @param bytes
     * @return
     */
    protected static String toHex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("转换的数据为空");
        }
        String value = "", temp = "";
        for (byte b : bytes) {
            temp = Integer.toHexString(b & 0xff);
            value += temp.length() == 1 ? "0" + temp : temp;
        }
        return value;
    }
}
