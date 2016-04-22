package com.ld.web.util;

import java.io.IOException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * <p>Title: DESUtil </p>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p>Description: 加密工具类 </p>
 * 
 * @author LD
 * 
 * @date 2015-08-26
 */
public class EncryptionUtil {

    private static Logger logger = Logger.getLogger(EncryptionUtil.class);

    private static final String MD5 = "MD5";
    private static final String SHA256 = "SHA-256";
    private static final String DES = "DES";
    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final String DESEDE = "DESede";

    private static final String CIPHER_ALGORITHM_CBC = "DESede/CBC/PKCS5Padding";

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
     * Md5
     * 
     * @param source
     * @return
     * @throws Exception
     */
    public static String md5EncryptOutHex(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            String md5 = "";
            for (byte b : md.digest(source.getBytes())) {
                String temp = Integer.toHexString(b & 0xff);
                md5 += (temp.length() == 1 ? "0" + temp : temp);
            }
            return md5;
        } catch (Exception e) {
            // This should not happen!
            logger.error(String.format("MD5 EncryptOutHex error: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Sha256 to encryption
     * 
     * @param input
     * @return
     */
    public static String sha256(String input) {
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

    /**
     * Des
     * 
     * @param key
     * @param source
     * @return
     * @throws Exception
     */
    public static String desEncryptOutHex(String source, String key) {
        try {
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("utf-8"));
            SecretKeyFactory skf = SecretKeyFactory.getInstance(DES);
            Cipher cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.ENCRYPT_MODE, skf.generateSecret(desKeySpec));
            return ByteUtil.bytesToHexString(cipher.doFinal(source.getBytes("utf-8"))).toUpperCase();
        } catch (Exception e) {
            // This should not happen!
            logger.error(String.format("Des EncryptOutHex error: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * HmacSHA1
     * 
     * @param source
     * @param key
     * @return
     * @throws Exception
     */
    public static String hmacSHA1EncryptOutHex(String source, String key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("utf-8"), HMAC_SHA1);
            Mac mac = Mac.getInstance(HMAC_SHA1);
            mac.init(signingKey);
            return ByteUtil.bytesToHexString(mac.doFinal(source.getBytes("utf-8"))).toUpperCase();
        } catch (Exception e) {
            // This should not happen!
            logger.error(String.format("HmacSHA1 EncryptOutHex error: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * DESede/CBC/PKCS5Padding
     * 
     * @param source
     * @param key
     * @return
     * @throws Exception
     */
    public static String desedeEncryptOutHex(String source, String key, String iv) {
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("utf-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DESEDE);
            SecretKey secretKey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);

            IvParameterSpec ivSpec = StringUtil.isEmpty(iv) ? null : new IvParameterSpec(iv.getBytes("utf-8"));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            return ByteUtil.bytesToHexString((cipher.doFinal(source.getBytes("utf-8")))).toUpperCase();
        } catch (Exception e) {
            // This should not happen!
            logger.error(String.format("DESede/CBC/PKCS5Padding EncryptOutHex error: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * DESede/CBC/PKCS5Padding
     * 
     * @param source
     * @param key
     * @return
     * @throws Exception
     */
    public static String desedeDecryptOutHex(String source, String key, String iv) {
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("utf-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DESEDE);
            SecretKey secretKey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);

            IvParameterSpec ivSpec = StringUtil.isEmpty(iv) ? null : new IvParameterSpec(iv.getBytes("utf-8"));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            return new String(cipher.doFinal(ByteUtil.hexStringToBytes(source)), "utf-8");
        } catch (Exception e) {
            // This should not happen!
            logger.error(String.format("DESede/CBC/PKCS5Padding DecryptOutHex error: %s", e.getMessage()), e);
            throw new RuntimeException(e);
        }
    }

}
