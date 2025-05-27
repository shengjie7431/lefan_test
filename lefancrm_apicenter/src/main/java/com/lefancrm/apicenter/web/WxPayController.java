package com.lefancrm.apicenter.web;

import com.lefancrm.apicenter.util.wechatPay.ResponseHandler;
import com.lefancrm.apicenter.util.wechatPay.util.WxPayConfig;
import com.lefancrm.apicenter.util.wechatPay.util.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 2017/4/26.
 */
@Controller
@RequestMapping(value = "/")
public class WxPayController {




    @RequestMapping(value = "/wxPay/wxPayNotify")
    public void index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("**********************callback**************callback******************************");
        try {
            //创建支付应答对象
            ResponseHandler resHandler = new ResponseHandler(request, response);
            resHandler.setKey(WxPayConfig.partner_key);
            //---------------------------------------------------------
            //微信支付通知（后台通知）示例，商户按照此文档进行开发即可
            //---------------------------------------------------------
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            System.out.println("~~~~~~~~~~~~~~~~付款成功~~~~~~~~~");
            outSteam.close();
            inStream.close();
            String result  = new String(outSteam.toByteArray(),"utf-8");
            Map<Object, Object> map = XMLUtil.doXMLParse(result);
            for(Object keyValue : map.keySet()){
                System.out.println("***************** wechat pay scuess:"+keyValue+"="+map.get(keyValue));
            }


            if("SUCCESS".equals(map.get("result_code"))){
                String outTradeNo = map.get("out_trade_no").toString();

            }

//            String appid = map.get("appid").toString();
//            String feetype = map.get("fee_type").toString();
//            String nonceStr = map.get("nonce_str").toString();
//            final String outTradeNo = map.get("out_trade_no").toString();
//            String tradeType = map.get("trade_type").toString();
//            String sign = map.get("sign").toString();
//            String totalFee = map.get("total_fee").toString();
//            String openid = map.get("openid").toString();
//
//            Map<String,Object> hashMap = new HashMap<String, Object>();
//            hashMap.put("wxpayCode",outTradeNo.toString());
//
//
//
//            System.out.println("*****************777777777777777777:");
        }catch (Exception ex){
            System.out.println("ex"+ex);
        }
    }
}
