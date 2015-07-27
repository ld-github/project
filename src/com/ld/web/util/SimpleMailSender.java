package com.ld.web.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.ld.web.bean.mail.MailSenderInfo;
import com.ld.web.bean.mail.MyAuthenticator;

/**
 * 
 * <p>Title: SimpleMailSender</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description: 发送邮件工具类</p>
 *
 * @author LD
 *
 * @date 2015-3-27
 */
public class SimpleMailSender implements Serializable {

    private static final long serialVersionUID = -9002158219804474539L;

    /**
     * 以文本格式发送邮件
     * 
     * @param mailInfo
     *            待发送的邮件信息
     * @return
     */
    public static void sendTextMail(MailSenderInfo mailInfo) throws Exception {
        MyAuthenticator authenticator = null;
        mailInfo = chooseServer(mailInfo);
        Properties pro = mailInfo.getProperties();
        // 如果需要身份认证，则创建一个密码验证器
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUsername(), mailInfo.getPassword());
        }
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        Message msg = new MimeMessage(sendMailSession);
        Address from = new InternetAddress(mailInfo.getFromAddress());
        Address to = new InternetAddress(mailInfo.getToAddress());
        msg.setFrom(from);
        msg.setRecipient(Message.RecipientType.TO, to);
        msg.setSubject(mailInfo.getSubject());
        msg.setSentDate(new Date());
        msg.setText(mailInfo.getContent());
        Transport.send(msg);
    }

    /**
     * 以HTML格式发送邮件
     * 
     * @param mailInfo
     *            待发送的邮件信息
     * @return
     */
    public static void sendHtmlMail(MailSenderInfo mailInfo) throws Exception {
        MyAuthenticator authenticator = null;
        mailInfo = chooseServer(mailInfo);
        Properties pro = mailInfo.getProperties();
        // 如果需要身份认证，则创建一个密码验证器
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUsername(), mailInfo.getPassword());
        }
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        Message msg = new MimeMessage(sendMailSession);
        Address from = new InternetAddress(mailInfo.getFromAddress());
        Address to = new InternetAddress(mailInfo.getToAddress());
        Multipart mainPart = new MimeMultipart();
        BodyPart html = new MimeBodyPart();
        msg.setFrom(from);
        msg.setRecipient(Message.RecipientType.TO, to);
        msg.setSubject(mailInfo.getSubject());
        msg.setSentDate(new Date());
        html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        msg.setContent(mainPart);
        Transport.send(msg);
    }

    /**
     * 根据用户邮箱账号选择邮箱服务器和端口号
     * 
     * @param username
     * @return
     */
    public static MailSenderInfo chooseServer(MailSenderInfo mailInfo) {
        String username = mailInfo.getUsername();
        String identification = username.substring(username.lastIndexOf("@"), username.length());
        if (!SERVERS_INFOS.containsKey(identification)) {
            return mailInfo;
        }
        String[] serverInfo = SERVERS_INFOS.get(identification);
        mailInfo.setServerHost(serverInfo[0]);
        mailInfo.setServerPort(serverInfo[1]);
        return mailInfo;
    }

    /**
     * 邮箱服务器信息
     */
    public static final Map<String, String[]> SERVERS_INFOS = new HashMap<String, String[]>() {
        private static final long serialVersionUID = 457859374055559467L;
        {
            put("@qq.com", new String[] { "smtp.qq.com", "25" });
            put("@sina.com", new String[] { "smtp.sina.com", "25" });
            put("@163.com", new String[] { "smtp.163.com", "25" });
        }
    };
}
