package com.lefancrm.backend.util;

import org.springframework.util.StringUtils;

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
        if ("".equals(str)){
            return str;
        }
        String lastStr = str.substring(str.length() -1);
        if ("\n".equals(lastStr)){
            str = str.substring(0,str.length() -1);
            return replacePrint(str);
        }
        return str;
    }

    public static void main(String[] args) {
        String abc = "SDFSAD\n" +
                "\nssssssssssss";
        String bc = "SDFASD\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n";
        System.out.println(replacePrint(abc));
        System.out.println(replacePrint(bc));
    }
}
