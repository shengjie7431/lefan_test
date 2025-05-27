package com.lefancrm.apicenter.util.wechatPay.util;

/**
 * Created by user on 2015/3/11.
 */
public class WxPubPayConfig {
    //收款方
    public  static String spname = "乐凡公估";

    //商户号
    public  static  String partner = "1465987502";

    //密钥
    public  static  String partner_key = "217962";

    //appid
    public  static  String app_id="wx698f1593ad128635";

    public  static  String app_secret = "PaWqHEeiobWbF6Y81sO66qaW4OZkLX69";

    //appkey
    public  static  String app_key="PaWqHEeiobWbF6Y81sO66qaW4OZkLX69";

    //支付完成后的回调处理页面
    public  static  String notify_url ="wxPay.wxPayNotify";
    //调试模式
    public  static  boolean DEBUG_ = false;

    public  static  String  wxpaycertPath="/cert/apiclient_cert.p12";
}
