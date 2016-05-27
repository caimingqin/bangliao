package com.bl.common.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

public class DateFormatFactory {
	
	static Map<String, SimpleDateFormat> dateFormatCache = new HashMap<String, SimpleDateFormat>();
	
	public static final String LONG_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String LONG_DATE = "yyyy-MM-dd";
	
	public static final String defaultDateFormatPattern = LONG_DATETIME;
	
	public static SimpleDateFormat getInstance(String pattern){
		if(StringUtils.isNotBlank(pattern)){
			if(dateFormatCache.containsKey(pattern)){
				return dateFormatCache.get(pattern);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			dateFormatCache.put(pattern, dateFormat);
			return dateFormat;
		}
		return getDefaultDateFormat();
	}
	
	public static SimpleDateFormat getDefaultDateFormat() {
		return DateFormatFactory.getInstance(defaultDateFormatPattern);
	}

	/**
     * 获取日期的yyyy-MM-dd格式
     */
    public static String getYyyyMMdd(Date date) {
        return getInstance(LONG_DATE).format(date);
    }
    
    /**
     * 获取日期的yyyy-MM-dd HH:mm:ss格式
     */
    public static String getYyyyMMddHHmmss(Date date) {
        return getInstance(LONG_DATETIME).format(date);
    }
    
    /**
     * 获取日期的yyyy-MM-dd HH:mm格式
     */
    public static String getYyyyMMddHHmm(Date date) {
        return getInstance("yyyy-MM-dd HH:mm").format(date);
    }
    
    /**
     * 转换日期字符串(日期格式yyyy-MM-dd HH:mm)为Date对象
     */
    public static Date getYyyyMMddHHmm(String date) throws ParseException {
        return getInstance("yyyy-MM-dd HH:mm").parse(date);
    }
    
    /**
     * 转换日期字符串(日期格式yyyy-MM-dd)为Date对象
     */
    public static Date getYyyyMMdd(String date) throws ParseException{
        return getInstance(LONG_DATE).parse(date);
    }
    
    /**
     * 转换日期字符串(日期格式yyyy-MM-dd)为Date对象
     */
    public static Date getYyyyMMddHHmmss(String date) throws ParseException{
        return getInstance(LONG_DATETIME).parse(date);
    }

    
    /**
     * 转换日期字符串(日期格式yyyy-MM-dd 00:00:01)为Calendar对象
     */
    public static Calendar strToCalendar(String date) throws ParseException{
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(getInstance(LONG_DATETIME).parse(date));
        return cal;
    }
    
    /**
     * 转换日期Date(日期格式yyyy-MM-dd 00:00:01)为Calendar对象
     */
    public static Calendar dateToCalendar(Date date) throws ParseException{
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
        return cal;
    }
    
    /**
     * 转换日期Calender(日期格式yyyy-MM-dd 00:00:01)为String对象
     */
    public static String calendarToStr(Calendar calendar){
    	SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATETIME);
    	sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    	String dateStr = sdf.format(calendar.getTime());
    	return dateStr;
    }
}
