
package com.lefancrm.apicenter.util;

/*import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SendMessageUntil {
	private final static String url = "http://www.etuocloud.com/gateway.action";
	//应用 app_key
	private final static String APP_KEY = "4bdPcFT8OKYntrazrj1IK5MQkJnMs959";
	//应用 app_secret
	private final static String APP_SECRET = "o8StP550NV2CoYA0MNiNe1F5XbXswRWoiii4HOGrrXHWAbNMVvPk2Tdgrllf8fim";
	//接口响应格式 json或xml
	private final static String FORMAT = "json";
	private static String genSign(Map<String, String> params)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//TreeMap 默认按key 升序
		Map<String,String> sortMap = new TreeMap<String,String>();
		sortMap.putAll(params);
		//以k1=v1&k2=v2...方式拼接参数
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> s : sortMap.entrySet()) {
			String k = s.getKey();
			String v = s.getValue();
			if(StringUtils.isBlank(v)){//过滤空值
				continue;
			}
			builder.append(k).append("=").append(v).append("&");
		}
		if (!sortMap.isEmpty()) {
			builder.deleteCharAt(builder.length() - 1);
		}
		//拼接应用的app_secret
		builder.append(APP_SECRET);
		//摘要
		MessageDigest instance = MessageDigest.getInstance("MD5");
		byte[] digest = instance.digest(builder.toString().getBytes("UTF-8"));
		//十六进制表示
		return new String(encodeHex(digest));
	}
	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
		'e', 'f' };
	private static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS_LOWER[0x0F & data[i]];
		}
		return out;
	}
	public static String sendSmsCustom(HttpServletResponse response,String cardtype,String carnumber,String time,String phonenumber,String cardnumber) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		String paramsmes=cardtype+","+cardnumber+","+carnumber+","+time;
		params.put("app_key", APP_KEY);
		params.put("view", FORMAT);
		params.put("method", "cn.etuo.cloud.api.sms.template");
		params.put("to", phonenumber);
		params.put("template", "777");
		params.put("params", paramsmes);
		params.put("sign", genSign(params));
		String result = HttpClientUtils.httpPost(url, params);
		return result;
	}

    public static String sendSmsCustom2(HttpServletResponse response,String cardtype,String idcard,String time,String phonenumber,String cardnumber) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=cardtype+","+cardnumber+","+idcard+","+time;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1284");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }

	public static String sendSmsCardAc(HttpServletResponse response,String cardtype,String phonenumber,String cardnumber,String cardpwd) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		String paramsmes=cardtype+","+cardnumber+","+cardpwd;
		params.put("app_key", APP_KEY);
		params.put("view", FORMAT);
		params.put("method", "cn.etuo.cloud.api.sms.template");
		params.put("to", phonenumber);
		params.put("template", "787");
		params.put("params", paramsmes);
		params.put("sign", genSign(params));
		String result = HttpClientUtils.httpPost(url, params);
		return result;
	}

    //贷款状态变更短信
    public static String sendSmsLoanApplication(String phonenumber,String loanStateStr) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=loanStateStr;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "992");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //案件中心状态变更通知
    public static String sendSmsCaseCenterInfo(String phonenumber,String caseType,String caseStatus) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=caseType+","+caseStatus;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1045");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //成为推广大使短信发送
    public static String sendSmsUserPromoted(String phonenumber) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes="4006303071";
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "990");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }

    //共享理赔邀请第三方参与调解
    public static String sharedCaseSendMsg(String phonenumber,String userName,String dateStr) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName+","+dateStr;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1158");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }

    //共享理赔驳回案件短信
    public static String sharedCaseRejectMsg(String phonenumber,String userName) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1189");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }

    //共享理赔保险公司理赔员参与消息
    public static String sharedInPffierCaseSendMsg(String phonenumber,String userName) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1160");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //调解方案金额变动发送短信通知
    public static String sharedCaseModifySendMsg(String phonenumber,String userName) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1159");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }


    //调解方案，同意或者不同意短信
    public static String sharedCaseOpinionMsg(String phonenumber,String userName,String otherName,String opinion) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName+","+otherName+","+opinion;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1190");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }

    //调解方案，调解成功
    public static String sharedCaseSuccessMsg(String phonenumber,String userName) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1192");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //调解方案，调解失败
    public static String sharedCaseFailMsg(String phonenumber,String userName) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1191");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //邀请保险员
    public static String sharedCaseInOfficerMsg(String phonenumber,String userName,String carNumber,String dateStr) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName+","+carNumber+","+dateStr;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1196");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }


    public static String sendSmsCardLeQi(HttpServletResponse response,String cardNumber,String phonenumber) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=cardNumber;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1282");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }

    public static UserSendSms sendSmsValidateCodeByUser(String phonenumber,String smsCode,int type) throws Exception {
        UserSendSms sendSms=new UserSendSms();
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=smsCode;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.simple");
        params.put("to", phonenumber);
        if(1==type){
            params.put("template", "1270");
        }else{
            params.put("template", "1271");
        }
        params.put("smscode", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        sendSms.setSmsCode(smsCode);
        sendSms.setTelphone(phonenumber);
        return sendSms;
    }
    public static void main(String[] args) throws Exception {
        String time="1年 明日0时生效";
        String result= SendMessageUntil.sendSmsCustom(null, "车险人伤“及时雨”服务卡", "苏C5399S"
                , time, "13605219298", "Y0000691");
    }
}
*/
import com.lefancrm.apicenter.model.SendSms;
import com.lefancrm.apicenter.model.UserSendSms;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SendMessageUntil {
    private final static String url = "http://www.etuocloud.com/gateway.action";
    //应用 app_key
    private final static String APP_KEY = "4bdPcFT8OKYntrazrj1IK5MQkJnMs959";
    //应用 app_secret
    private final static String APP_SECRET = "o8StP550NV2CoYA0MNiNe1F5XbXswRWoiii4HOGrrXHWAbNMVvPk2Tdgrllf8fim";
    //接口响应格式 json或xml
    private final static String FORMAT = "json";
    private static String genSign(Map<String, String> params)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //TreeMap 默认按key 升序
        Map<String,String> sortMap = new TreeMap<String,String>();
        sortMap.putAll(params);
        //以k1=v1&k2=v2...方式拼接参数
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> s : sortMap.entrySet()) {
            String k = s.getKey();
            String v = s.getValue();
            if(StringUtils.isBlank(v)){//过滤空值
                continue;
            }
            builder.append(k).append("=").append(v).append("&");
        }
        if (!sortMap.isEmpty()) {
            builder.deleteCharAt(builder.length() - 1);
        }
        //拼接应用的app_secret
        builder.append(APP_SECRET);
        //摘要
        MessageDigest instance = MessageDigest.getInstance("MD5");
        byte[] digest = instance.digest(builder.toString().getBytes("UTF-8"));
        //十六进制表示
        return new String(encodeHex(digest));
    }
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f' };
    private static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return out;
    }
    public static String sendSmsCustom(HttpServletResponse response,String cardtype,String carnumber,String time,String phonenumber,String cardnumber) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=cardtype+","+cardnumber+","+carnumber+","+time;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "777");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    public static String sendSmsCardAc(HttpServletResponse response,String cardtype,String phonenumber,String cardnumber,String cardpwd) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=cardtype+","+cardnumber+","+cardpwd;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "787");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    public static SendSms sendSmsValidateCode(String phonenumber,String smsCode) throws Exception {
        SendSms sendSms=new SendSms();
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=smsCode;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "305");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        sendSms.setSmsCode(smsCode);
        sendSms.setTelphone(phonenumber);
        return sendSms;
    }
    public static UserSendSms sendSmsValidateCodeByUser(String phonenumber,String smsCode,int type) throws Exception {
        UserSendSms sendSms=new UserSendSms();
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=smsCode;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.simple");
        params.put("to", phonenumber);
        if(1==type){
            params.put("template", "1270");
        }else{
            params.put("template", "1271");
        }
        params.put("smscode", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        sendSms.setSmsCode(smsCode);
        sendSms.setTelphone(phonenumber);
        return sendSms;
    }
    //贷款状态变更短信
    public static String sendSmsLoanApplication(String phonenumber,String loanStateStr) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=loanStateStr;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "992");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //案件中心状态变更通知
    public static String sendSmsCaseCenterInfo(String phonenumber,String caseType,String caseStatus) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=caseType+","+caseStatus;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1045");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //成为推广大使短信发送
    public static String sendSmsUserPromoted(String phonenumber) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes="4006303071";
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "990");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }

    //共享理赔邀请第三方参与调解
    public static String sharedCaseSendMsg(String phonenumber,String userName,String dateStr) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName+","+dateStr;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1158");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }

    //共享理赔驳回案件短信
    public static String sharedCaseRejectMsg(String phonenumber,String userName) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1189");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }

    //共享理赔保险公司理赔员参与消息
    public static String sharedInPffierCaseSendMsg(String phonenumber,String userName) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1160");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //调解方案金额变动发送短信通知
    public static String sharedCaseModifySendMsg(String phonenumber,String userName) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1159");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }


    //调解方案，同意或者不同意短信
    public static String sharedCaseOpinionMsg(String phonenumber,String userName,String otherName,String opinion) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName+","+otherName+","+opinion;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1190");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }

    //调解方案，调解成功
    public static String sharedCaseSuccessMsg(String phonenumber,String userName) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1192");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //调解方案，调解失败
    public static String sharedCaseFailMsg(String phonenumber,String userName) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1191");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //邀请保险员
    public static String sharedCaseInOfficerMsg(String phonenumber,String userName,String carNumber,String dateStr) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=userName+","+carNumber+","+dateStr;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1196");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }

    //测算审核短息
    public static String paymentEstimateMsg(String phonenumber,String dateStr) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=dateStr;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1333");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //分派案件短信提醒
    public static String assignmentSurveyCase(String phonenumber,String investigator,String receiveDateStr,String surveyNo,String surveyPerson,String durationStr) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=investigator+","+receiveDateStr+","+surveyNo+","+surveyPerson+","+durationStr;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1537");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //任务到期前一天T-1
    public static String surveyCaseTJian1(String phonenumber,String investigator,String surveyNo,String surveyPerson) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=investigator+","+surveyNo+","+surveyPerson;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1538");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //任务到期当天
    public static String surveyCaseT(String phonenumber,String investigator,String surveyNo,String surveyPerson) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=investigator+","+surveyNo+","+surveyPerson;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1539");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    //任务到期后一天
    public static String surveyCaseTJIA1(String phonenumber,String investigator,String surveyNo,String surveyPerson) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes=investigator+","+surveyNo+","+surveyPerson;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", "1540");
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
    /**
     * 案件发送短信
     * @param phoneNumber  接收电话号码
     * @param params       参数集合   约定最后一个参数必须是模板编号
     * @return
     * @throws Exception
     */
    public static String caseToMessage(String phoneNumber,String ...params) throws Exception{
        Map<String, String> maps = new HashMap<String, String>();
        if (params != null){
            StringBuffer sb = new StringBuffer();
            //不循环最后一个参数
            for (int i = 0 ; i < params.length - 1; i++){
                //倒数第二个参数不加逗号
                if (i == params.length - 2){
                    sb.append(params[i]);
                    break;
                }
                sb.append(params[i].concat(","));
            }
            maps.put("app_key", APP_KEY);
            maps.put("view", FORMAT);
            maps.put("method", "cn.etuo.cloud.api.sms.template");
            maps.put("to", phoneNumber);
            maps.put("template", params[params.length - 1]);
            maps.put("params", sb.toString());
            maps.put("sign", genSign(maps));
        }
        String result = HttpClientUtils.httpPost(url, maps);
        return result;
    }

    //案件中心状态变更通知
    public static String sendFinaApplicantInfo(String phonenumber,String insuredName,String entrustOrgName, Double actualMoney, String payImgUrl, String template) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes = insuredName +","+ entrustOrgName +","+ actualMoney +","+ payImgUrl;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", template);
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }

    public static String sendFinaApplicantInfo2(String phonenumber,String insuredName,String entrustOrgNameFirst,String entrustOrgNameSecond, Double actualMoney, String payImgUrlFirst,String payImgUrlSecond,String payImgUrlThree, String template) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String paramsmes = insuredName +","+ entrustOrgNameFirst +","+ entrustOrgNameSecond +","+ actualMoney +","+ payImgUrlFirst+","+ payImgUrlSecond+","+ payImgUrlThree;
        params.put("app_key", APP_KEY);
        params.put("view", FORMAT);
        params.put("method", "cn.etuo.cloud.api.sms.template");
        params.put("to", phonenumber);
        params.put("template", template);
        params.put("params", paramsmes);
        params.put("sign", genSign(params));
        String result = HttpClientUtils.httpPost(url, params);
        return result;
    }
}
