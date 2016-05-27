package com.bl.common.utils.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * 日期工具类，提供有关日期操作方面的方法。
 */

public class DateUtil {
	public static final int PROMOTION_STATUS_PAST = 0;
	public static final int PROMOTION_STATUS_RUNNING = 1;
	public static final int PROMOTION_STATUS_NO_RUNING = 2;

	/**
	 * 时间格式
	 */
	public final static String TIME_FORMAT = "HH:mm:ss:SS";

	/**
	 * 缺省短日期格式
	 */
	public final static String DEFAULT_SHORT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * yyyy-MM-dd HH:mm:ss格式数据。
	 */
	public final static String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd格式数据。
	 */
	public final static String DATE_ONLY_FORMAT = "yyyy-MM-dd";
	/**
	 * 缺省短日期格式
	 */
	public final static String DEFAULT_SHORT_DATE_FORMAT_ZH = "yyyy年M月d日";

	/**
	 * 缺省长日期格式
	 */
	public final static String DEFAULT_LONG_DATE_FORMAT = DEFAULT_SHORT_DATE_FORMAT + " " + TIME_FORMAT;

	/**
	 * Java能支持的最小日期字符串（yyyy-MM-dd）。
	 */
	public final static String JAVA_MIN_SHORT_DATE_STR = "1970-01-01";

	/**
	 * Java能支持的最小日期字符串（yyyy-MM-dd HH:mm:ss:SS）。
	 */
	public final static String JAVA_MIN_LONG_DATE_STR = "1970-01-01 00:00:00:00";

	/**
	 * Java能支持的最小的Timestamp。
	 */
	public final static Timestamp JAVA_MIN_TIMESTAMP = convertStrToTimestamp(JAVA_MIN_LONG_DATE_STR);

	/**
	 * 比较当前时间是否在beginDate与endDate两个日期之间。
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean checkCurDateBetween(String beginDate, String endDate) {
		long beginDateTime = convertDateStrToMilliSeconds(beginDate, DEFAULT_DATE_TIME_FORMAT);
		long endDateTime = convertDateStrToMilliSeconds(endDate, DEFAULT_DATE_TIME_FORMAT);
		long curDateTime = System.currentTimeMillis();

		if (curDateTime >= beginDateTime && curDateTime <= endDateTime) {
			return true;
		}
		return false;
	}

	/**
	 * 获取当前日期的上一周星期一的日期。注意只返回yyyy-MM-dd格式的数据。
	 * 
	 * @return
	 */
	public static String getMondayDateForLastWeek() {
		Calendar cal = Calendar.getInstance();
		// n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		int n = -1;
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.add(Calendar.DATE, n * 7);
		// 想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 获取当前日期的上一周星期日的日期。注意只返回yyyy-MM-dd格式的数据。
	 * 
	 * @return
	 */
	public static String getSundayDateForLastWeek() {
		Calendar cal = Calendar.getInstance();
		// n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		int n = -1;
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.add(Calendar.DATE, n * 7);
		// 想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	public static String getNameByDate(String date) {
		// 从今天、昨天、前天三个角度来判断。

		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_SHORT_DATE_FORMAT);
		// 今天
		// String currentDay=
		return null;
	}

	public static String formatDate(String dateStr, String formatStyle) {
		Date date = convertStrToDate(dateStr, formatStyle);
		dateStr = convertDateToStr(date, formatStyle);
		return dateStr;
	}

	/**
	 * 取得指定日期所在周的第一天
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 取得指定日期所在周的最后一天
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 获取指定日期在当年中的所在周数。
	 * 
	 * @param dateStr
	 *            年月日 时分秒。
	 */
	public static int getWeekOfYearByDate(String dateStr) {
		Calendar calendar = Calendar.getInstance();// new GregorianCalendar();
		Date date = DateUtil.convertStrToDate(dateStr, DateUtil.DEFAULT_DATE_TIME_FORMAT);
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 把字符串转换为Timestamp类型，对于短日期格式，自动把时间设为系统当前时间。
	 * 
	 * @return Timestamp
	 * @see #convertStrToTimestamp(String,boolean)
	 */
	public static Timestamp convertStrToTimestamp(String dateStr) {
		return convertStrToTimestamp(dateStr, false);
	}

	/**
	 * 把字符串转换为Timestamp类型，对于短日期格式，自动把时间设为0。
	 * 
	 * @return Timestamp
	 * @see #convertStrToTimestamp(String,boolean)
	 */
	public static Timestamp convertStrToTimestampZero(String dateStr) {
		return convertStrToTimestamp(dateStr, true);
	}

	/**
	 * 把字符串转换为Timestamp类型。
	 * 
	 * @param dateStr
	 *            - 日期字符串，只支持"yyyy-MM-dd"和"yyyy-MM-dd HH:mm:ss:SS"两种格式。
	 *            如果为"yyyy-MM-dd"，系统会自动取得当前时间补上。
	 * @param addZeroTime
	 *            - 当日期字符串为"yyyy-MM-dd"这样的格式时，addZeroTime为true表示
	 *            用0来设置HH:mm:ss:SS，否则用当前Time来设置。
	 * @return Timestamp
	 */
	private static Timestamp convertStrToTimestamp(String dateStr, boolean addZeroTime) {
		if (dateStr == null) {
			return null;
		}

		String dStr = dateStr.trim();
		if (dStr.indexOf(" ") == -1) {
			if (addZeroTime) {
				dStr = dStr + " 00:00:00:00";
			} else {
				dStr = dStr + " " + getCurrDateStr(DateUtil.TIME_FORMAT);
			}
		}

		Date utilDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_LONG_DATE_FORMAT);

		try {
			utilDate = simpleDateFormat.parse(dStr);
		} catch (Exception ex) {
			throw new RuntimeException("DateUtil.convertStrToTimestamp(): " + ex.getMessage());
		}

		return new Timestamp(utilDate.getTime());
	}

	/**
	 * 得到系统当前时间的Timestamp对象
	 * 
	 * @return 系统当前时间的Timestamp对象
	 */
	public static Timestamp getCurrTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * <p>
	 * 取得当前日期，并将其转换成格式为"dateFormat"的字符串 例子：假如当前日期是 2003-09-24 9:19:10，则：
	 * 
	 * <pre>
	 * getCurrDateStr("yyyyMMdd")="20030924"
	 * getCurrDateStr("yyyy-MM-dd")="2003-09-24"
	 * getCurrDateStr("yyyy-MM-dd HH:mm:ss")="2003-09-24 09:19:10"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param dateFormat
	 *            String 日期格式字符串
	 * @return String
	 */
	public static String getCurrDateStr(String dateFormat) {
		return convertDateToStr(new Date(), dateFormat);
	}

	/**
	 * 将日期类型转换成指定格式的日期字符串
	 * 
	 * @param date
	 *            待转换的日期
	 * @param dateFormat
	 *            日期格式字符串
	 * @return String
	 */
	public static String convertDateToStr(Date date, String dateFormat) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * 将指定格式的字符串转换成日期类型
	 * 
	 * @param date
	 *            待转换的日期字符串
	 * @param dateFormat
	 *            日期格式字符串
	 * @return Date
	 */
	public static Date convertStrToDate(String dateStr, String dateFormat) {
		if (dateStr == null || dateStr.equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			return sdf.parse(dateStr);
		} catch (Exception e) {
			throw new RuntimeException("DateUtil.convertStrToDate():" + e.getMessage());
		}
	}

	/**
	 * 计算两个日期之间的相隔的年、月、日。注意：只有计算相隔天数是准确的，相隔年和月都是 近似值，按一年365天，一月30天计算，忽略闰年和闰月的差别。
	 * 
	 * @param datepart
	 *            两位的格式字符串，yy表示年，MM表示月，dd表示日
	 * @param startdate
	 *            开始日期
	 * @param enddate
	 *            结束日期
	 * @return double 如果enddate>startdate，返回一个大于0的实数，否则返回一个小于等于0的实数
	 */
	public static double dateDiff(String datepart, Date startdate, Date enddate) {
		if (datepart == null || datepart.equals("")) {
			throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值：" + datepart);
		}

		double days = (double) (enddate.getTime() - startdate.getTime()) / (60 * 60 * 24 * 1000);

		if (datepart.equals("yy")) {
			days = days / 365;
		} else if (datepart.equals("MM")) {
			days = days / 30;
		} else if (datepart.equals("dd")) {
			return days;
		} else {
			throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值：" + datepart);
		}
		return days;
	}

	/**
	 * 把日期对象加减年、月、日后得到新的日期对象
	 * 
	 * @param depart
	 *            年、月、日、时、分(yy\MM\dd\hh\mm)
	 * @param number
	 *            加减因子
	 * @param date
	 *            需要加减年、月、日的日期对象
	 * @return Date 新的日期对象
	 */
	public static Date addDate(String datepart, int number, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (datepart.equals("yy")) {
			cal.add(Calendar.YEAR, number);
		} else if (datepart.equals("MM")) {
			cal.add(Calendar.MONTH, number);
		} else if (datepart.equals("dd")) {
			cal.add(Calendar.DATE, number);
		} else if (datepart.equals("hh")) { // 新加设置时间的方法
			cal.add(Calendar.HOUR, number);
		} else if (datepart.equals("mm")) { // 新加设置分钟的方法
			cal.add(Calendar.MINUTE, number);
		} else {
			throw new IllegalArgumentException("DateUtil.addDate()方法非法参数值：" + datepart);
		}

		return cal.getTime();
	}

	/**
	 * 将普通时间 格式的字符串转化成unix时间戳
	 * 
	 * @param timeStamp
	 * @param dateFormat
	 * @return
	 */
	public static long convertDateStrToUnixTimeStamp(String dateStr, String dateFormat) {
		if (StringUtils.isBlank(dateStr)) {
			return 0;
		}
		long timeStamp = DateUtil.convertStrToDate(dateStr, dateFormat).getTime();
		return timeStamp / 1000;
	}

	/**
	 * 将普通时间 格式的字符串转化成unix时间戳 单位为毫秒
	 * 
	 * @param timeStamp
	 * @param dateFormat
	 * @return
	 */
	public static long convertDateStrToMilliSeconds(String dateStr, String dateFormat) {
		if (StringUtils.isBlank(dateStr)) {
			return 0;
		}
		long timeStamp = DateUtil.convertStrToDate(dateStr, dateFormat).getTime();
		return timeStamp;
	}

	/**
	 * 将unix时间戳转化成普通时间 格式的字符串
	 * 
	 * @param timeStamp
	 * @param dateFormat
	 * @return
	 */
	public static String convertUnixTimeStampToDateStr(long timeStamp, String dateFormat) {
		if (timeStamp == 0) {
			return "";
		}
		Long timestamp = Long.parseLong(timeStamp + "") * 1000;
		String dateStr = DateUtil.convertDateToStr(new Date(timestamp), dateFormat);
		return dateStr;
	}

	/**
	 * 获取当前unix时间的秒数。
	 * 
	 * @return
	 */
	public static long getCurrentUnixTimeSecond() {
		return getCurrTimestamp().getTime() / 1000;
	}

	/**
	 * 0为已经过期了，1为进行中。2为尚未进行。 如果两个日期都为null，则认为正在进行中。。
	 * 
	 * @return
	 */
	public static int validCurrDateInRange(String startDate, String endDate) {
		if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
			throw new IllegalArgumentException("抱歉，开始日期与结束日期不能同时为空！");
		}

		long currDateSecond = getCurrentUnixTimeSecond();

		long startDateSecond = -1;
		long endDateSecond = -1;

		if (!StringUtils.isBlank(startDate)) {
			startDateSecond = convertDateStrToUnixTimeStamp(startDate, DEFAULT_DATE_TIME_FORMAT);
		}
		if (!StringUtils.isBlank(endDate)) {
			endDateSecond = convertDateStrToUnixTimeStamp(endDate, DEFAULT_DATE_TIME_FORMAT);
		}

		if (startDateSecond < 1 && endDateSecond < 1) {
			throw new IllegalArgumentException("抱歉，参数不正确！");
		}

		// 当前日期与两个时间进行相减对比。
		long startDateCha = currDateSecond - startDateSecond;
		long endDateCha = endDateSecond - currDateSecond;

		// 判断参数是否传null过来，如果不是传null进来，则判断传值的合法性。
		if (startDateSecond > 0 && endDateSecond > 0) {
			long temp = endDateSecond - startDateSecond;
			if (temp < 0) {
				throw new IllegalArgumentException("抱歉，数据不合理，开始时间超过了结束时间.");
			} else if (startDateCha >= 0 && endDateCha >= 0) {
				return PROMOTION_STATUS_RUNNING;// 正在进行中。
			} else if (startDateCha < 0 && endDateCha >= 0) {
				return PROMOTION_STATUS_NO_RUNING;// 尚未进行
			}
		} else if (startDateSecond > 0) {

			if (startDateCha >= 0) {
				return PROMOTION_STATUS_RUNNING;
			} else {
				return PROMOTION_STATUS_NO_RUNING;
			}
		} else if (endDateSecond > 0) {

			if (endDateCha >= 0) {
				return PROMOTION_STATUS_RUNNING;
			}
		}

		return PROMOTION_STATUS_PAST;

	}

	/**
	 * 当天的开始时间
	 * 
	 * @return
	 */
	public static String getCurDateStart() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return formatter.format(new Date());
	}

	/**
	 * 当天的结束时间
	 * 
	 * @return
	 */
	public static String getCurDateEnd() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 59:59:59");
		return formatter.format(new Date());
	}

	/**
	 * 计算2个日期之间的间隔的周期数
	 * 
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @param msPeriod
	 *            单位周期的毫秒数
	 * @return 周期数
	 */
	public static long getPeriodNum(Date start, Date end, long msPeriod) {
		return getIntervalMs(start, end) / msPeriod;
	}

	/**
	 * 计算2个日期之间的毫秒数
	 * 
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @return 毫秒数
	 */
	public static long getIntervalMs(Date start, Date end) {
		return end.getTime() - start.getTime();
	}

	/**
	 * 将小时数转换成天数
	 * 
	 * @param hour
	 * @return
	 */
	public static String convertHourToDayStr(int hour) {
		if (hour < 24) {
			return hour + "小时";
		}

		int day = hour / 24;
		int h = hour % 24;
		if (h == 0) {
			return day + "天";
		}
		return day + "天" + h + "小时";
	}
	
	/**
	 * 将日期转换成默认的yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param hour
	 * @return
	 */
	public static String convertDateToString(Date date) {
		return DateUtil.convertDateToStr(date, DateUtil.DEFAULT_DATE_TIME_FORMAT);
	}
}