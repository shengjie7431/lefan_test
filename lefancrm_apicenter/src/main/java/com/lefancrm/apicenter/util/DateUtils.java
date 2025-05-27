package com.lefancrm.apicenter.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by user on 2015/6/3.
 */
public class DateUtils {


    private static final String YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
    private static final String YYYYMMDD="yyyy-MM-dd";
    private static final String YYYYMM="yyyy-MM";


    public static void main1(String[] args) throws ParseException {
//        Double a = 0.00D;
//        System.out.println( (float) (3 - 5)  /  3);
//        System.out.println(new BigDecimal((float) (3 - 5) / 3).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String dateStr = simpleDateFormat.format(new Date());
//        System.out.println(dateStr);
//        String data1 = "2020-05-08";
//        String data2 = "2020-05-09";
//        System.out.println(getChainTime(data1, data2));
//        System.out.println(data1.compareTo(data2));
//        System.out.println(Double.valueOf(1)/Double.valueOf(15));


        //1.获取当前时间
        Date dd=new Date();
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String end=sim.format(dd);
        //2.定义一个比较的时间
        String begin="2020-05-27 14:00:00";
        //把string类型转换为long类型的
        long st = sim.parse(begin).getTime();
        long en = new Date().getTime();
        //1秒：1000 1分钟：60000;1小时:3600000；1天为86400000
        //计算天数
        System.out.println(en-st);
        int day=(int) ((en-st)/86400000);
        //计算小时
        int h=(int) (((en-st)%86400000)/3600000);
        //计算分钟
        int m=(int)(((en-st)%86400000)%3600000)/60000;
        //计算秒
        int s=(int)((((en-st)%86400000)%3600000)%60000)/1000;
        System.out.println("day:"+day+"hour:"+h+"minuters:"+m+"seconds:"+s);

    }


    /**
     * 获取两个时间之前相差多少年
     * @param smdate 当前天
     * @param bdate
     * @return
     */
    public static int getDistanceDays(Date smdate, Date bdate){
        long smTime = smdate.getTime();
        long bTime = bdate.getTime();
        long time = Math.abs(smTime - bTime);
        int day = (int)(time/12/30/24/60/60/1000);
        return day;
    }

    public static  int[]  getDateLength(String  fromDate, String  toDate)  {
        Calendar  c1  =  getCal(fromDate);
        Calendar  c2  =  getCal(toDate);
        int[]  p1  =  {  c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH)  };
        int[]  p2  =  {  c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DAY_OF_MONTH)  };
        return  new  int[]  {  p2[0]  -  p1[0], p2[0]  *  12  +  p2[1]  -  p1[0]  *  12  -  p1[1], (int)  ((c2.getTimeInMillis()  -  c1.getTimeInMillis())  /  (24  *  3600  *  1000))  };
    }
    static  Calendar  getCal(String  date)  {
        Calendar  cal  =  Calendar.getInstance();
        cal.clear();
        cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6))  -  1, Integer.parseInt(date.substring(6, 8)));
        return  cal;
    }


    /**
     * 字符串转时间
     * @param param
     * @return
     */
    public static Date parseDate(String param, String format){
        Date date = null;
        try{
            if (param != null && !"".equals(param)){
                SimpleDateFormat sdf =   new SimpleDateFormat(format);
                date = sdf.parse(param);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String DateToStr(Date date, String format) {
        String str = "";
        if (date == null){
            return str;
        }
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            str = sdf.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
    /**
         * @param
         * @throws java.text.ParseException
         * @描述 —— 获取某年某月中周一的总数【返回-1表示传入参数有误！！！】
         */
    private static int getMondayNumByYearMonth(int year, int month)
            throws ParseException {
        if (month >= 13 || month <= 0) {
            System.out.println("月份不存在！");
        } else {
            Date date = getLastDayOfYearMonth(year, month);
            // 当前月份的总天数
            int jiequ_tians = Integer.parseInt(new SimpleDateFormat("dd")
                    .format(date));
            int tongji = 0;
            date = getFirstDayOfYearMonth(year, month);
            for (int i = 0; i < jiequ_tians; i++) {
                Date temp = getDateFromSourceDate(date, i);
                if (getWeekNumByDate(temp) == 1) {
                    do {
                        tongji++;
                        i += 7;
                    } while (i < jiequ_tians);
                }
            }
            return tongji;
        }
        return -1;
    }

    /**
     * @param year
     * @param month
     * @param week
     * @return
     * @描述 —— 获取指定年月周的周一日期【返回null表示出入参数有误！！！】
     */
    public static Date getFirstDateByYearMonthWeek(int year, int month, int week) {
        if (month >= 13 || month <= 0) {
            System.out.println("月份不存在！");
        } else {
            Date date = getLastDayOfYearMonth(year, month);
            // 当前月份的总天数
            int jiequ_tians = Integer.parseInt(new SimpleDateFormat("dd")
                    .format(date));
            int tongji = 0;
            date = getFirstDayOfYearMonth(year, month);
            for (int i = 0; i < jiequ_tians; i++) {
                // 从当月1号开始到当月最后一天遍历数据
                Date temp = getDateFromSourceDate(date, i);
                if (getWeekNumByDate(temp) == 1) {
                    // 一旦发现有周一直接加上7
                    do {
                        tongji++;
                        if (tongji == week) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, month - 1);
                            calendar.set(Calendar.DAY_OF_MONTH, i + 1);
                            return calendar.getTime();
                        }
                        i += 7;
                    } while (i < jiequ_tians);
                }
            }
        }
        return null;
    }

    /**
     * @param date
     * @return
     * @描述 —— 获取日期所在周的第一天
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
      /*  if(dayOfWeek==1){
            dayOfWeek=7;
        }else{
            dayOfWeek=dayOfWeek-1;
        }*/
        System.out.println("==================:"+dayOfWeek);
        Calendar calFirstDayInThisWeek = (Calendar) cal.clone();
        calFirstDayInThisWeek.add(Calendar.DATE, cal
                .getActualMinimum(Calendar.DAY_OF_WEEK)
                - dayOfWeek);
        Date dateTemp = calFirstDayInThisWeek.getTime();

        return getDateFromSourceDate(dateTemp, 1);
    }

    /**
     * @param date
     * @return
     * @描述 —— 获取日期所在周的最后一天
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        int dayOfWeek= cal.get(Calendar.DAY_OF_WEEK);
       /* if(dayOfWeek==1){
            dayOfWeek=7;
        }else{
            dayOfWeek=dayOfWeek-1;
        }*/
        System.out.println("*******************:"+dayOfWeek);
        Calendar calLastDayInThisWeek = (Calendar) cal.clone();
        calLastDayInThisWeek.add(Calendar.DATE, cal
                .getActualMaximum(Calendar.DAY_OF_WEEK)
                - dayOfWeek);

        Date dateTemp = calLastDayInThisWeek.getTime();
        return getDateFromSourceDate(dateTemp, 1);
    }


    public static Date getFirstDayOfYearMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        // 当前月的第一天
        cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }


    public static Date getLastDayOfYearMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        // 当前月的最后一天
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        return cal.getTime();
    }


    public static int getWeekNumByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return weekDay == 1 ? 7 : weekDay - 1;
    }


    public static Date getDateFromSourceDate(Date currentDate, int num) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(currentDate);
        cal.add(GregorianCalendar.DATE, num);
        return cal.getTime();
    }

    /**
     * 获取环比日期（之前的日期）
     *
     * @param startDateStr 开始时间
     * @param endDateStr   结束时间
     * @return beforeDate
     */
    public static String getChainTime(String startDateStr, String endDateStr) {
        //指定转换格式
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //进行转换
        LocalDate startDate = LocalDate.parse(startDateStr, fmt);
        LocalDate endDate = LocalDate.parse(endDateStr, fmt);
        //相差天数
        int day = (int) (endDate.toEpochDay() - startDate.toEpochDay());
        //如果相差0天 返回前一天日期
//        if (day == 0) {
//            day = 1;
//        }
        String beforeDate = startDate.minusDays(day + 1).toString();
        return beforeDate;
    }

    /**
     * 获取上月的同比开始时间
     * @param startTime
     * @return
     */
    public static Date getTbStartTime(Date startTime){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.MONTH, -1);
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取上月的同比结束日期
     * @param tbStartTime
     * @param startTime
     * @param endTime
     * @return
     */
    public static Date getTbEndTime(Date tbStartTime,Date startTime,Date endTime){
        Long day = (endTime.getTime() - startTime.getTime())/(60*60*24*1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tbStartTime);
        calendar.add(Calendar.DATE, day.intValue());
        return calendar.getTime();
    }

    /**
     * 获取以月为单位的同比日期
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    public static String getMomTime(String startDateStr,String endDateStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        try {
            bef.setTime(sdf.parse(startDateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            aft.setTime(sdf.parse(endDateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        System.out.println(Math.abs(month + result));
        int m = Math.abs(month + result);

        Calendar tempTime = Calendar.getInstance();
        try {
            tempTime.setTime(sdf.parse(startDateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tempTime.add(tempTime.MONTH,- (m + 1));
        System.out.println(sdf.format(tempTime.getTime()));
        return sdf.format(tempTime.getTime()) + "-01";
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
        //                      此处修改为+1则是获取后一天
        calendar.set(Calendar.DATE,day-1);

        String lastDay = sdf.format(calendar.getTime());
        return lastDay;
    }

    /**
     * 获取当前系统的时间
     * @return
     */
    public static String getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSS);
        return df.format(new Date());
    }


    /**
     * 将yyyy-MM-dd HH:mm:ss日期格式化成yyyy-MM-dd
     * @return
     */
    public static Date getFormatDate(String time){
        try{
            SimpleDateFormat df = new SimpleDateFormat(YYYYMMDD);
            Date date = df.parse(time);
            return date;
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取指定日期的前几天
     * @param currentTime
     * @param days
     * @return
     */
    public static String getFirstDays(String currentTime,int days){
        try{
            //日期格式
            DateFormat format = new SimpleDateFormat(YYYYMMDDHHMMSS);
            Date date = format.parse(currentTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, days);
            Date d = calendar.getTime();
            String strDate = format.format(d);
            return strDate;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
            DateFormat format = new SimpleDateFormat(YYYYMM);
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
     * 计算两个时间差
     * @param beginDateStr
     * @param endDateStr
     * @return
     */
    public static long getDaySub(Date beginDateStr,Date endDateStr) {

        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        day = (endDateStr.getTime()-beginDateStr.getTime())/(24*60*60*1000);
        return day;
    }

    // 字符串 转 日期
    public static Date strToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.getErrorOffset();
        }
        return date;
    }


      /**
      * 判断时间是否在时间段内
      * 
      * @param nowTime
      * @param beginTime
      * @param endTime
      * @return
      */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static float decimalFormat(String pattern, double value) {
        return Float.parseFloat(new DecimalFormat(pattern).format(value));
    }

    public static LocalDate dateToLocalDate(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static float differentDaysByMillisecond(Date date1, Date date2) {
        long diffSeconds = date2.getTime() - date1.getTime();
        long diffHour = diffSeconds / (1000 * 3600);
        System.out.println("获得小时:" + diffHour);
        float diffDay = (float) diffHour / 24;
        float floatDay = decimalFormat("0.00", diffDay);
        System.out.println(decimalFormat("0", diffDay));
        return floatDay;
    }

    public static Date getTbDate(String tempDate){
        try {
            return getTbDate(new SimpleDateFormat("yyyy-MM-dd").parse(tempDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getTbDate(Date tempDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String fmtDate = (Integer.valueOf(simpleDateFormat.format(tempDate)) - 1) + "-" + new SimpleDateFormat("MM").format(tempDate);

        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(fmtDate + "-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main2(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String str1 = "2020-11";
        String str2 = "2020-11";
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        try {
            bef.setTime(sdf.parse(str1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            aft.setTime(sdf.parse(str2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        System.out.println(Math.abs(month + result));
        int m = Math.abs(month + result);

        Calendar tempTime = Calendar.getInstance();
        try {
            tempTime.setTime(sdf.parse(str1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tempTime.add(tempTime.MONTH,- (m + 1));
        System.out.println(sdf.format(tempTime.getTime()));

        System.out.println(DateUtils.getMomTime(str1, str2));


        Date date = DateUtils.getTbDate(new Date());
//        System.out.println( new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    public static Date getUpMonth(){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }


    public static void convertTimeBySearchType(StringBuilder startTime,StringBuilder endTime,String searchType,int year,int keyValue){
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
        }else if ("month".equals(searchType)){//月度
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,keyValue - 1);
            int first = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH,first);
            start = simpleDateFormat.format(calendar.getTime());

            int last = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH,last);
            end = simpleDateFormat.format(calendar.getTime());

        }else if ("quarter".equals(searchType) || "curQuarter".equals(searchType)){//季度
            if(keyValue == 1){
                calendar.set(year, 0, 1, 00, 00, 00);
                start = simpleDateFormat.format(calendar.getTime());
                calendar.set(year, 2, 31, 23, 59, 59);
                end = simpleDateFormat.format(calendar.getTime());
            }else if(keyValue == 2){
                calendar.set(year, 3, 1, 00, 00, 00);
                start = simpleDateFormat.format(calendar.getTime());
                calendar.set(year, 5, 30, 23, 59, 59);
                end = simpleDateFormat.format(calendar.getTime());
            }
            else if(keyValue == 3){
                calendar.set(year, 6, 1, 00, 00, 00);
                start = simpleDateFormat.format(calendar.getTime());
                calendar.set(year, 8, 30, 23, 59, 59);
                end = simpleDateFormat.format(calendar.getTime());
            }
            else if(keyValue == 4){
                calendar.set(year, 9, 1, 00, 00, 00);
                start = simpleDateFormat.format(calendar.getTime());
                calendar.set(year, 11, 31, 23, 59, 59);
                end = simpleDateFormat.format(calendar.getTime());
            }
        }else if ("curYear".equals(searchType)){//本年
            calendar.setTime(new Date());
            year = calendar.get(Calendar.YEAR);
            start = year + "-01-01";
            end = year + "-12-31";
        }

        try {
            if (!"".equals(end)){
                if (simpleDateFormat.parse(end).after(new Date())) {
                    end = simpleDateFormat.format(new Date());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        startTime = startTime.delete(0,startTime.length()).append(start);
        endTime = endTime.delete(0,endTime.length()).append(end);
    }

    public static void convertTimeBySearchType(StringBuilder startTime,StringBuilder endTime,String searchType){
        convertTimeBySearchType(startTime,endTime,searchType,-1,-1);
    }

    /**
     * 获取本年度第一天
     * @param date
     * @return
     */
    public static Date getCurYear(Date date){
        try {
           return new SimpleDateFormat("yyyy-mm-dd").parse(new SimpleDateFormat("yyyy").format(date) + "-01-01");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Date getUpYear(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR,-1);
        return calendar.getTime();
    }

    /**
     * 获取上月的第一天
     * @param date
     * @return
     */
    public static Date getUpMonthFirst(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }

    /**
     * 获取上月的最后一天
     * @param date
     * @return
     */
    public static Date getUpMonthLast(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取当月第一天
     * @param date
     * @return
     */
    public static Date getCurMonthFirst(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当月的最后一天
     * @param date
     * @return
     */
    public static Date getCurMonthLast(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(getUpMonthLast(new Date())));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(getCurMonthLast(new Date())));

    }
}
