package com.lefancrm.apicenter.util.suning;

import com.lefancrm.apicenter.util.suning.dto.CardInfoDto;
import com.lefancrm.apicenter.util.suning.dto.PaymentOrderDto;
import com.lefancrm.apicenter.util.suning.util.CryptoUtil;
import com.lefancrm.apicenter.util.suning.util.Digest;
import com.lefancrm.apicenter.util.suning.util.HttpClientUtil;
import com.lefancrm.apicenter.util.suning.util.JSONUtil;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author 16060823
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PaymentOrderMain {
    /**
     * 签名私钥
     */
    private static final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAg"
        + "EAAoGBALb_y4p_Fj3yyaukPXU43h1ZZ3f3MVG2JT5FIxRacHxWarboPPviL4nkZPbU7YNQ8YrAdT"
        + "zfsv8Est6jOxVXwcTplRcnWKe86j_fVH2LwOLjVcT2YMuCq8vk6wqNFrm5FpHs8MToibbrLay0Mh"
        + "PVMDv_QEHL1FSb3jIp2Y185MSXAgMBAAECgYEAq9Dm7hX8cBVwO_NssI7AHJZAi2ZPrAR0W07WbN"
        + "mtG1CXyTurZmMrdzA1i3rHta9d2YRxjsIJcV6cAmUHqDZI-l5SaGUNI5W4uq5tFVenvgNEvYQsRz"
        + "JWPfYtkFCgXG38CNu-v8G3lEbptxUa8jfBhYDYsqyAFuktY8eILfmqaOECQQDZ8brK_ANYVKeXy2"
        + "eeYz8uikr_JCpTRbwc4_BPOuGQ604GhY3CqvHh5heJbZUnYjJRZh-P7QkJ67cEqQe2E_yVAkEA1v"
        + "P-0dzlv_I83q1ECdXV_C0tRtwxfdNDeAhoJjD-7CPqFeH5FsMi1d1u7eI0A0cmtYgKv1yfOQ_3Wv"
        + "4TPY2FewJAFjiZTPzo8mafN9DbXcDMvlgHUYBEeH0RsvhilhFw0i_LosqXK5P2WYv2NmLf5EYUz0"
        + "vUR_5o_4PzAxyg1qWvCQJBANSK2rJkfAC545pDNP2MpVP-z8A7ReymkxYDKghpdgfE01fj40qwlK"
        + "dnlqI9gzSijBH_fLEPQplHwkwE4r2ATDUCQQDMJmDsuubnHNUADCDin6NR9cyQRK1Y1QNuhK6xU_"
            + "vEUTRMY9BkHJGXo1hQHirH5sr6Y8K3zx7cYgbKiyUG1IgX";
    /**
     * 访问地址 SIT 
     */
   static   String url ="http://ebankpaysit.cnsuning.com/epps-ebpg/singleWithhold/paymentOrder.htm";
  // static String url ="https://ebanksandbox.suning.com/epps-ebpg/singlewithhold/paymentOrder.htm";
     
    public static void main(String[] args) {

        try {
            System.out.println("1.构造参数开始....");;
            //1.构造参数
            PaymentOrderDto dto = buildPaymentOrderDto();
            
            CardInfoDto buildCardInfo = buildCardInfo();
            // 2.cardInfo 加密
            String jsonStr = JSONUtil.toJSONString(buildCardInfo);
            String cryptoStr = CryptoUtil.encryptJson(jsonStr, "F:/suning/yifubao-pre.cer");
            dto.setCardInfo(cryptoStr);
            System.out.println("加密字符串"+jsonStr);
            
            System.out.println("加密结果"+cryptoStr);
            // 3.签名
            Map<String, String> map = objectToMap(dto);
             //将这些数据按字典顺序（从a到z，首字母相同则看第二个字母）排序并拼接  字符串
            String digestStr = Digest.digest(map, "signature", "signAlgorithm");
            System.out.println("签名前:"+digestStr);
            
            String signature = CryptoUtil.sign(digestStr, CryptoUtil.getPrivateKey(PRIVATE_KEY));
            
            dto.setSignature(signature);
            System.out.println("请求环境 参数"+map);
            map = objectToMap(dto);
            String responseStr = HttpClientUtil.post(url, map, false);
            System.out.println("结果响应"+responseStr);
            
        } catch (Exception e) {
            System.out.println("错误内容"+e);
        }

    }

    private static CardInfoDto buildCardInfo() {
        // 取默认的信息
        return new CardInfoDto();
    }

    private static PaymentOrderDto buildPaymentOrderDto() {
        System.out.println("支付订单构建开始....");
        PaymentOrderDto dto = new PaymentOrderDto();
        dto.setMerchantNo("70056371");
        dto.setPublicKeyIndex("0001");
        dto.setVersion("1.0");
        dto.setSignature("");
        dto.setSignAlgorithm("RSA");
        dto.setInputCharset("UTF-8");
        dto.setSubmitTime(getNum());
        dto.setBankCode("TEST_KJ");
        dto.setCardType("1");
        //dto.setCardInfo("加密");  
        dto.setOutOrderNo(getNum()+"001"); //
        dto.setOrderType("01");
        dto.setOrderAmount("100");
        dto.setCurrency("CNY");
        dto.setOrderTime(getNum());
        dto.setSalerMerchantNo("");
        dto.setGoodsType("011001");
        dto.setGoodsName("6LSd5bCU6YeR5oqk6IWV5byPIA==");
        dto.setPayTimeout("7d");
        dto.setRoyaltyParameters("NzAwNTY1NzVeMTBe5YiG5L2g55qEfDcwMDU2NTc1XjIwXuS9oOS5n+aciQ=="); // 70056575^10^分你的|70056575^20^你也有
        dto.setTunnelData("eyJ0dW5uZWxEYXRhIjoidGVzdERhdGEifQ==");// {"tunnelData":"testData"}
        dto.setRemark("6YCP5Lyg57uZ5ZWG5oi355qE5YaF5a65");// 透传给商户的内容
        System.out.println("支付订单构建结束...");
        return dto;
    }
    
    
    public static String  getNum()
    {
        SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
    
    
    

    private static Map<String, String> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object fieldObj =field.get(obj);
            String fieldStr="";
            if(null!=fieldObj)
            {
                fieldStr =fieldObj.toString();
            }
            map.put(field.getName(), fieldStr);
        }

        return map;
    }

}
