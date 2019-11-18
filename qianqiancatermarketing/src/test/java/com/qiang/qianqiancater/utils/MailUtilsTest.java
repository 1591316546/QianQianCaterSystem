package com.qiang.qianqiancater.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author QIANG
 */
public class MailUtilsTest {

    @Test
    public void sendEmail() {
        boolean b = MailUtils.sendEmail("第一封测试邮件", "这是测试内容", "1441988149@qq.com", null);
        System.out.println(b);
    }
}