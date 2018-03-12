package com.smart.common;

/**
 * 全局配置常量
 * @author chauncy
 */
public class Constant {

    public static final String SECRETKEY = "vPZRcqu1C6BEjJV6cyw87ACJ";

    public static final long TOKEN_EXPIRE_TIME = 180 *24 * 60 * 60 * 1000;

    public static final int RESCODE_REQUEST_OK = 100;               //response ok

    public static final int RESCODE_OPERATE_SUCCEED = 200;          //operate succeed

    public static final int RESCODE_OPERATE_FAIL = 201;             //operate failed

    public static final int RECODE_USERNAME_USED = 301;             //username has been used

    public static final int RESCODE_MOBILE_HASREGISTERD = 302;      //mobile number has been registered

    public static final int RESCODE_CAPTCHACODE_ERROR = 303;        //captcha code error

    public static final int RESCODE_CAPTCHACODE_TIMEOUT = 304;      //captcha code has timeout

    public static final int RESCODE_USER_NOTEXIST = 305;            //user not exist

    public static final int RESCODE_PASSWORD_ERROR = 306;           //password error

    public static final int RESCODE_CHECKPARAM_ERROR = 400;         //illegal param

    public static final int RESCODE_REQUEST_ERROR = 500;            //request error

    public static final int RESCODE_TOKEN_ERROR = 700;              //token invalid

    public static final int RESCODE_TOKEN_EXPIRE = 701;             //token expired

}
