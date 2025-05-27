package com.lefancrm.backend.util;

/**
 * 工具类 - 编号生成
 */
public class SerialNumberUtil {
    public static final String ORDER_PREFIX = "DD";
    public static final String RETURN_PREFIX = "TH";
    public static final String PRODUCT_PREFIX = "PR";


    public static synchronized String nextOrderCode() {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-8,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return ORDER_PREFIX +paymentID;
    }

    public static synchronized String nextWxPayOrderCode(String prefix) {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-8,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return prefix +paymentID;
    }

    public static synchronized String nextCaseCode(String prefix) {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-8,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return prefix +paymentID;
    }
    public static synchronized String nextAgentCode(String agentName) {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-8,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return agentName +paymentID;
    }
    public static synchronized String nextCardCode(String agentName) {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-6,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return agentName +paymentID;
    }
    public static synchronized String nextCardPwd() {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-6,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return paymentID;
    }

    /**
     * 生成贷款协议号
     * @param preName
     * @return
     */
    public static synchronized String generLoanAgreementCode(String preName) {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-8,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return preName.concat(paymentID);
    }

    /**
     * 生成一个10位数的请求编号
     * @return
     */
    public static synchronized String generSeqCode(){
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-8,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return paymentID;
    }

    /**
     * 生成开票数据--案件编号
     * @param preName
     * @return
     */
    public static synchronized String toBuildCaseNo(String preName) {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-8,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return preName.concat(paymentID);
    }

    /**
     * 生成一个编号
     * @param preName
     * @return
     */
    public static synchronized String toBuilNo(String preName) {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String str1 = String.valueOf(now);
        String str2 = str1.substring(str1.length()-8,str1.length());
        String paymentID =String.valueOf(r1)+String.valueOf(str2)+String.valueOf(r2);
        return preName.concat(paymentID);
    }

    public static synchronized String getSurveyCode(String preName){
        preName = preName == null ? "SH" : preName;
//        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyyMMddHHsss");
        String code = String.valueOf(preName) + System.currentTimeMillis();
//        String code = String.valueOf(preName) + String.valueOf(simpleDateFormat.format(new Date()));
        return code;
    }

    public static synchronized String getSurveyCode(String preName,String c){
        preName = preName == null ? "SH" : preName;
//        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyyMMddHHsss");
        String code = String.valueOf(c) + String.valueOf(preName) + System.currentTimeMillis();
        return code;
    }
}