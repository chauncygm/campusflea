package com.smart.struct;

public class CommonResult {

    /**
     * response code
     */
    private int code;

    /**
     * response message
     */
    private String msg;

    /**
     * response data
     */
    private Object data;

    /**
     * auth token
     */
    private String token;

    public CommonResult() {}

    public CommonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResult(int code, String msg, Object data, String token) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.token = token;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", token='" + token + '\'' +
                '}';
    }
}
