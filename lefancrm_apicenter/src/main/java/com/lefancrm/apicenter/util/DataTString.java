package com.lefancrm.apicenter.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DataTString {
		public static String DateToStr(Date date) {
			   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   String str = format.format(date);
			   return str;
			}
		public static String DateToStrs(Date date) {
			   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			   String str = format.format(date);
			   return str;
			}
		public static String DateaddToStr(Date date){
			 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		     Calendar c = Calendar.getInstance();
		     c.add(Calendar.DAY_OF_MONTH, 1);
		     c.add(Calendar.DAY_OF_MONTH, 1);
		     
			return "";
			
		}
		public static Date Strtodate(String date){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dates=null;
			try {
				dates = format.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dates;
		}
		public static String addDay(String date){
			Date date2=Strtodate(date);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		    Calendar c = Calendar.getInstance();
		    c.set(date2.getYear(), date2.getMonth(), date2.getDay());
		    c.add(Calendar.DAY_OF_MONTH, 1);
		    return sf.format(c.getTime());
		}
		public static String addyear(String date){
			Date date2=Strtodate(date);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		    Calendar c = Calendar.getInstance();
		    c.set(date2.getYear(), date2.getMonth(), date2.getDay());
		    c.add(Calendar.DAY_OF_MONTH, 1);
		    c.add(Calendar.YEAR, 1);
		    return sf.format(c.getTime());
		}
}
