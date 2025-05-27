package com.lefancrm.backend.util;

import sh.zj100.common.util.ImageUtil;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by lixianfeng on 2019/11/19.
 */
public class GetWorkDay {
    public static int getDays(int type){
        int day = 0;
        switch (type){
            case 1 : day = 5; break;
            case 2 : day = 6; break;
            case 3 : day = 7; break;
        }
        return day;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(GetWorkDay.getDays(1));


//        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-08");
//        Date nextNotWorkTime = getNextNotWorkTime(date, 1);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(nextNotWorkTime));
//
//        date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-29");
//        Date date1 = calLeaveEndDate(date, null, 5, 1);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date1));
//
//
//        int days = days(0, 12);
//        System.out.println(days);
//        Double sun = 0D;
//        Double num = 12D;
//        System.out.println(sun / num - 0.25);
//
//
//        File file = new File("F:\\generate\\product.jpg");
////        File yasuoFile = new File("F:\\generate\\1200x1200\\放射科.jpg");
////        ImageUtil.drawImageScale(file,yasuoFile,1200,1200);
////        file = yasuoFile;
////        String fileName = file.getName();
////        System.out.println(fileName.substring(fileName.lastIndexOf(".") + 1));
////
////        Long size = file.length() / 1024;
////        System.out.println(size);
//
//
//        File sourceFile = new File(file.getPath());
//        String yasuoFilePath = sourceFile.getParent() + "/1200x1200/";
//        File yasuoFile = new File(yasuoFilePath);
//        if (!yasuoFile.exists()){
//            yasuoFile.mkdir();
//        }
//        yasuoFile = new File(yasuoFilePath + "/" + file.getName());
//        try {
//            ImageUtil.drawImageScale(file,file,1200,1200);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        file = yasuoFile;
//        System.out.println("file:" + file);
//
//
//        Calendar cal = Calendar.getInstance();
//        long time = file.lastModified();
//        cal.setTimeInMillis(time);
//        if(cal.getTime().compareTo(new SimpleDateFormat("yyyy-MM-dd").parse("2019-11-12")) >= 0){
//            System.out.println("11111");
//        }
//
//
//        String start = "2020-05-01";
//        String end = "2020-05-31";
//        String format = "yyyy-MM-dd";
//        List<Date> list = new ArrayList<Date>();
//        GetWorkDay getWorkDay = new GetWorkDay();
////        list = getWorkDay.calLeaveDaysList(new SimpleDateFormat("yyyy-MM-dd").parse(start), new SimpleDateFormat("yyyy-MM-dd").parse(end),1);
//        System.out.println("天数：" + list);
//
//        Date endTime = getWorkDay.calLeaveEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-17"),null,7,2);
//        System.out.println(new SimpleDateFormat(format).format(endTime));
    }

    /**
     * 获取某日期的下一个非工作日时间(含某日期)
     * @param date
     * @param efficiencyAttr 1工作日  2自然日 3纯属去除周六周日
     * @return
     */
    public static Date getNextNotWorkTime(Date date,int efficiencyAttr) throws Exception{
        date = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-M-dd").format(date));//将日期转成年月日哥实
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        date = cal.getTime();

        GetWorkDay getWorkDay = new GetWorkDay();
        if (efficiencyAttr == 1){//工作日 周一到周五休息。周六到周日上班都需要考虑
            int week;
            outer : while (true){
                cal.setTime(date);
                week = cal.get(Calendar.DAY_OF_WEEK) - 1;
                if(week == 0 || week == 6){//0为周日，6为周六
                    if (!getWorkDay.getWeekWork().contains(cal.getTime())) {//不在周六周日上班的集合内。
                        cal.add(Calendar.DAY_OF_MONTH, +1);
                        date = cal.getTime();
                        continue;
                    }
                }else{
                    //是否是节假日。
                    List<Map> holidDays = getWorkDay.getHolidDays();
                    for (Map holidDay : holidDays) {
                        if(date.compareTo((Date)holidDay.get("startDay"))>-1&&date.compareTo((Date)holidDay.get("endDay"))<1){
                            cal.add(Calendar.DAY_OF_MONTH, +1);
                            date = cal.getTime();
                            continue outer;
                        }
                    }
                }
                break ;
            }
        }else if (efficiencyAttr == 2){//自然日
            List<Map> holidDays = getWorkDay.getHolidDaysForHZ();
            outer : while (true){
                for (Map map : holidDays){
                    if(date.compareTo((Date)map.get("startDay"))>-1&&date.compareTo((Date)map.get("endDay"))<1){//如果在节假日内 则+1一天
                        cal.add(Calendar.DAY_OF_MONTH, +1);
                        date = cal.getTime();
                        continue outer;
                    }
                }
                break ;
            }
        }else if (efficiencyAttr == 3){
            //如果是周六周日且不在上班的范围内 则+1天
            int week;
            outer : while (true){
                cal.setTime(date);
                week = cal.get(Calendar.DAY_OF_WEEK) - 1;
                if(week == 0 || week == 6){//0为周日，6为周六
                    if (!getWorkDay.getWeekWork().contains(cal.getTime())) {//不在周六周日上班的集合内。
                        cal.add(Calendar.DAY_OF_MONTH, +1);
                        date = cal.getTime();
                        continue outer;
                    }
                }
                break ;
            }
        }
        return date;
    }


    /**
     *  获取 开始日期。截止日期的天数。
     * @param startTime
     * @param endTime
     * @param efficiencyAttr
     * @return
     */
    public static int calLeaveDays(String startTime,String endTime,int efficiencyAttr){
        try {
            Date start = null;
            Date end = null;
            if (startTime == null){
                start = new Date();
            }else{
                start = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
            }
            if (endTime == null){
                end = new Date();
            }else{
                end = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
            }
            return calLeaveDays(start,end,efficiencyAttr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    /**
     *  获取 开始日期。截止日期的天数。
     * @param startTime
     * @param endTime
     * @param efficiencyAttr 1 工作日（去掉周六周日）  2自然日  3、纯属去除周六周日
     * @return
     */
    public static int calLeaveDays(Date startTime,Date endTime,int efficiencyAttr){
        try {
            startTime = getNextNotWorkTime(startTime,efficiencyAttr);
            endTime = getNextNotWorkTime(endTime,efficiencyAttr);
        }catch (Exception e){
            e.printStackTrace();
        }

        String strTime = new SimpleDateFormat("yyyy-MM-dd").format(startTime);
        try {
            startTime = new SimpleDateFormat("yyyy-MM-dd").parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        strTime = new SimpleDateFormat("yyyy-MM-dd").format(endTime);
        try {
            endTime = new SimpleDateFormat("yyyy-MM-dd").parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (startTime.compareTo(endTime) == 0){
            return 0;
        }

        Boolean f = false;
        if (startTime.compareTo(endTime) > 0){
            Date oldStart = startTime;
            Date oldTime = endTime;
            startTime = oldTime;
            endTime = oldStart;
            f = true;
        }

        double leaveDays = 0;
        //从startTime开始循环，若该日期不是节假日或者不是周六日则请假天数+1
        Date flag = startTime;//设置循环开始日期
        Calendar cal = Calendar.getInstance();

        //efficiencyAttr 时效设置（1：工作日；2、自然日）
        if(efficiencyAttr == 1){
            //从数据库得到节假日的起始日期和终止日期
            List<Map> maps = null;
            try{
                GetWorkDay getWorkDay = new GetWorkDay();
                maps = getWorkDay.getHolidDays();//maps用于保存符合条件的所有节假日的起始日期和终止日期，如startDate:2017-07-13,endDate:2017-07-14
            }catch (Exception e){
                e.printStackTrace();
            }
            //用于格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int week;
            outer:while(flag.compareTo(endTime)!=1){
                cal.setTime(flag);
                //判断是否为周六日
                week = cal.get(Calendar.DAY_OF_WEEK) - 1;
                if(week == 0 || week == 6){//0为周日，6为周六
                    //2020年6月11日新增逻辑         如果是周六周日的上班 时效要算一天
                    GetWorkDay getWorkDay = new GetWorkDay();
                    if (getWorkDay.getWeekWork().contains(cal.getTime())){
                        //继续往下循环。  leaveDays + 1;
                    }else{
                        //跳出循环进入下一个日期
                        cal.add(Calendar.DAY_OF_MONTH, +1);
                        flag = cal.getTime();
                        continue;
                    }
                }else{
                    //判断是否为节假日
                    if(maps != null || !maps.isEmpty()){
                        inner:for (Map map : maps){
                            if(flag.compareTo((Date)map.get("startDay"))>-1&&flag.compareTo((Date)map.get("endDay"))<1){
                                //跳出循环进入下一个日期
                                cal.add(Calendar.DAY_OF_MONTH, +1);
                                flag = cal.getTime();
                                continue outer;
                            }
                        }
                    }
                }
                leaveDays = leaveDays + 1;
                //日期往后加一天
                cal.add(Calendar.DAY_OF_MONTH, +1);
                flag = cal.getTime();
            }
        }else if(efficiencyAttr == 2){
            //从数据库得到节假日的起始日期和终止日期
            List<Map> maps = null;
            try{
                GetWorkDay getWorkDay = new GetWorkDay();
                maps = getWorkDay.getHolidDaysForHZ();//maps用于保存符合条件的所有节假日的起始日期和终止日期，如startDate:2017-07-13,endDate:2017-07-14
            }catch (Exception e){
                e.printStackTrace();
            }
            outer:while(flag.compareTo(endTime)!=1){
                cal.setTime(flag);
                //判断是否为节假日
                if(maps != null || !maps.isEmpty()){
                    inner:for (Map map : maps){
                        if(flag.compareTo((Date)map.get("startDay"))>-1&&flag.compareTo((Date)map.get("endDay"))<1){
                            //跳出循环进入下一个日期
                            cal.add(Calendar.DAY_OF_MONTH, +1);
                            flag = cal.getTime();
                            continue outer;
                        }
                    }
                }

                leaveDays = leaveDays + 1;
                //日期往后加一天
                cal.add(Calendar.DAY_OF_MONTH, +1);
                flag = cal.getTime();
            }
        }else if(efficiencyAttr == 3){
            int week;
            outer:while(flag.compareTo(endTime)!=1){
                cal.setTime(flag);
                //判断是否为周六日
                week = cal.get(Calendar.DAY_OF_WEEK) - 1;
                if(week == 0 || week == 6){//0为周日，6为周六
                    //2020年6月11日新增逻辑         如果是周六周日的上班 时效要算一天
                    GetWorkDay getWorkDay = new GetWorkDay();
                    if (getWorkDay.getWeekWork().contains(cal.getTime())){
                        //继续往下循环。  leaveDays + 1;
                    }else{
                        //跳出循环进入下一个日期
                        cal.add(Calendar.DAY_OF_MONTH, +1);
                        flag = cal.getTime();
                        continue;
                    }
                }
                leaveDays = leaveDays + 1;
                //日期往后加一天
                cal.add(Calendar.DAY_OF_MONTH, +1);
                flag = cal.getTime();
            }
        }
        leaveDays = leaveDays - 1;
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(endTime) + ":" + (new Double(leaveDays).intValue()));
        if (f){
            return -(new Double(leaveDays).intValue());
        }
        return new Double(leaveDays).intValue();
//        return leaveDays;
    }


    /**
     * 获取某开始日期。  N天后的截止日期
     * @param startTime
     * @param endTime
     * @param days
     * @param efficiencyAttr 1 工作日（去掉周六周日）  2自然日
     * @return
     */
    public static Date calLeaveEndDate(Date startTime,Date endTime,int days,int efficiencyAttr){
        try {
            startTime = getNextNotWorkTime(startTime,efficiencyAttr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (days < 0){
            return null;
        }
        if (efficiencyAttr != 1 && efficiencyAttr != 2){
            return null;
        }
        if (endTime == null){
            endTime = startTime;
        }

        String strTime = new SimpleDateFormat("yyyy-MM-dd").format(startTime);
        try {
            startTime = new SimpleDateFormat("yyyy-MM-dd").parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        strTime = new SimpleDateFormat("yyyy-MM-dd").format(endTime);
        try {
            endTime = new SimpleDateFormat("yyyy-MM-dd").parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        double leaveDays = 0;
        //从startTime开始循环，若该日期不是节假日或者不是周六日则请假天数+1
        Date flag = startTime;//设置循环开始日期
        Calendar cal = Calendar.getInstance();

        //efficiencyAttr 时效设置（1：工作日；2、自然日）
        if(efficiencyAttr == 1){
            //从数据库得到节假日的起始日期和终止日期
            List<Map> maps = null;
            try{
                GetWorkDay getWorkDay = new GetWorkDay();
                maps = getWorkDay.getHolidDays();//maps用于保存符合条件的所有节假日的起始日期和终止日期，如startDate:2017-07-13,endDate:2017-07-14
            }catch (Exception e){
                e.printStackTrace();
            }
            //用于格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int week;
            outer:while(flag.compareTo(endTime)!=1){
                cal.setTime(flag);
                //判断是否为周六日
                week = cal.get(Calendar.DAY_OF_WEEK) - 1;
                if(week == 0 || week == 6){//0为周日，6为周六
                    //2020年6月11日新增逻辑         如果是周六周日的上班 时效要算一天
                    GetWorkDay getWorkDay = new GetWorkDay();
                    if (getWorkDay.getWeekWork().contains(cal.getTime())){
                        //继续往下循环。  leaveDays + 1;
                    }else{
                        //跳出循环进入下一个日期
                        cal.add(Calendar.DAY_OF_MONTH, +1);
                        flag = cal.getTime();
                        continue;
                    }
                }else{
                    //判断是否为节假日
                    if(maps != null || !maps.isEmpty()){
                        inner:for (Map map : maps){
                            if(flag.compareTo((Date)map.get("startDay"))>-1&&flag.compareTo((Date)map.get("endDay"))<1){
                                //跳出循环进入下一个日期
                                cal.add(Calendar.DAY_OF_MONTH, +1);
                                flag = cal.getTime();
                                continue outer;
                            }
                        }
                    }
                }
                leaveDays = leaveDays + 1;
                //日期往后加一天
                cal.add(Calendar.DAY_OF_MONTH, +1);
                flag = cal.getTime();
            }
        }else if(efficiencyAttr == 2){
            //从数据库得到节假日的起始日期和终止日期
            List<Map> maps = null;
            try{
                GetWorkDay getWorkDay = new GetWorkDay();
                maps = getWorkDay.getHolidDaysForHZ();//maps用于保存符合条件的所有节假日的起始日期和终止日期，如startDate:2017-07-13,endDate:2017-07-14
            }catch (Exception e){
                e.printStackTrace();
            }
            outer:while(flag.compareTo(endTime)!=1){
                cal.setTime(flag);
                //判断是否为节假日
                if(maps != null || !maps.isEmpty()){
                    inner:for (Map map : maps){
                        if(flag.compareTo((Date)map.get("startDay"))>-1&&flag.compareTo((Date)map.get("endDay"))<1){
                            //跳出循环进入下一个日期
                            cal.add(Calendar.DAY_OF_MONTH, +1);
                            flag = cal.getTime();
                            continue outer;
                        }
                    }
                }

                leaveDays = leaveDays + 1;
                //日期往后加一天
                cal.add(Calendar.DAY_OF_MONTH, +1);
                flag = cal.getTime();
            }
        }
        leaveDays = leaveDays - 1;

//        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(endTime) + ":" + (new Double(leaveDays).intValue()));
        if (days == new Double(leaveDays).intValue()){
            return endTime;
        }else{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endTime);
            calendar.add(Calendar.DATE,1);
            endTime = calendar.getTime();
            return calLeaveEndDate(startTime,endTime,days,efficiencyAttr);
        }
    }


    /**
     *
     * @param startTime
     * @param endTime
     * @param efficiencyAttr 返回天数集合：1 工作日（去掉周六周日）  2自然日  3、纯属去除周六周日
     * @return
     */
    public static List calLeaveDaysList(Date startTime,Date endTime,int efficiencyAttr){
        List list = new ArrayList<>();
        String strTime = new SimpleDateFormat("yyyy-MM-dd").format(startTime);
        try {
            startTime = new SimpleDateFormat("yyyy-MM-dd").parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        strTime = new SimpleDateFormat("yyyy-MM-dd").format(endTime);
        try {
            endTime = new SimpleDateFormat("yyyy-MM-dd").parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (startTime.compareTo(endTime) == 0){
            return null;
        }

        Boolean f = false;
        if (startTime.compareTo(endTime) > 0){
            Date oldStart = startTime;
            Date oldTime = endTime;
            startTime = oldTime;
            endTime = oldStart;
            f = true;
        }

        //从startTime开始循环，若该日期不是节假日或者不是周六日则请假天数+1
        Date flag = startTime;//设置循环开始日期
        Calendar cal = Calendar.getInstance();

        //efficiencyAttr 时效设置（1：工作日；2、自然日）
        if(efficiencyAttr == 1){
            //从数据库得到节假日的起始日期和终止日期
            List<Map> maps = null;
            try{
                GetWorkDay getWorkDay = new GetWorkDay();
                maps = getWorkDay.getHolidDays();//maps用于保存符合条件的所有节假日的起始日期和终止日期，如startDate:2017-07-13,endDate:2017-07-14
            }catch (Exception e){
                e.printStackTrace();
            }
            //用于格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int week;
            outer:while(flag.compareTo(endTime)!=1){
                cal.setTime(flag);
                //判断是否为周六日
                week = cal.get(Calendar.DAY_OF_WEEK) - 1;
                if(week == 0 || week == 6){//0为周日，6为周六
                    //2020年6月11日新增逻辑         如果是周六周日的上班 时效要算一天
                    GetWorkDay getWorkDay = new GetWorkDay();
                    if (getWorkDay.getWeekWork().contains(cal.getTime())){
                        //继续往下循环。  leaveDays + 1;
                    }else{
                        //跳出循环进入下一个日期
                        cal.add(Calendar.DAY_OF_MONTH, +1);
                        flag = cal.getTime();
                        continue;
                    }
                }else{
                    list.add(cal.getTime());
                    //判断是否为节假日
                    if(maps != null || !maps.isEmpty()){
                        inner:for (Map map : maps){
                            if(flag.compareTo((Date)map.get("startDay"))>-1&&flag.compareTo((Date)map.get("endDay"))<1){
                                //跳出循环进入下一个日期
                                list.remove(cal.getTime());
//                                list.add(cal.getTime());
                                cal.add(Calendar.DAY_OF_MONTH, +1);
                                flag = cal.getTime();
                                continue outer;
                            }
                        }
                    }
                }
                //日期往后加一天
                cal.add(Calendar.DAY_OF_MONTH, +1);
                flag = cal.getTime();
            }
        }
        if (f){
            return list;
        }
        return list;
    }

    public static int getDutyDays(java.util.Date startDate,java.util.Date endDate) {
        int result = 0;
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = df.parse(df.format(startDate));
            endDate = df.parse(df.format(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        while (startDate.compareTo(endDate) <= 0) {
            if (startDate.getDay() != 6 && startDate.getDay() != 0)
                result++;
            startDate.setDate(startDate.getDate() + 1);
        }
        return result;
    }

    /**
     * 根据区域 + 业务类型 获取天数
     * @param areaType
     * @param serviceId 1 工作日（去掉周六周日）
     * @return
     */
    public static int days(int areaType,int serviceId){
        int days = 0;
        if (serviceId == 13){
            days = 7;
        }else{
            switch (areaType){
                case 0 : return 3;
                case 1 : return 4;
                case 2 : return 4;
                case 3 : return 5;
                case 4 : return 7;
            }
        }
        return days;
    }


    /**
     *  国家规定的节假日集合
     * @return
     * @throws Exception
     */
    private List<Map> getHolidDays() throws Exception{
        List<Map> list = new ArrayList<Map>();
        Map<String,Date> map =  null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //2019年节假日
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2019-01-01"));map.put("endDay",simpleDateFormat.parse("2019-01-01"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2019-02-04"));map.put("endDay",simpleDateFormat.parse("2019-02-10"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2019-04-05"));map.put("endDay",simpleDateFormat.parse("2019-04-07"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2019-05-01"));map.put("endDay",simpleDateFormat.parse("2019-05-04"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2019-06-07"));map.put("endDay",simpleDateFormat.parse("2019-06-09"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2019-09-13"));map.put("endDay",simpleDateFormat.parse("2019-09-15"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2019-10-01"));map.put("endDay",simpleDateFormat.parse("2019-10-07"));list.add(map);
        //2020年节假日
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2020-01-01"));map.put("endDay",simpleDateFormat.parse("2020-01-01"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2020-01-24"));map.put("endDay",simpleDateFormat.parse("2020-01-30"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2020-04-04"));map.put("endDay",simpleDateFormat.parse("2020-04-06"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2020-05-01"));map.put("endDay",simpleDateFormat.parse("2020-05-05"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2020-06-25"));map.put("endDay",simpleDateFormat.parse("2020-06-27"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2020-10-01"));map.put("endDay",simpleDateFormat.parse("2020-10-08"));list.add(map);

        //2021年节假日
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2021-01-01"));map.put("endDay",simpleDateFormat.parse("2021-01-03"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2021-02-11"));map.put("endDay",simpleDateFormat.parse("2021-02-17"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2021-04-03"));map.put("endDay",simpleDateFormat.parse("2021-04-05"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2021-05-01"));map.put("endDay",simpleDateFormat.parse("2021-05-05"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2021-06-12"));map.put("endDay",simpleDateFormat.parse("2021-06-14"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2021-09-19"));map.put("endDay",simpleDateFormat.parse("2021-09-21"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2021-10-01"));map.put("endDay",simpleDateFormat.parse("2021-10-07"));list.add(map);

        //2022年节假日
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2022-01-01"));map.put("endDay",simpleDateFormat.parse("2022-01-03"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2022-01-31"));map.put("endDay",simpleDateFormat.parse("2022-02-06"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2022-04-03"));map.put("endDay",simpleDateFormat.parse("2022-04-05"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2022-04-30"));map.put("endDay",simpleDateFormat.parse("2022-05-04"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2022-06-03"));map.put("endDay",simpleDateFormat.parse("2022-06-05"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2022-09-10"));map.put("endDay",simpleDateFormat.parse("2022-09-12"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2022-10-01"));map.put("endDay",simpleDateFormat.parse("2022-10-07"));list.add(map);

        //2023年节假日
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2022-12-31"));map.put("endDay",simpleDateFormat.parse("2023-01-02"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2023-01-21"));map.put("endDay",simpleDateFormat.parse("2023-01-27"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2023-04-05"));map.put("endDay",simpleDateFormat.parse("2023-04-05"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2023-04-29"));map.put("endDay",simpleDateFormat.parse("2023-05-03"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2023-06-22"));map.put("endDay",simpleDateFormat.parse("2023-06-24"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2023-09-29"));map.put("endDay",simpleDateFormat.parse("2023-10-06"));list.add(map);


        //2024节假日
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2023-12-30"));map.put("endDay",simpleDateFormat.parse("2024-01-01"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2024-02-10"));map.put("endDay",simpleDateFormat.parse("2024-02-17"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2024-04-04"));map.put("endDay",simpleDateFormat.parse("2024-04-06"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2024-05-01"));map.put("endDay",simpleDateFormat.parse("2024-05-05"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2024-06-08"));map.put("endDay",simpleDateFormat.parse("2024-06-10"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2024-09-15"));map.put("endDay",simpleDateFormat.parse("2024-09-17"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2024-10-01"));map.put("endDay",simpleDateFormat.parse("2024-10-07"));list.add(map);




        //2025节假日
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2025-01-01"));map.put("endDay",simpleDateFormat.parse("2025-01-01"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2025-01-28"));map.put("endDay",simpleDateFormat.parse("2025-02-04"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2025-04-04"));map.put("endDay",simpleDateFormat.parse("2025-04-06"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2025-05-01"));map.put("endDay",simpleDateFormat.parse("2025-05-05"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2025-05-31"));map.put("endDay",simpleDateFormat.parse("2025-06-02"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2025-10-01"));map.put("endDay",simpleDateFormat.parse("2025-10-08"));list.add(map);

        return list;
    }

    /**
     *  互助 特殊的节假日集合 （自然日）
     * @return
     * @throws Exception
     */
    private List<Map> getHolidDaysForHZ() throws Exception{
        List<Map> list = new ArrayList<Map>();
        Map<String,Date> map =  null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //2019年节假日
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2019-01-01"));map.put("endDay",simpleDateFormat.parse("2019-01-01"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2019-02-04"));map.put("endDay",simpleDateFormat.parse("2019-02-10"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2019-10-01"));map.put("endDay",simpleDateFormat.parse("2019-10-07"));list.add(map);
        //2020年节假日
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2020-01-24"));map.put("endDay",simpleDateFormat.parse("2020-01-30"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2020-10-01"));map.put("endDay",simpleDateFormat.parse("2020-10-08"));list.add(map);

        //2021年春节 十一
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2021-02-11"));map.put("endDay",simpleDateFormat.parse("2021-02-17"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2021-10-01"));map.put("endDay",simpleDateFormat.parse("2021-10-07"));list.add(map);

        //2022年春节 十一
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2022-01-31"));map.put("endDay",simpleDateFormat.parse("2022-02-06"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2022-10-01"));map.put("endDay",simpleDateFormat.parse("2022-10-07"));list.add(map);

        //2023年春季 十一
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2023-01-21"));map.put("endDay",simpleDateFormat.parse("2023-01-27"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2023-09-29"));map.put("endDay",simpleDateFormat.parse("2023-10-06"));list.add(map);

        //2024年春节 十一
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2024-02-10"));map.put("endDay",simpleDateFormat.parse("2024-02-17"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2024-10-01"));map.put("endDay",simpleDateFormat.parse("2024-10-07"));list.add(map);


        //2025年春节 十一
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2025-01-28"));map.put("endDay",simpleDateFormat.parse("2025-02-04"));list.add(map);
        map =  new HashMap<String,Date>();map.put("startDay",simpleDateFormat.parse("2025-10-01"));map.put("endDay",simpleDateFormat.parse("2025-10-08"));list.add(map);
        return list;
    }

    /**
     * 周六周日  上班的日期集合
     * @return
     */
    private List<Date> getWeekWork(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> list = new ArrayList<Date>();
        try {
            //2020年
            list.add(simpleDateFormat.parse("2020-05-09"));
            list.add(simpleDateFormat.parse("2020-06-28"));
            list.add(simpleDateFormat.parse("2020-09-27"));
            list.add(simpleDateFormat.parse("2020-10-10"));

            //2021年
            list.add(simpleDateFormat.parse("2021-02-07"));
            list.add(simpleDateFormat.parse("2021-02-20"));
            list.add(simpleDateFormat.parse("2021-04-25"));
            list.add(simpleDateFormat.parse("2021-05-08"));
            list.add(simpleDateFormat.parse("2021-09-18"));
            list.add(simpleDateFormat.parse("2021-09-26"));
            list.add(simpleDateFormat.parse("2021-10-09"));

            //2022年
            list.add(simpleDateFormat.parse("2022-01-29"));
            list.add(simpleDateFormat.parse("2022-01-30"));
            list.add(simpleDateFormat.parse("2022-04-02"));
            list.add(simpleDateFormat.parse("2022-04-24"));
            list.add(simpleDateFormat.parse("2022-05-07"));
            list.add(simpleDateFormat.parse("2022-10-08"));
            list.add(simpleDateFormat.parse("2022-10-09"));

            //2023年
            list.add(simpleDateFormat.parse("2023-01-28"));
            list.add(simpleDateFormat.parse("2023-01-29"));
            list.add(simpleDateFormat.parse("2023-04-23"));
            list.add(simpleDateFormat.parse("2023-05-06"));
            list.add(simpleDateFormat.parse("2023-06-25"));
            list.add(simpleDateFormat.parse("2023-10-07"));
            list.add(simpleDateFormat.parse("2023-10-08"));

            //2024
            list.add(simpleDateFormat.parse("2024-02-04"));
            list.add(simpleDateFormat.parse("2024-02-18"));
            list.add(simpleDateFormat.parse("2024-04-07"));
            list.add(simpleDateFormat.parse("2024-04-28"));
            list.add(simpleDateFormat.parse("2024-05-11"));
            list.add(simpleDateFormat.parse("2024-09-14"));
            list.add(simpleDateFormat.parse("2024-09-29"));
            list.add(simpleDateFormat.parse("2024-10-12"));

            //2025
            list.add(simpleDateFormat.parse("2025-01-26"));
            list.add(simpleDateFormat.parse("2025-02-08"));
            list.add(simpleDateFormat.parse("2025-04-27"));
            list.add(simpleDateFormat.parse("2025-09-28"));
            list.add(simpleDateFormat.parse("2025-10-11"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
