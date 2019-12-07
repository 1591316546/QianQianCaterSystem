package com.qiang.test;

import org.junit.Test;

import java.util.UUID;

/**
 * @author QIANG
 */
public class TestUUID {
    @Test
    public void testUUID(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        System.out.println(uuid.toString());
        System.out.println(uuid.toString().length());
    }
}
