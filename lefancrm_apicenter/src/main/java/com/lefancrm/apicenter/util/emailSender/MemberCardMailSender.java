package com.lefancrm.apicenter.util.emailSender;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by DELL on 2017/10/23.
 */
@Service
public class MemberCardMailSender {


    /**
     * @param filePath
     *            文件路径
     * @return 获得html的全部内容
     */
    private static String readHtml(String filePath) {
        BufferedReader  br = null;
        StringBuffer sb = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    filePath), "utf-8"));
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    private static String getInsuranceDate(){
        Calendar cal = new GregorianCalendar();
        int year = cal.get(Calendar.YEAR);//yy  直接计算年数+1
        int month = cal.get(Calendar.MONTH);//MM
        int day = cal.get(Calendar.DATE);//dd
        String str = year + "年" + (month+1) + "月" + day +"日00:00起至" + (year+1) + "年" + (month+1) + "月" + day +"日24:00止";
        return str;
    }

    /**
     * 发送会员卡邮件
     * @param cardNumber 会员卡号
     * @param carNumber 车牌号
     * @return
     */
    public static String newContext(String path ,String cardNumber,String carNumber){
        String str = readHtml(path);
        str = str.replace("<p></p>", "<p>"+cardNumber+"</p>");
        str = str.replace("保险车辆车牌号：</span><span class='tow'></span>",
                "保险车辆车牌号：</span><span class='tow'>"+carNumber+"</span>");
        str = str.replace("保险期限：</span><span class='tow'></span>",
                " 保险期限：</span><span class='tow'>"+getInsuranceDate()+"</span>");
        return str;
    }
}
