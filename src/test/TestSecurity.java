package test;

import com.ld.web.util.CharacterTool;

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
        System.out.println(CharacterTool.sha(str));
        System.out.println(CharacterTool.sha(str).length());
    }
}
