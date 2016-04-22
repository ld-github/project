package com.ld.web.util;

/**
 * 
 *<p>Title: ByteUtil</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2015-12-23
 */
public class ByteUtil {

    public static String bytesToHexString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("ToHex encrypt data is null...");
        }
        String value = "", temp = "";
        for (byte b : bytes) {
            temp = Integer.toHexString(b & 0xff);
            value += temp.length() == 1 ? "0" + temp : temp;
        }
        return value;
    }

    /**
     * Convert hex string to byte[]
     * 
     * @param hexString
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     * 
     * @param c
     *            char
     * @return byte
     */
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
