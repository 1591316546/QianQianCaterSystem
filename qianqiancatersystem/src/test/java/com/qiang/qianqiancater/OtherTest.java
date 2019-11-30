package com.qiang.qianqiancater;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * @author QIANG
 */
public class OtherTest {
    @Test
    public void testOther(){
        //生成激活码
        String s = UUID.randomUUID().toString();
        try {
            String addr = InetAddress.getLocalHost().getHostAddress();//获得本机IP
            System.out.println(addr);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
