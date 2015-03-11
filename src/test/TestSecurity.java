package test;

import it.sauronsoftware.base64.Base64;

import com.ld.web.bean.User;

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
        System.out.println(User.sha(str));
        System.out.println(User.sha(str).length());

        String encoded = Base64.encode(",!@#$%^&*()_+{}:<>?ASSDFGFDG！", "UTF-8");
        System.out.println(encoded);
        String decoded = Base64.decode(encoded, "UTF-8");
        System.out.println(decoded);
    }
}
