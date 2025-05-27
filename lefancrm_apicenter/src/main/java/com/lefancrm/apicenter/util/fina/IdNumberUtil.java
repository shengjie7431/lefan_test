package com.lefancrm.apicenter.util.fina;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdNumberUtil {
    //根据身份证号输出年龄
    public static int IdNOToAge(String IdNO){
        if (StringUtils.isEmpty(IdNO)) {
            return 0;
        }
        int leh = IdNO.length();
        String dates="";
        if (leh == 18) {
            int se = Integer.valueOf(IdNO.substring(leh - 1)) % 2;
            dates = IdNO.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            int u=Integer.parseInt(year)-Integer.parseInt(dates);
            return u;
        }
        return 0;
    }
}
