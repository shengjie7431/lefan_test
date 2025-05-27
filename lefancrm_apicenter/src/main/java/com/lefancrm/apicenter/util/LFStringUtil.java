package com.lefancrm.apicenter.util;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LFStringUtil {
    /**
     * 去掉字符串最后的换行 及 空格
     * @param str
     * @return
     */
    public static String replacePrint(String str){
        if (StringUtils.isEmpty(str)){
            return str;
        }
        str = str.trim();
        String lastStr = str.substring(str.length() -1);
        if ("\n".equals(lastStr)){
            str = str.substring(0,str.length() -1);
            return replacePrint(str);
        }
        return str;
    }

    private static String resStr(String str){
        int a = str.indexOf("${");
        if (a == -1) return "";
        String tempStr = str.substring(str.indexOf("${"));
        int b = tempStr.indexOf("}");
        if (b == -1) return "";
        String resStr = str.substring(a, a + b + 1);
        return resStr;
    }

    public static void main(String[] args) {
        String str = "abc{kkk${abc}bbb}sss${kkk}skkk";
        String temp = str;
        for (;true;){
            String resStr = LFStringUtil.resStr(temp);//
            if ("".equals(resStr)) {
                break;
            }
            System.out.println(resStr);
            temp = temp.substring(temp.indexOf(resStr) + resStr.length());
        }

//        System.out.println(str.indexOf("${"));
//        System.out.println(str.substring(str.indexOf("${")).indexOf("}"));
//
//
//        Calendar cld = Calendar.getInstance();
//        List<String> dates = new ArrayList<String>();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
//        cld.setTime(new Date());
//        cld.add(Calendar.YEAR,-1);
//        Date tempTime = cld.getTime();
//        for (;true;){
//            cld.setTime(tempTime);
//            cld.add(Calendar.MONTH,1);
//            tempTime = cld.getTime();
//            if (tempTime.after(new Date())){
//                break;
//            }
//            dates.add(simpleDateFormat.format(tempTime));
//        }
//        for (String date : dates) {
//            System.out.println(date);
//        }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        String abc = "SDFSAD\n" +
//                "\nssssssssssss";
//        String bc = "SDFASD\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "\n";
//        System.out.println(replacePrint(abc));
//        System.out.println(replacePrint(bc));
    }
}
