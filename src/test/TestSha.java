package test;

import com.ld.web.bean.User;

/**
 * 
 * <p>Title: User</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-15
 */
public class TestSha {
    public static void main(String[] args) {
        String str = "admin123";
        System.out.println(User.sha(str));
        System.out.println(User.sha(str).length());
    }
}
