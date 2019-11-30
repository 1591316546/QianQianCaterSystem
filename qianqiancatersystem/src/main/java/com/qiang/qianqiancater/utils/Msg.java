package com.qiang.qianqiancater.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息处理的类，方便向客户端回传数据
 * 状态码 code ：
 *  100成功
 *  200失败
 * 出错消息 msg
 * 附加数据 extend：
 *  不同的键值对，附加可变数据。
 * @author QIANG
 */
public class Msg {
    private Integer code;
    private String msg;
    private Map<String,Object> extend = new HashMap<>();

    public static Msg success(){
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("成功");
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("失败");
        return result;
    }

    public Msg add(String key,Object value){
        this.getExtend().put(key,value);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
