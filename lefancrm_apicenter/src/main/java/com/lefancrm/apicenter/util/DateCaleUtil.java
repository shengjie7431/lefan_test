package com.lefancrm.apicenter.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lixianfeng on 2019/3/18.
 */
public class DateCaleUtil {
    /**
     * 判断输入的年月日日期是否属于休息日
     * @param date  需要判断的日期（年月日）
     * @param lawHolidayList  国家规定放假的时间
     * @param lawWorkList  国家规定的工作日期
     * @return
     */
    public static boolean isDayOff(Date date,List<Date> lawHolidayList,List<Date> lawWorkList){

        for(Date date1 :lawHolidayList){
            int c = date.compareTo(date1);
            if(c==0){
                //休息日
                return true;
            }
        }

        for(Date date1 :lawWorkList){
            int c = date.compareTo(date1);
            if(c==0){
                //工作日
                return false;
            }
        }

        return isZhouLiuZhouRiDate(date);
    }


    /**
     * 判断时间是否属于正常周六日
     * @param date
     * @return
     */
    public static boolean isZhouLiuZhouRiDate(Date date){

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        //是否属于周六日
        boolean flag = (week == 0 || week == 6);
        return flag;

    }

    /**
     * 排除国家法定的休息日、正常周六日，计算两个时间相差多少小时数（休息日当天时间为零处理）
     * @param startTimeYYYYMMDDHHMMSS  年月日时分秒
     * @param endTimeYYYYMMDDHHMMSS  年月日时分秒
     * @param lawHolidayList
     * @param lawWorkList
     * @return
     */
    public static long workHours(Date startTimeYYYYMMDDHHMMSS,
                                 Date endTimeYYYYMMDDHHMMSS,
                                 List<Date> lawHolidayList,
                                 List<Date> lawWorkList) throws Exception {
        //开始时间转成年月日格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strStartTimeYYYYMMDD = sdf.format(startTimeYYYYMMDDHHMMSS);
        Date startTimeYYYYMMDD = sdf.parse(strStartTimeYYYYMMDD);
        //开始时间是否属于休息日
        boolean startTimeIsDayOff = isDayOff(startTimeYYYYMMDD, lawHolidayList, lawWorkList);

        //结束时间转成年月日格式
        String strEndTimeYYYYMMDD = sdf.format(endTimeYYYYMMDDHHMMSS);
        Date endTimeYYYYMMDD = sdf.parse(strEndTimeYYYYMMDD);
        //结束时间是否属于休息日
        boolean endTimeIsDayOff = isDayOff(endTimeYYYYMMDD, lawHolidayList, lawWorkList);

        //分为4种情况
        if (startTimeIsDayOff) {
            if (!endTimeIsDayOff) {
                //开始时间在休息日里，结束时间不在休息日里（开始那天不计算小时数，结束那天计算小时数）
                Calendar cal = Calendar.getInstance();
                cal.setTime(startTimeYYYYMMDD);
                cal.add(Calendar.DAY_OF_MONTH, +1);
                Date validStartTimeYYYYMMDD = cal.getTime();
                Date validStartTimeYYYYMMDDTemp = validStartTimeYYYYMMDD;
                int skipDay = 0;

                //循环遍历开始时间之后的每一个日期
                while (validStartTimeYYYYMMDDTemp.compareTo(endTimeYYYYMMDDHHMMSS) != 1) {
                    if (isDayOff(validStartTimeYYYYMMDDTemp, lawHolidayList, lawWorkList)) {
                        skipDay += 1;
                    }
                    cal.add(Calendar.DAY_OF_MONTH, +1);
                    validStartTimeYYYYMMDDTemp = cal.getTime();
                }

                return ((endTimeYYYYMMDDHHMMSS.getTime() - validStartTimeYYYYMMDD.getTime()) / (60 * 60 * 1000)) - skipDay * 24;
            } else {
                //开始时间在休息日里，结束时间也在休息日里（开始那天不计算小时数，结束那天也不计算小时数，看中间有多少个工作日）
                Calendar cal = Calendar.getInstance();
                cal.setTime(startTimeYYYYMMDD);
                cal.add(Calendar.DAY_OF_MONTH, +1);
                Date validStartTimeYYYYMMDD = cal.getTime();
                //工作日天数
                int workDays = 0;
                //循环遍历开始时间之后的每一个日期
                while (validStartTimeYYYYMMDD.compareTo(endTimeYYYYMMDDHHMMSS) != 1) {
                    if (!isDayOff(validStartTimeYYYYMMDD, lawHolidayList, lawWorkList)) {
                        workDays += 1;
                    }
                    cal.add(Calendar.DAY_OF_MONTH, +1);
                    validStartTimeYYYYMMDD = cal.getTime();
                }
                return workDays * 24;
            }
        } else {
            if (endTimeIsDayOff) {

                int skipDay = 0;
                //开始时间不在休息日里，结束时间在休息日里
                Calendar cal = Calendar.getInstance();
                cal.setTime(startTimeYYYYMMDD);
                cal.add(Calendar.DAY_OF_MONTH, +1);
                Date validStartTimeYYYYMMDD = cal.getTime();
                while (validStartTimeYYYYMMDD.compareTo(endTimeYYYYMMDDHHMMSS) != 1) {
                    if (!isDayOff(validStartTimeYYYYMMDD, lawHolidayList, lawWorkList)) {
                        skipDay += 1;
                    }
                    cal.add(Calendar.DAY_OF_MONTH, +1);
                    validStartTimeYYYYMMDD = cal.getTime();
                }

                Calendar ca = Calendar.getInstance();
                ca.setTime(startTimeYYYYMMDDHHMMSS);
                int startHour = ca.get(Calendar.HOUR_OF_DAY);
                return (24-startHour) + skipDay * 24;
            } else {
                //开始时间在不在休息日里，结束时间也不在休息日里
                int skipDay = 0;
                Calendar cal = Calendar.getInstance();
                cal.setTime(startTimeYYYYMMDD);
                cal.add(Calendar.DAY_OF_MONTH, +1);
                Date validStartTimeYYYYMMDD = cal.getTime();
                while (validStartTimeYYYYMMDD.compareTo(endTimeYYYYMMDDHHMMSS) != 1) {
                    if (isDayOff(validStartTimeYYYYMMDD, lawHolidayList, lawWorkList)) {
                        skipDay += 1;
                    }
                    cal.add(Calendar.DAY_OF_MONTH, +1);
                    validStartTimeYYYYMMDD = cal.getTime();
                }
                return ((endTimeYYYYMMDDHHMMSS.getTime() - startTimeYYYYMMDDHHMMSS.getTime()) / (60 * 60 * 1000)) - skipDay * 24;
            }
        }
    }

    public static void main(String args[]) throws Exception{



        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = "";
        String end = "";
        int month = 5;
        Calendar now = Calendar.getInstance();
//        int month = now.get(Calendar.MONTH) + 1;

        if(month == 1 || month ==2 || month ==3){
            now.set(now.get(Calendar.YEAR), 0, 1, 00, 00, 00);
            start = simpleDateFormat.format(now.getTime());
            now.set(now.get(Calendar.YEAR), 2, 31, 23, 59, 59);
            end = simpleDateFormat.format(now.getTime());
        }else if(month == 4 || month ==5 || month ==6){
            now.set(now.get(Calendar.YEAR), 3, 1, 00, 00, 00);
            start = simpleDateFormat.format(now.getTime());
            now.set(now.get(Calendar.YEAR), 5, 30, 23, 59, 59);
            end = simpleDateFormat.format(now.getTime());
        }
        else if(month == 7 || month ==8 || month ==9){
            now.set(now.get(Calendar.YEAR), 6, 1, 00, 00, 00);
            start = simpleDateFormat.format(now.getTime());
            now.set(now.get(Calendar.YEAR), 8, 30, 23, 59, 59);
            end = simpleDateFormat.format(now.getTime());
        }
        else if(month == 10 || month ==11 || month ==12){
            now.set(now.get(Calendar.YEAR), 9, 1, 00, 00, 00);
            start = simpleDateFormat.format(now.getTime());
            now.set(now.get(Calendar.YEAR), 11, 31, 23, 59, 59);
            end = simpleDateFormat.format(now.getTime());
        }
        System.out.println(start);
        System.out.println(end);


        Long a = 23L;
        System.out.println(String.format("%s天%s小时", a / 24, a - a / 24 * 24));
//        System.out.println(a - a / 24 * 24);


        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> lawHolidayDate = new ArrayList<>();
        List<Date> lawWorkDate = new ArrayList<>();
        String [] lawHolidayDateStr =
                new String[] {};
        String [] lawWorkDateStr =
                new String[] {};

        for(String str :lawHolidayDateStr){
            lawHolidayDate.add(yyyyMMdd.parse(str));
        }

        for(String str :lawWorkDateStr){
            lawWorkDate.add(yyyyMMdd.parse(str));
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = sdf.parse("2019-03-15 1:00:00");
        Date endDate = sdf.parse("2019-03-18 1:00:01");
        Long hours = workHours(startDate,endDate,lawHolidayDate,lawWorkDate);
        SimpleDateFormat tempSdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        Date tempDate = tempSdf.parse(tempSdf.format(endDate));
        if (endDate.getTime() > tempDate.getTime()){
            hours += 1;
        }
        System.out.println("相差小时数：" + hours);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2019);
        calendar.set(Calendar.MONTH,1 - 1);
        int first = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,first);
        start = simpleDateFormat.format(calendar.getTime());

        int last = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,last);
        end = simpleDateFormat.format(calendar.getTime());

        System.out.println(start);
        System.out.println(end);

        int year = 2019;
        int key = 4;
        if(key == 1){
            calendar.set(year, 0, 1, 00, 00, 00);
            start = simpleDateFormat.format(calendar.getTime());
            calendar.set(year, 2, 31, 23, 59, 59);
            end = simpleDateFormat.format(calendar.getTime());
        }else if(key == 2){
            calendar.set(year, 3, 1, 00, 00, 00);
            start = simpleDateFormat.format(calendar.getTime());
            calendar.set(year, 5, 30, 23, 59, 59);
            end = simpleDateFormat.format(calendar.getTime());
        }
        else if(key == 3){
            calendar.set(year, 6, 1, 00, 00, 00);
            start = simpleDateFormat.format(calendar.getTime());
            calendar.set(year, 8, 30, 23, 59, 59);
            end = simpleDateFormat.format(calendar.getTime());
        }
        else if(key == 4){
            calendar.set(year, 9, 1, 00, 00, 00);
            start = simpleDateFormat.format(calendar.getTime());
            calendar.set(year, 11, 31, 23, 59, 59);
            end = simpleDateFormat.format(calendar.getTime());
        }

        System.out.println(start);
        System.out.println(end);
    }

    /**
     * 排除周六周日  计算两个时间相差得天数
     * @return
     * @throws Exception
     */
    public static Long getDiffHours(Date starTime,Date endTime) {
        try {
            SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
            List<Date> lawHolidayDate = new ArrayList<>();
            List<Date> lawWorkDate = new ArrayList<>();
            String [] lawHolidayDateStr =
                    new String[] {};
            String [] lawWorkDateStr =
                    new String[] {};
            for(String str :lawHolidayDateStr){
                lawHolidayDate.add(yyyyMMdd.parse(str));
            }
            for(String str :lawWorkDateStr){
                lawWorkDate.add(yyyyMMdd.parse(str));
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date startDate = sdf.parse("2019-03-15 1:00:00");
            Date startDate = sdf.parse(sdf.format(starTime));
//        Date endDate = sdf.parse("2019-03-18 1:00:01");
            Date endDate = sdf.parse(sdf.format(endTime));

            Long hours = workHours(startDate,endDate,lawHolidayDate,lawWorkDate);
            SimpleDateFormat tempSdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
            Date tempDate = tempSdf.parse(tempSdf.format(endDate));
            if (endDate.getTime() > tempDate.getTime()){
                hours += 1;
            }
            return hours;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0L;
    }
}

