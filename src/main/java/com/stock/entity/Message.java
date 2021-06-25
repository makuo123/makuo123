package com.stock.entity;

/**
 * @Author mk
 * @Date 2020/10/22 10:11
 * @Version 1.0
 */
public class Message<T> {
    private boolean flag;
    private String msg;
    private T data;

    public Message() {
    }

    public Message(boolean flag, String msg, T data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
