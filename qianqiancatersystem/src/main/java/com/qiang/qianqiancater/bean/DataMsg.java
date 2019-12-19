package com.qiang.qianqiancater.bean;

import java.util.List;

/**
 * 对返回消息类的补充，专门返回表格数据，以期更方便
 * code 状态码 0 成功 -1 失败
 * msg 返回的提示信息
 * count 数据的总条数
 * data 返回的数据载荷
 * @author QIANG
 */
public class DataMsg<T> {
    private int code;
    private String msg;
    private int count;
    private List<T> data;

    public DataMsg(int code, String msg, int count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public static DataMsg success(String msg,int count, List<Object> data){
        return new DataMsg(0,msg,count,data);
    }

    public static <T> DataMsg success(int count, List<T> data){
        return new DataMsg(0,"",count,data);
    }

    public static <T> DataMsg success(String msg){
        return new DataMsg(0,msg,0,null);
    }

    public static DataMsg fail(String msg){
        return new DataMsg(-1,msg,0,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
