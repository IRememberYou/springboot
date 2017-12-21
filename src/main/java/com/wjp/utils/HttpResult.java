package com.wjp.utils;

/**
 * Created by pinan on 2017/12/13.
 */
public class HttpResult<T> {
    private T data;
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public HttpResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public HttpResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public HttpResult setData(T data) {
        this.data = data;
        return this;
    }
}
