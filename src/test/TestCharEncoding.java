package test;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCharEncoding {

    public static String readUnicodeStr(String unicodeStr) {
        StringBuilder buf = new StringBuilder();
        // 因为java转义和正则转义，所以u要这么写
        String[] cc = unicodeStr.split("\\\\u");
        for (String c : cc) {
            if (c.equals(""))
                continue;
            int cInt = Integer.parseInt(c, 16);
            char cChar = (char) cInt;
            buf.append(cChar);
        }
        return buf.toString();
    }

    public static String readUnicodeStr2(String unicodeStr) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < unicodeStr.length(); i++) {
            char char1 = unicodeStr.charAt(i);
            if (char1 == '\\' && isUnicode(unicodeStr, i)) {
                String cStr = unicodeStr.substring(i + 2, i + 6);
                int cInt = Integer.parseInt(cStr, 16);
                buf.append((char) cInt);
                // 跨过当前unicode码，因为还有i++，所以这里i加5，而不是6
                i = i + 5;
            } else {
                buf.append(char1);
            }
        }
        return buf.toString();
    }

    private static boolean isUnicode(String unicodeStr, int i) {
        int len = unicodeStr.length();
        int remain = len - i;
        // unicode码，反斜杠后还有5个字符 uxxxx
        if (remain < 5)
            return false;

        char flag2 = unicodeStr.charAt(i + 1);
        if (flag2 != 'u')
            return false;
        String nextFour = unicodeStr.substring(i + 2, i + 6);
        return isHexStr(nextFour);
    }

    private static boolean isHexStr(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            boolean isHex = ch >= '1' && ch <= '9' || ch >= 'a' && ch <= 'f' || ch >= 'A' && ch <= 'F';
            if (!isHex)
                return false;
        }
        return true;
    }

    public static String toUnicode(String str) {
        char[] arChar = str.toCharArray();
        int iValue = 0;
        String uStr = "";
        for (int i = 0; i < arChar.length; i++) {
            iValue = (int) str.charAt(i);
            if (iValue <= 256) {
                uStr += "\\" + Integer.toHexString(iValue);
            } else {
                uStr += "\\u" + Integer.toHexString(iValue);
            }
        }
        return uStr;
    }

    public static String unescapeUnicode(String str){
        StringBuffer b=new StringBuffer();
        Matcher m = Pattern.compile("\\\\u([0-9a-fA-F]{4})").matcher(str);
        while(m.find())
            b.append((char)Integer.parseInt(m.group(1),16));
        return b.toString();
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "\u91CD\u5E86\u8D22\u7ECF\u804C\u4E1A\u5B66\u9662";
        String str1 = readUnicodeStr2(str) + "asd";
        System.out.println(str1);
        str1 = "id: 1, bb: 哈哈";
        System.out.println(toUnicode(str1));
        System.out.println(unescapeUnicode(str1));
        System.out.println(new String(str1.getBytes(), "utf-8"));
    }
}
