package com.smart.struct;

public class CommonResult {

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 返回token
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

    public CommonResult(int code, Object data, String msg, String token) {
        this.code = code;
        this.data = data;
        this.msg = msg;
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
