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
    public void sendMail(EmailSendAccountVO account, String subject, String htmlMsg,
                         String receiver) throws Exception {
        List<String> receivers = new ArrayList<String>();
        receivers.add(receiver);
        this.sendMail(account, subject, htmlMsg, receivers, null);
    }

    // 需要抄送
    public void sendMail(EmailSendAccountVO account, String subject, String htmlMsg,
                         String receiver, String emailcc) throws Exception {
        List<String> receivers = new ArrayList<String>();
        receivers.add(receiver);
        this.sendMail(account, subject, htmlMsg, receivers, emailcc);
    }

    public void sendMail(EmailSendAccountVO account, String subject, String htmlMsg,
                         List<String> receivers) throws Exception {
        this.sendMail(account, subject, htmlMsg, receivers, null);
    }

    public void sendMail(EmailSendAccountVO account, String subject, String htmlMsg,
                         List<String> receivers, String emailcc) throws Exception {
        if (account == null) {
            account = this.getSystemSendAccount();
        }

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

    // 需要发送附件的邮件
    public void backsendMail(EmailSendAccountVO account, String subject, String htmlMsg,
                             String receiver, String attachment, String offername, String companyName,
                             String emailcc) throws Exception {
        List<String> receivers = new ArrayList<String>();
        receivers.add(receiver);
        this.backsendMail(account, subject, htmlMsg, receivers, attachment, offername,
                companyName, emailcc);
    }

    // 后台发送需要附件邮件
    public void backsendMail(EmailSendAccountVO account, String subject, String htmlMsg,
                             List<String> receivers, String attachment, String offername,
                             String companyName, String emailcc) throws Exception {
        if (account == null) {
            account = this.getSystemSendAccount();
        }

        Properties props = new Properties();
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", account.getSmtp());
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(props,
                new MyAuthenticator(account.getEmail(), account.getPwd()));

        // 创建邮件对象
        MimeMessage msg = new MimeMessage(session);

        // ===========================================================
        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();

        // 设置邮件的文本内容
        BodyPart contentPart = new MimeBodyPart();
        // 设置文本内容 设置文本格式
        contentPart.setContent(htmlMsg, "text/html;charset=GB2312");
        multipart.addBodyPart(contentPart);
        // 添加附件
        BodyPart messageBodyPart = new MimeBodyPart();
        // 设置附件的数据源
        // DataSource source = new FileDataSource(new File(attachment));
        // 添加附件的内容
        messageBodyPart
                .setDataHandler(new DataHandler(attachment, "text/html;charset=GB2312"));
        // 添加附件的标题
        // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码

        sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
        String filename = companyName + "_录用通知书.html";
        String text = MimeUtility.encodeText(filename);// 转码
        // messageBodyPart.setFileName("=?GBK?B?" +
        // enc.encode(filename.getBytes()) + "?=");
        messageBodyPart.setFileName(text);
        multipart.addBodyPart(messageBodyPart);

        msg.setContent(multipart, "text/html;charset=utf-8");
        // ===========================================================
        msg.setSubject(subject);
        // msg.setContent(htmlMsg, "text/html;charset=utf-8"); // 设置邮件格式
        // 设置发件人
        if (account.getShowName() != null) {
            msg.setFrom(new InternetAddress(account.getEmail(), account.getShowName(),
                    "utf-8"));
        }

        for (int i = 0; i < receivers.size(); i++) {
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(receivers.get(i)));
        }
        // 发送邮件 抄送人
        if (emailcc != null && emailcc.length() > 0) {
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

    private EmailSendAccountVO getSystemSendAccount() {
        EmailSendAccountVO vo = new EmailSendAccountVO();

        Properties emailProp = PropertiesUtils.getProperties("email");
        String account = emailProp.getProperty("account");
        String pwd = emailProp.getProperty("pwd");
        String showName = emailProp.getProperty("showName");

        vo.setEmail(account);
        vo.setPwd(pwd);
        vo.setShowName(showName);
        vo.setSmtp("smtp.exmail.qq.com");
        return vo;
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
