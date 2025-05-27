package com.lefancrm.apicenter.util.fina;

import com.lefancrm.apicenter.util.GetWorkDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowAgingStr {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static String getAgingHtml(Date assTime,Date commitTime,Date endTime){
        String color = "";
        int days = 0;
        String _html = "";
        try {
            Date curDate = format.parse(format.format(new Date()));
            if (assTime != null){assTime = format.parse(format.format(assTime));}
            if (commitTime != null){commitTime = format.parse(format.format(commitTime));}
            if (endTime != null){endTime = format.parse(format.format(endTime));}
            if (commitTime == null){
                if (curDate.getTime() > endTime.getTime()){
                    color = "#e51c23";//红色
                    days = GetWorkDay.calLeaveDays(endTime,curDate,2);
                    _html = "<div style='color:"+color+"'>超期" + days + "天</div>";
                }else {
                    color = "#3ba9ff";//绿色
                    days = GetWorkDay.calLeaveDays(curDate,endTime,2);
                    if (days >=0 && days <=2){
                        color = "#ff9800";
                    }
                    _html = "<div style='color:"+color+"'>剩余" + days + "天</div>";
                }
            }else{
                days = GetWorkDay.calLeaveDays(assTime,commitTime,2);
                if (commitTime.getTime() <= endTime.getTime()){
                    color = "#3ba9ff";
                    _html = "<div style='color:"+color+"'>时效"+days+"天</div>";//绿色时效
                }else{
                    color = "#e51c23";
                    _html = "<div style='color:"+color+"'>时效"+days+"天</div>";//红色时效
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return _html;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getAgingHtml(new Date(), new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-13"), new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
