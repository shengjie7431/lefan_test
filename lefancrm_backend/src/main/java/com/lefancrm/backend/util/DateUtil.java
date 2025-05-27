package com.lefancrm.backend.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static final String YYYYMMDD="yyyy-MM-dd";
    //转换
    public static void convertTimeBySearchType(StringBuilder startTime,StringBuilder endTime,String searchType){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar= Calendar.getInstance();
        String start = "";
        String end = "";
        if ("upMonth".equals(searchType)) {
            calendar.setTime(new Date());
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            start = simpleDateFormat.format(calendar.getTime());

            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            end = simpleDateFormat.format(calendar.getTime());
        } else if ("yesterday".equals(searchType)){
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -1);
            start = simpleDateFormat.format(calendar.getTime());
            end = start;
        }else if ("today".equals(searchType)){
            calendar.setTime(new Date());
            start = simpleDateFormat.format(calendar.getTime());
            end = start;
        }else if ("curWeek".equals(searchType)){
            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            start = simpleDateFormat.format(calendar.getTime());

            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            end = simpleDateFormat.format(calendar.getTime());
        }else if ("curMonth".equals(searchType)){
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            start = simpleDateFormat.format(calendar.getTime());

            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            end = simpleDateFormat.format(calendar.getTime());
        }
        startTime = startTime.delete(0,startTime.length()).append(start);
        endTime = endTime.delete(0,endTime.length()).append(end);
    }


    /**
     * 获取当前时间上月的第一天
     * @return
     */
    public static String getTopMonth(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date date = calendar.getTime();
        String startTime=simpleDateFormat.format(date);
        return startTime;
    }

    /**
     * 获取当前时间上月的第后一天
     * @return
     */
    public static String getLastMonth(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date date = calendar.getTime();
        String endTime=simpleDateFormat.format(date);
        return endTime;
    }

    public static Date getUpMonth(){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的前几月
     * @param currentTime
     * @param days
     * @return
     */
    public static String getFirstMonth(String currentTime,int days){
        try{
            //日期格式
            DateFormat format = new SimpleDateFormat("yyyy-MM");
            Date date = format.parse(currentTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, days);
            Date d = calendar.getTime();
            String strDate = format.format(d);
            return strDate;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将CST格式的时间转换成日期格式
     * @param currentTime
     * @return
     */
    public static String getCSTStr(String currentTime){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            Date date = (Date) sdf.parse(currentTime);
            String formatStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
            return formatStr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得指定日期的前一天
     * @param specifiedDayDate
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDayDate){
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
        Calendar calendar = Calendar.getInstance();
        Date date=null;
        try {
            date = sdf.parse(specifiedDayDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day=calendar.get(Calendar.DATE);
        //此处修改为+1则是获取后一天
        calendar.set(Calendar.DATE,day-1);

        String lastDay = sdf.format(calendar.getTime());
        return lastDay;
    }


    //去除节假日和周末
    public static String request(String httpArg) {
        String httpUrl = "http://tool.bitefu.net/jiari/";
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?d=" +httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        // 处理节假日
        String httpArg = "2020-10-07";
        String jsonResult = request(httpArg);
        // 0 上班  1周末 2节假日
        if ("0".equals(jsonResult)) {
            //return resultObject.getFailResult("上班");
            System.out.println("0上班");
        }
        if ("1".equals(jsonResult)) {
            //return resultObject.getFailResult("1周末");
            System.out.println("1节假日");
        }
        if ("2".equals(jsonResult)) {
            //return resultObject.getFailResult("");
            System.out.println("2节假日");
        }
    }
}
