package com.smart.struct;

public class ResultMgr {

    public static CommonResult loginSuccess(String token) {
        return new CommonResult(Constant.RESCODE_REQUEST_SUCCESS, null, "登录成功", token);
    }

    public static CommonResult success() {
        return new CommonResult(Constant.RESCODE_REQUEST_SUCCESS, "请求成功");
    }

    public static CommonResult success(String msg) {
        return new CommonResult(Constant.RESCODE_REQUEST_SUCCESS, msg);
    }

    public static CommonResult success(Object obj) {
        return new CommonResult(Constant.RESCODE_REQUEST_SUCCESS, "请求成功", obj);
    }

    public static CommonResult success(String msg, Object obj) {
        return new CommonResult(Constant.RESCODE_REQUEST_SUCCESS, msg, obj);
    }

    public static CommonResult paramError() {
        return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "参数错误");
    }

    public static CommonResult error() {
        return new CommonResult(Constant.RESCODE_REQUEST_ERROR, "请求失败");
    }

    public static CommonResult tokenTimeout() {
        return new CommonResult(Constant.RESCODE_TOKEN_EXPIRE, "token失效");
    }

    public static CommonResult tokenError() {
        return new CommonResult(Constant.RESCODE_TOKEN_ERROR, "token错误");
    }
}
