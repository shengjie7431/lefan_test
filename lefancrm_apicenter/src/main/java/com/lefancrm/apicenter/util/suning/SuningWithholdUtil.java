package com.lefancrm.apicenter.util.suning;

import com.lefancrm.apicenter.util.suning.dto.CardInfoDto;
import com.lefancrm.apicenter.util.suning.dto.PaymentOrderDto;
import com.lefancrm.apicenter.util.suning.util.CryptoUtil;
import com.lefancrm.apicenter.util.suning.util.Digest;
import com.lefancrm.apicenter.util.suning.util.HttpClientUtil;
import com.lefancrm.apicenter.util.suning.util.JSONUtil;
import org.apache.commons.codec.binary.Base64;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SuningWithholdUtil {
    /**
     * 签名私钥
     */
    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMBCrSpa/41WFB2o" +
            "YlRpQvfcCSDt4hb+8MnU0uqIeQ2WxCTdimwOHfH2wNpee13KTtUT3tdb3rC2IFE+" +
            "/zYblg9NO6onrUmm7siSKdzq9D3Ek3UmO1rJ8gNYnRDtkoGkXRB81R3HOqwAsCDo" +
            "Pih5U2OY0CTsF6uwoEN/Bu1F4B89AgMBAAECgYAsrdIMK8WDlONr2Puw3h1f+FRu" +
            "wJlv+qL+ZGT3d+uZytWeM5W9crJmzo8WsCo/K4qSXeHFbmYb7tcnyloIuvRuBow6" +
            "fdNBf6ptFxsC1N7WbP7ZljvLJCg1gb7rjViPPaz9cDEr3b+eYJPtnHNBbMIq/Vrt" +
            "0Q3fx8y6r/VnPuTAiQJBAO2FbO+1YwbiOmi7UA6dsQefeXXmgcE0Obx/3jLaqqIC" +
            "iN951w1LzX7png0rncxG9D3ifBNlwPztfUYEwYN1HNMCQQDPN9GKKxQg1x+zkOAI" +
            "hHvMKRbvRkSlT4bP2EJ93jAgTaKoPQ2G4E9+RietgXL5owwW9FRuq2GDUEymIWpR" +
            "OgmvAkBIJrPEzVDbknUzw1K6XeSc8DCxQ+g+jGLNg/o3cH1M4YA6goR1IYW2+7hr" +
            "P8ibeSJQejA+pDZPnsTjNCakjDrJAkEAip8qvCWpZ3bCLFvko44NSzzJrPPzBCu5" +
            "Yd2oTY+P3mxRPf7px6rTQwQnkvigM8QRWGHHAeoAZ9oWDKUZ/JC43QJBAIYg5tHG" +
            "Ujs44DoAzFvdiIjp0vDL82GFBRk7xOl8gZx0v6t49aYHIjwHtTjcf68/0zEyET19" +
            "1OmUWGpp/pdEn3E=";

    /**
     * 下单支付 测试URL接口
     */
    static String url ="https://ebanksandbox.suning.com:10018/epps-ebpg/singleWithhold/paymentOrder.do";


    public static void main(String[] args){
        try {
            System.out.println(new Date().getTime());

            //1.构造支付订单 以及 卡信息 参数
            PaymentOrderDto paymentOrderDto = createPaymentOrderDto();
            CardInfoDto cardInfoDto = createCardInfoDto();
            System.out.println("构造数据完成");
            //2.加密卡信息
            String cardInfoJson = JSONUtil.toJSONString(cardInfoDto);
            String cryptoCardInfo = CryptoUtil.encryptJson(cardInfoJson,"F:/suning/yifubao-pre.cer");
            paymentOrderDto.setCardInfo(cryptoCardInfo);
            System.out.println("加密卡信息完成");
            //2.签名
            Map<String,String> map = objectToMap(paymentOrderDto);
            String digest = Digest.digest(map,"signature","signAlgorithm");
            String signature = CryptoUtil.sign(digest,CryptoUtil.getPrivateKey(PRIVATE_KEY));
            paymentOrderDto.setSignature(signature);
            System.out.println("签名完成");
            map = objectToMap(paymentOrderDto);
            String response = HttpClientUtil.post(url,map,false);
            System.out.println("返回响应内容：" + response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static PaymentOrderDto createPaymentOrderDto(){
        try {
            PaymentOrderDto dto = new PaymentOrderDto();
            dto.setMerchantNo("70057000");//交易发起方商户号，易付宝提供
            dto.setPublicKeyIndex("0001");
            dto.setVersion("2.0");
            dto.setSignature("");//签名
            dto.setSignAlgorithm("RSA");
            dto.setInputCharset("UTF-8");
            dto.setSubmitTime(getNum());
            dto.setBankCode(getBankCodeByName(""));
            dto.setCardType("1");
            dto.setCardInfo("");//卡信息
            dto.setOutOrderNo(getNum().concat("0001"));
            dto.setOrderType("01");
            dto.setOrderAmount("5");
            dto.setOrderTime(getNum());
            dto.setCurrency("CNY");
            dto.setSalerMerchantNo("70057000");//卖家商户易付宝商户号
            dto.setGoodsType("012345");//商品类型 易付宝分配
            String goodsName = Base64.encodeBase64String("商品名称".getBytes("UTF-8"));
            dto.setGoodsName(goodsName);//商品名称 Base64 字符集 UTF-8 转码
            dto.setPayTimeout("3d");
            dto.setBusinessType("012345");//业务类型 有易付宝分配
            dto.setSubMerchantNo("70057000");//二级商户号
            dto.setSubMerchantName("70057000");//二级商户名称
            dto.setTunnelData("");//扩展信息
            dto.setRemark("");//备注
            return dto;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static CardInfoDto createCardInfoDto(){
        return new CardInfoDto();
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

    public static String  getNum(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
    public static String getBankCodeByName(String bankName){
        switch (bankName){
            case "平安银行": return "PAB";
            case "浦发银行": return "SPDB";
            case "上海农商银行": return "SRCB";
            case "上海银行": return "BOSH";
            case "盛京银行": return "SJB";
            case "西安银行": return "XAB";
            case "兴业银行": return "CIB";
            case "鄞州银行": return "YZB";
            case "营口银行": return "BOYK";
            case "邮政储蓄银行": return "PSBC";
            case "招商银行": return "CMB";
            case "浙江稠州商业银行": return "CZCB";
            case "浙商银行": return "CZB";
            case "中国工商银行": return "ICBC";
            case "中国光大银行": return "CEB";
            case "中国建设银行": return "CCB";
            case "中国民生银行": return "CMBC";
            case "中国农业银行": return "ABC";
            case "中国银行": return "BOC";
            case "中信银行": return "CITIC";
            case "重庆农村商业银行": return "CQRCB";
        }
        return "TEST_KJ";
    }
}
