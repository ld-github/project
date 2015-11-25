package test;

import com.ld.web.util.CharacterTool;
import com.ld.web.util.SignUtil;

/**
 * 
 * <p>Title: TestSecurity</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-15
 */
public class TestSecurity {
    public static void main(String[] args) {
        String str = "admin";
        System.out.println(CharacterTool.sha256(str));
        System.out.println(CharacterTool.sha256(str).length());
        String json = "\"abc\"";
        String key = "zhax";
        System.out.println(SignUtil.signJson(json, key));
    }
}
