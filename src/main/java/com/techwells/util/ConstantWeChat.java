package com.techwells.util;

import jdk.internal.dynalink.beans.StaticClass;

public class ConstantWeChat {
    public static final String 	MCHID = "1407783002";   //商户号
    public static final String APPID = "wxb8542cb0f9990521";   //appId
    public static final String RedirectUri="http://www.emoonbow.com/aiyixue-server/wechat/receiveNotifyUrl.do";  //登录的回调地址
    public static final String SECRET = "40e860aabe090259a67ae3940cd0c1dd";  //秘钥
    public static final String KEY = "hengtianyidaqixiaqiangdiaoxitong";  //key
    public static final String NOTIFY_URL = "http://www.emoonbow.com/aiyixue-server/wechat/receiveNotifyUrl.do";//支付的回调地址
    public static final String TRADE_TYPE = "NATIVE";// 交易类型
    public static final String SUCCESS="SUCCESS";    //成功
    public static final String FAIL="FAIL";        //失败


    public static final String ACCESS_TOKEN = "你的企业号_ACCESS_TOKEN";
    public static final String encodingAESKey = "";
    public static final String LINE_URL = "http://www.twxztss.cc";
    public static final String grant_type = "authorization_code";
    public static final String MCH_ID = "1407783002";// 商户号
    public static final String PRIVATE_KEY = "hengtianyidaqixiaqiangdiaoxitong"; // 商户私钥

    public static final String BANK_TYPE = "WX";// 银行通道类型
    public static final String SIGN_TYPE = "MD5";// 签名方式
    public static final String PARTNER = "";// 商户号
    // private String partnerKey = "c513b82634eef322";// 商户密码
    public static final String PARTNER_KEY = "";// 商户密码
    public static final String FEE_TYPE = "1";// 现金支付币种

    public static final String INPUT_CHARSET = "UTF-8";// 字符编码


    public static final String PREPAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 统一支付接口地址
    public static final String WXREFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";// 微信退款申请接口
    // 订单查询接口(POST)
    public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    // 微信支付成功支付后跳转的地址
    public final static String SUCCESS_URL = "";
}
