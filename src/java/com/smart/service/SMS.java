package com.smart.service;


import com.smart.utils.Md5Util;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * 网建短信平台 api
 * response code discription
 * -1	没有该用户账户
 * -2	接口密钥不正确
 * -21	MD5接口密钥加密不正确
 * -3	短信数量不足
 * -11	该用户被禁用
 * -14	短信内容出现非法字符
 * -4	手机号格式不正确
 * -41	手机号码为空
 * -42	短信内容为空
 * -51	短信签名格式不正确 接口签名格式为：【签名内容】
 * -6	IP限制
 * >0	短信发送数量
 */
public class SMS {

    private static final Logger logger = Logger.getLogger(SMS.class);

    private static final String URI = "http://utf8.api.smschinese.cn";
    private static final String HEADERNAME = "Content-Type";
    private static final String HEADERVALUE = "application/x-www-form-urlencoded;charset=utf-8";
    private static final String UID = "SMSqixian";
    private static final String MD5KEY = "311838C12438D924D61AC986793DA49D";

    public static boolean sendCaptchaCode(String mobile, String code) {

        NameValuePair[] data ={
                new NameValuePair("Uid", UID),
                new NameValuePair("KeyMD5", MD5KEY),
                new NameValuePair("smsMob", mobile),
                new NameValuePair("smsText","验证码：" + code)
        };

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(URI);
        post.addRequestHeader(HEADERNAME, HEADERVALUE);
        post.setRequestBody(data);

        try {
            //execute send message
            client.executeMethod(post);
            //get response result
            Header[] headers = post.getResponseHeaders();
            int statusCode = post.getStatusCode();
            String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
            System.out.println("statusCode:"+statusCode);
            logger.info("SMS send succeed: mobile [" + mobile + "] code [ "+ code + "] statusCode [" + statusCode + "] result [" + result +"]");
            if (statusCode == 200 && Integer.valueOf(result) > 0) {
                return true;
            }
        } catch (IOException e) {
            logger.error("SMS send faild: mobile [" + mobile + "] code [ "+ code + "]");
        } finally {
            post.releaseConnection();
        }
        return false;
    }

}