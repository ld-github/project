package test;

import java.util.Map;

import com.ld.web.util.CharacterTool;
import com.ld.web.util.RSACoder;

public class TestRsa {
    public static void main(String[] args) throws Exception {
        Map<String, Object> keyMap = RSACoder.initKey();

        String publicKey = RSACoder.getPublicKey(keyMap);
        String privateKey = RSACoder.getPrivateKey(keyMap);
        System.out.println("公钥: \n\r" + publicKey);
        System.out.println("私钥： \n\r" + privateKey);

        System.out.println("私钥加密——公钥解密");
        String inputStr = "page:{currentPage:1, totalPage:20, records:[]}";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);

        String encodedDataStr = CharacterTool.base64EecodeBuffer(encodedData);
        System.out.println("EncodedData str:" + encodedDataStr);

        byte[] decodedData = RSACoder.decryptByPublicKey(CharacterTool.base64DecodeBuffer(encodedDataStr), publicKey);

        String outputStr = new String(decodedData);
        System.out.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);

        System.out.println("私钥签名——公钥验证签名");
        // 产生签名 这里的encodedData可以与下面的encodedData同时换成new int[]{2,45}
        String sign = RSACoder.sign(encodedData, privateKey);
        // 数字签名只要公钥人拿到签名的sign对比
        // 自己公钥通过同样的byte[]运算得到签名是否一致。是到致代表这个公钥就是对的，就是为现在发私钥人服务的。
        System.out.println("签名:\r" + sign);

        // 验证签名
        boolean status = RSACoder.verify(encodedData, publicKey, sign);
        System.out.println("状态:\r" + status);
    }
}
