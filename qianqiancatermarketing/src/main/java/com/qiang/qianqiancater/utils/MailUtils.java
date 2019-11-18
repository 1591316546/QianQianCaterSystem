package com.qiang.qianqiancater.utils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Properties;

/**
 * @author QIANG
 */
public class MailUtils {
    private final static String from = "qiangyanjun@yeah.net";
    private final static String password = "qianqiancater1";
    /* 默认配置 */
    private static String host = "smtp.yeah.net";

    /**
     * 发送邮件
     * @param host  主机名,一般是stmp.xxx.com
     * @param from  发送者邮箱帐号
     * @param password 发送者邮箱密码,有些邮箱需要用授权码代替密码
     * @param subject 邮件标题
     * @param body 邮件内容,可以是html格式的
     * @param tos  收件人邮箱帐号,多个收件人用逗号隔开
     * @param files 附件，多个附件用逗号隔开,附件地址要物理路径
     * @return 发送过程不出异常则返回true,否则返回false
     */
    public static boolean sendEmail(String host,final String from,final String password,String subject, String body, String tos, String[] files) {
        // 1.设置配置信息
        Properties prop = new Properties();
        // 主机名
        prop.put("mail.smtp.host", host);
        // 身份验证
        prop.put("mail.smtp.auth", "true");

        // 2.创建邮件会话实例(需要验证用户名与密码)
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // 3. 根据会话实例创建邮件信息对象
            MimeMessage message = new MimeMessage(session);

            // 4.设置发件人
            message.setFrom(new InternetAddress(from));

            // 5.设置收件人(这里是设置多个收件人，tos用逗号隔开)
            // TO(主送地址)、CC(抄送地址)、BCC(秘密抄送)
            InternetAddress[] internetAddressTo = InternetAddress.parse(tos);
            message.setRecipients(Message.RecipientType.TO, internetAddressTo);

            // 6.设置邮件发送日期(默认当前立即发送)
            message.setSentDate(new Date());

            // 7.设置邮件主题
            message.setSubject(subject);

            // 8.设置邮件主体内容(包括html文本和附件)
            MimeMultipart mm = new MimeMultipart();
            // 设置网页内容部分
            MimeBodyPart html = new MimeBodyPart();
            html.setContent(body, "text/html;charset=UTF-8");
            mm.addBodyPart(html);

            // 设置多个附件
            if(files!=null&&files.length>0) {

                for (String f : files) {

                    // 设置附件部分
                    MimeBodyPart attachment = new MimeBodyPart();
                    // 读取文件
                    DataHandler dh = new DataHandler(new FileDataSource(f));
                    // 将文件关联到附件上
                    attachment.setDataHandler(dh);
                    // 设置附件的文件名（需要编码避免中文乱码）
                    attachment.setFileName(MimeUtility.encodeText(dh.getName()));
                    mm.addBodyPart(attachment);
                }
            }
            message.setContent(mm);

            // 9.创建smtp协议的邮箱传输对象
            Transport tran = session.getTransport("smtp");
            // 把邮件信息发送给所有指定的收件人
            tran.send(message, message.getAllRecipients());
            // 关闭传输对象
            tran.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 发送邮件
     * 发件人信息使用默认配置
     * @param subject 邮件标题
     * @param body 邮件内容,可以是html格式的
     * @param tos  收件人邮箱帐号,多个收件人用逗号隔开
     * @param files 附件，多个附件用逗号隔开,附件地址要物理路径
     * @return 发送过程不出异常则返回true,否则返回false
     */
    public static boolean sendEmail(String subject, String body, String tos, String[] files) {
        return sendEmail(host, from, password, subject, body, tos, files);
    }

    /**
     * 发送邮件
     * @param host  主机名,一般是stmp.xxx.com
     * @param from  发送者邮箱帐号
     * @param password 发送者邮箱密码,有些邮箱需要用授权码代替密码
     * @param subject 邮件标题
     * @param body 邮件内容,可以是html格式的
     * @param tos  收件人邮箱帐号,多个收件人用逗号隔开
     * @param files 附件，多个附件用逗号隔开,附件地址使用的是相对于网站根目录的路径
     * @param request  Request请求对象，用于获取附件的物理路径
     * @return 发送过程不出异常则返回true,否则返回false
     */
    public static boolean sendEmail(String host,final String from,final String password,String subject, String body, String tos, String files,HttpServletRequest request) {
        ServletContext context= request.getServletContext();

        String[] paths=null;
        if(files!=null&&files.length()>0) {
            String[] fileArr = files.split(",");
            paths=new String[fileArr.length];
            for (int i=0;i<fileArr.length;i++) {
                paths[i]=context.getRealPath(fileArr[i]);
            }
        }
        return sendEmail(host, from, password, subject, body, tos, paths);
    }

    /**
     * 发送邮件
     * 发件人信息使用默认配置
     * @param subject 邮件标题
     * @param body 邮件内容,可以是html格式的
     * @param tos  收件人邮箱帐号,多个收件人用逗号隔开
     * @param files 附件，多个附件用逗号隔开,附件地址使用的是相对于网站根目录的路径
     * @param request  Request请求对象，用于获取附件的物理路径
     * @return 发送过程不出异常则返回true,否则返回false
     */
    public static boolean sendEmail(String subject, String body, String tos, String files, HttpServletRequest request) {
        return sendEmail(host, from, password, subject, body, tos, files,request);
    }
}