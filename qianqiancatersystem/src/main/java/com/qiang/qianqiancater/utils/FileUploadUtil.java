package com.qiang.qianqiancater.utils;

import java.util.UUID;

/**
 * @author QIANG
 */
public class FileUploadUtil {
    /**
     * 获取文件名，截断路径
     * @param name
     * @return
     */
    public static String getRealName(String name){
        int index = name.lastIndexOf("\\");
        return name.substring(index+1);
    }

    public static String getUUIDName(String realName){
        int index = realName.lastIndexOf(".");
        if (index == -1){
            //没有后缀名
            return UUID.randomUUID().toString().replace("-","").toUpperCase();
        }else {
            //有后缀名加上原来的后缀名
            return UUID.randomUUID().toString().replace("-","").toUpperCase()
                    +realName.substring(index);
        }
    }
}
