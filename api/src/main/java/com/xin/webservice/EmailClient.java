package com.xin.webservice;

import com.zhenhr.tools.PropertiesUtils;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 发送邮件
 *
 * @author guoyongshi
 */
public class EmailClient {

    /**
     * 发送邮件
     *
     * @param subject
     * @param htmlMsg
     * @param receiver
     * @throws Exception
     */
    public static void sendMail(EmailSendAccountVO account, String subject, String htmlMsg,
                                String receiver) throws Exception {
        List<String> receivers = new ArrayList<String>();
        receivers.add(receiver);
        sendMail(account, subject, htmlMsg, receivers, null);
    }

    // 需要抄送
    public static void sendMail(EmailSendAccountVO account, String subject, String htmlMsg,
                                String receiver, String emailcc) throws Exception {
        List<String> receivers = new ArrayList<String>();
        receivers.add(receiver);
        sendMail(account, subject, htmlMsg, receivers, emailcc);
    }

    public static void sendMail(EmailSendAccountVO account, String subject, String htmlMsg,
                                List<String> receivers) throws Exception {
        sendMail(account, subject, htmlMsg, receivers, null);
    }

    public static void sendMail(EmailSendAccountVO account, String subject, String htmlMsg,
                                List<String> receivers, String emailcc) throws Exception {

        Properties props = new Properties();
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", account.getSmtp());

        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        // 设置环境信息
        Session session = Session.getInstance(props,
                new MyAuthenticator(account.getEmail(), account.getPwd()));

        // 创建邮件对象
        MimeMessage msg = new MimeMessage(session);

        msg.setSubject(subject);
        msg.setContent(htmlMsg, "text/html;charset=utf-8"); // 设置邮件格式
        // 设置发件人
        if (account.getShowName() != null) {
            msg.setFrom(new InternetAddress(account.getEmail(), account.getShowName(),
                    "utf-8"));
        }

        for (int i = 0; i < receivers.size(); i++) {
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(receivers.get(i)));
        }

        if (emailcc != null) {
            String[] split = emailcc.split(";");
            for (String cc : split) {
                if (cc.length() > 0) {
                    msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
                }
            }
        }

        // 发送邮件
        Transport.send(msg);
        // 关闭连接
    }


    private static class MyAuthenticator extends Authenticator {
        String userName = null;
        String password = null;

        public MyAuthenticator(String username, String password) {
            this.userName = username;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    }

    public static void main(String[] args) {
        EmailClient client = new EmailClient();
        List<String> receivers = new ArrayList<String>();
        receivers.add("yanhui.bai@zhenhr.com");
        receivers.add("guoyong.shi@zhenhr.com");
        try {
            EmailSendAccountVO account = new EmailSendAccountVO();
            client.sendMail(account, "测试", "<p>这是一个测试1</p><p>这是一个测试2</p><p>这是一个测试3</p>",
                    receivers, null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
