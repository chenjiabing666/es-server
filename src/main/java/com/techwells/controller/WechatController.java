package com.techwells.controller;

import com.github.wxpay.sdk.WXPayUtil;
import com.techwells.service.UserService;
import com.techwells.util.HttpUtils;
import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.html.HTMLTableCaptionElement;
import com.techwells.util.ConstantWeChat;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WechatController {
    @Resource
    private UserService userService;

    /**
     * 微信第三方登录
     * @param request
     * @return
     */
    @RequestMapping("/wechat/login")
    public void login(HttpServletRequest request, HttpServletResponse response){
        /**
         * 请求，获取code
         * 1、appid ： 需要指定
         * 2、redirect_uri：回调的url
         * 3、scope： 指定域，网页授权作用域为snsapi_login
         * 4、state： 一般是随机数，可以不指定
         */
        String url="https://open.weixin.qq.com/connect/qrconnect?" +
                "appid="+ConstantWeChat.APPID +
                "&redirect_uri="+URLEncoder.encode(ConstantWeChat.RedirectUri)+
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=STATE#wechat_redirect";
//        String url="http://www.baidu.com";
        try {
            response.sendRedirect(url);   //重定向请求
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 回调函数，用于获取code，进一步的请求，获取用户信息等
     * @param request
     * @return
     */
    @RequestMapping("/wechat/callback")
    public Object callback(HttpServletRequest request){
        String code=request.getParameter("code");  //获取code
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid="+ConstantWeChat.APPID +
                "&secret="+ConstantWeChat.SECRET +
                "&code="+code +
                "&grant_type=authorization_code";

        //请求这个url，获取json
        String accessToken=null;
        String openid=null;
        try {
            Map<String,Object> result=HttpUtils.doGet(url);
            openid= (String) result.get("openid");//获取opendid，用户唯一标识
            accessToken= (String) result.get("access_token"); //调用接口的凭证
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取用户信息
        String urlInfo="https://api.weixin.qq.com/sns/userinfo?" +
                "access_token="+accessToken +
                "&openid="+openid+
                "&lang=zh_CN";

        //请求
        try {
            Map<String ,Object> userInfo=HttpUtils.doGet(urlInfo);
            return userInfo;  //直接返回userInfo,或者可以保存在数据库中
        } catch (Exception e) {
            e.printStackTrace();
        }




        return  null;
    }


    @RequestMapping("/wechat/pay")
    public void pay(HttpServletRequest request){
        String body=request.getParameter("body");
        String tradeNo=request.getParameter("tradeNo");  //商户订单号
        String totalFee=request.getParameter("totalFee");  //总金额 单位为分
        String productId=request.getParameter("productId");  //商品Id
        Map<String,String> map=new HashMap<>();  //使用map封装请求参数
        map.put("appid",ConstantWeChat.APPID);  //appId
        map.put("mch_id",ConstantWeChat.MCH_ID);  //商户号
        map.put("nonce_str",WXPayUtil.generateNonceStr());  //随机字符串，使用工具类生成即可
        map.put("body",body);  //商品描述
        map.put("out_trade_no",tradeNo); //商户订单号
        map.put("total_fee",totalFee);  //订单总金额，单位为分
        map.put("notify_url",ConstantWeChat.NOTIFY_URL);  //用户扫码支付的回调地址，必须是外网能够真实访问的
        map.put("trade_type",ConstantWeChat.TRADE_TYPE);   //交易类型，扫码支付或者其他的支付方式
        map.put("product_id",productId);  //商品Id

        String responseDataXml=null; //返回结果
        Map<String,String> responseMap=new HashMap<>();  //转换后的Map集合
        try {
            map.put("spbill_create_ip",InetAddress.getLocalHost().getHostAddress());  //终端IP
            String sign=WXPayUtil.generateSignature(map,ConstantWeChat.KEY);  //自动生成签名
            map.put("sign",sign);  //签名
            String requestDataXml=WXPayUtil.mapToXml(map);  //转换成xml格式，发出请求
            //调用下单的url，并且传入参数
            responseDataXml=HttpUtils.doPost("https://api.mch.weixin.qq.com/pay/unifiedorder",requestDataXml);  //发出请求，响应的xml格式的结果
            //将xml转换成Map集合
           responseMap=WXPayUtil.xmlToMap(responseDataXml);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //获取return_code判断通信是否成功
        String returnCode=responseMap.get("return_code");
        //如果通信成功
        if (ConstantWeChat.SUCCESS.equals(returnCode)){
            //获取result_code
            String resultCode=responseMap.get("result_code");
            //如果业务结果成功，那么可以获取返回的二维码的url
            if (ConstantWeChat.SUCCESS.equals(resultCode)){
                String codeUrl=responseMap.get("code_url");  //获取url
                //使用codeUrl生成二维码图片，用户扫描二维码....扫描之后将会调用回调的url


            }else{
                    //响应失败结果集
            }

        }else{
            //响应失败结果集
        }
    }

    /**
     * 用户扫描二维码之后的回调方法
     */
    @RequestMapping("/wechat/payCallback")
    public void payCallback(){

    }





}
