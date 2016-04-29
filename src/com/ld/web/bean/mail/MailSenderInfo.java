package com.ld.web.bean.mail;

import java.io.Serializable;
import java.util.Properties;

/**
 * 
 * <p>Title: MailSenderInfo</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description: 发送邮件需要使用的基本信息</p>
 *
 * @author LD
 *
 * @date 2015-03-27
 */
public class MailSenderInfo implements Serializable {

    private static final long serialVersionUID = 3134215115372555990L;

    private String serverHost; // 发送邮件的服务器的IP

    private String serverPort; // 发送邮件的服务器的端口

    private String fromAddress; // 邮件发送者的地址

    private String toAddress; // 邮件接收者的地址

    private String username; // 登陆邮件发送服务器的用户名

    private String password; // 登陆邮件发送服务器的密码

    private String subject; // 邮件主题

    private String content; // 邮件内容

    private String[] attachFileNames; // 邮件附件的文件名

    private boolean validate = false; // 是否需要身份验证

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }

    public boolean getValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public MailSenderInfo(String serverHost, String serverPort, String fromAddress, String toAddress, String username,
            String password, String subject, String content, String[] attachFileNames, boolean validate) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
        this.attachFileNames = attachFileNames;
        this.validate = validate;
    }

    public MailSenderInfo() {
    }

    /**
     * 获得邮件会话属性
     * 
     * @return
     */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", this.serverHost);
        p.put("mail.smtp.port", this.serverPort);
        p.put("mail.smtp.auth", this.validate ? "true" : "false");
        p.put("mail.transport.protocol", "smtp");
        return p;
    }
}
