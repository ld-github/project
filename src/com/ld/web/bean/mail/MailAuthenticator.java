package com.ld.web.bean.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * <p>Title: MailAuthenticator</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description: 密码验证器</p>
 *
 * @author LD
 *
 * @date 2015-03-27
 */
public class MailAuthenticator extends Authenticator {

    private String username; // 用户名

    private String password; // 密码

    public MailAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}
