package com.agricluture.http.https;


/**
 * Created by kk on 2018/2/2.
 */

public class HttpResultBean<T> {

    private String state;
    private String msg;
    private T data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
