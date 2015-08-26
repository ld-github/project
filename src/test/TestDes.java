package test;

import com.ld.web.util.DESUtil;

public class TestDes {
    public static void main(String[] args) {
        try {
            System.out.println(DESUtil.encryptOutHex("0001" + "001"
                    + "{\"thridcode\":\"12345612151611231\",\"name\":\"张三\"}", "7354B92D9A4E4794887B836A14B43A1C"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
