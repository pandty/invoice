package com.invoice.system.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 
 * @author Seyo 1.1 2013-04-07
 */
public class DateHelper {
	private static String yearmonthPattern = "yyyy-MM";

	public static String defaultDatePattern = "yyyy-MM-dd";

	private static String defaultTimePattern = "HH:mm:ss";

	private static String currentDateTimePattern = "yyyyMMddHHmmss";

	public static String dateToString(Object o) {
		if (o == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(o);
	}

	/**
	 * 天数
	 * 
	 * @param sd
	 * @param ed
	 * @return
	 */
	public static int getDays(Integer sd, Integer ed) {
		return (ed - sd) / (3600 * 24);
	}

	public static String dateToString(Object o, String format) {
		if (o == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(o);
	}

	public static Date stringToDate(String dateStr) {
		return stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date stringToDate(String dateStr, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			Date date = df.parse(dateStr);
			return date;
		} catch (Exception ex) {

		}
		return null;
	}

	public static String TimeStamp2Date(String timestampString, String formats) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new SimpleDateFormat(formats)
				.format(new Date(timestamp));
		return date;
	}

	public static int getMonthSpace(String date1, String date2)
			throws ParseException {

		int result = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(sdf.parse(date1));
		c2.setTime(sdf.parse(date2));

		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

		return result == 0 ? 1 : Math.abs(result);

	}

	public static Date stringToDate(String dateStr, String format, Locale local) {
		SimpleDateFormat df = new SimpleDateFormat(format, local);
		try {
			Date date = df.parse(dateStr);
			return date;
		} catch (Exception ex) {

		}
		return null;
	}

	/**
	 * 根据String类型时间转换成DATE类型时间
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date stringToEnDate(String dateStr) {
		return DateHelper.stringToDate(dateStr, "MMM dd, yyyy hh:mm:ss aa",
				new Locale("US"));
	}

	/**
	 * 获取指定时间加上几天后的时间
	 * 
	 * @param date
	 *            指定时间
	 * @param day
	 *            特定增加天数
	 * @return
	 */
	public static Date getDate(Date date, int day) {
		long myTime = (date.getTime() / 1000) + (60 * 60 * 24) * (day);
		Date nDate = new Date(myTime * 1000);
		return nDate;
	}

	/**
	 * 获取指定时间加上对应秒数得出对应的时间
	 * 
	 * @param date
	 *            指定时间
	 * @param seconds
	 *            特定增加秒数
	 * @return
	 */
	public static Date getDateBySeconds(Date date, int seconds) {
		long myTime = (date.getTime() / 1000) + seconds;
		Date nDate = new Date(myTime * 1000);
		return nDate;
	}

	/**
	 * 转换DATE类型为时间戳
	 * 
	 * @param dt
	 *            时间
	 * @return
	 */
	public static int getTimestamp(Date dt) {
		if (dt == null)
			return 0;
		return (int) (stringToDate(dateToString(dt)).getTime() / 1000);
	}

	public static long getTimestampLong(Date dt) {
		if (dt == null)
			return 0;
		return (long) (stringToDate(dateToString(dt)).getTime() / 1000);
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 */
	public static int getTimestampNow() {
		return getTimestamp(new Date());
	}

	public static long getTimestampNowLong() {
		return getTimestampLong(new Date());
	}

	// 查找当前月份
	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH)+1;
		return month;
	}
	// 查找上个月份
	public static int getLastMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		if(month==0){
			month=12;
		}
		return month;
	}

	// 查找当前季度
	public static int getCurrentSeason() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int season = 1;
		if (month > 0 && month < 4) {
			season = 1;
		} else if (month > 3 && month < 7) {
			season = 2;
		} else if (month > 6 && month < 9) {
			season = 3;
		} else {
			season = 4;
		}
		return season;
	}

	// 查找当前年份
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;

	}


	public static Date getDate(int timestamp) {
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String d = format.format(new Date(timestamp));
		// Date date = null;
		// try {
		// date = format.parse(d);
		// } catch (ParseException e) {
		// }
		return new Date(Long.parseLong(String.valueOf(timestamp)) * 1000);
	}
	public static Date getDate(long timestamp) {
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String d = format.format(new Date(timestamp));
		// Date date = null;
		// try {
		// date = format.parse(d);
		// } catch (ParseException e) {
		// }
		return new Date(Long.parseLong(String.valueOf(timestamp)) * 1000);
	}

	/**
	 * 时间类型转换
	 * 
	 * @param timestampString
	 * @return
	 */
	public static String TimeStamp2Date(String timestampString) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
				.format(new Date(timestamp));
		return date;
	}
	
	/**
	* 获取两个日期相差的月数
	* @param d1  较大的日期
	* @param d2  较小的日期
	* @return 如果d1>d2返回 月数差 否则返回0
	*/
	public static int getMonthDiff(Date endDay, Date beginDay) {
	    Calendar c1 = Calendar.getInstance();
	    Calendar c2 = Calendar.getInstance();
	    c1.setTime(endDay);
	    c2.setTime(beginDay);
	    if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
	    int year1 = c1.get(Calendar.YEAR);
	    int year2 = c2.get(Calendar.YEAR);
	    int month1 = c1.get(Calendar.MONTH);
	    int month2 = c2.get(Calendar.MONTH);
	    int day1 = c1.get(Calendar.DAY_OF_MONTH);
	    int day2 = c2.get(Calendar.DAY_OF_MONTH);
	    // 获取年的差值 假设 d1 = 2015-8-16 d2 = 2011-9-30
	    int yearInterval = year1 - year2;
	    // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
	    if(month1 < month2 || month1 == month2 && day1 < day2) yearInterval --;
	    // 获取月数差值
	    int monthInterval = (month1 + 12) - month2 ;
	    if(day1 < day2) monthInterval --;
	    monthInterval %= 12;
	    return yearInterval * 12 + monthInterval;
	}

	public static void main(String[] args) throws ParseException {

//		Calendar cal = Calendar.getInstance();
		int month=getMonthDiff(getDate(1559318400),getDate(1514736000));
	
		System.out.println(month);
	}
//	public static void main(String[] args) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
//        Date date = new Date();
// 
//        System.out.println("当前时间是：" + dateFormat.format(date));
// 
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date); // 设置为当前时间
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 2); // 设置为上一个月
//        date = calendar.getTime();
// 
//        System.out.println("上一个月的时间： " + calendar.g);
//        System.out.println("上一个月的时间： " + dateFormat.format(date));
//    }

	/** yyyy-MM-dd */
	public static final String DATE_FULLHYPHEN = "yyyy-MM-dd";

	/**
	 * 日期相减/相加
	 * 
	 * @param intervals
	 * @param format
	 * @return
	 */
	public static String getDiffDate(int intervals, String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, intervals);
		Date intervalDay = cal.getTime();
		return dateToString(intervalDay, format);
	}

	public static Date getMonth(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	public static Date getYear(Date date, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		return cal.getTime();
	}

	public static int getDateInternal(Date d1, Date d2) {
		int internal = 0;
		long internalTime = Math.abs((d1.getTime() - d2.getTime()) / 1000);
		int oneDay = 86400;
		internal = (int) internalTime / oneDay;
		if (internalTime % oneDay > 0)
			internal++;
		return internal;
	}

	public static Date getHour(Date date, int hour) {
		long myTime = (date.getTime() / 1000) + (60 * 60) * (hour);
		Date nDate = new Date(myTime * 1000);
		return nDate;
	}

	public static Date getMinute(Date date, int minute) {
		long myTime = (date.getTime() / 1000) + (60) * (minute);
		Date nDate = new Date(myTime * 1000);
		return nDate;
	}

	public static boolean isSameDate(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
				&& (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
				&& (c1.get(Calendar.DAY_OF_MONTH) == c2
						.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 将日期转换成指定格式的字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null | pattern.length() == 0) {
			return null;
		}
		SimpleDateFormat fmt = new SimpleDateFormat(pattern);
		String convStr = fmt.format(date);
		return convStr;
	}

	/**
	 * 获取日期中的年份,如(2008-8-10中的2008)
	 * 
	 * @param date
	 *            日期
	 * @return int
	 * @throws ParseException
	 */
	public static int yearOfDate(Date date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(defaultDatePattern);
		String d = format.format(date);
		return Integer.parseInt(d.substring(0, 4));
	}

	/**
	 * 获取日期中的月份,如(2008-8-10中的8)
	 * 
	 * @param date
	 *            日期
	 * @return int
	 * @throws ParseException
	 */
	public static int monthOfDate(Date date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(defaultDatePattern);
		String d = format.format(date);
		return Integer.parseInt(d.substring(5, 7));
	}

	/**
	 * 获取日期中的一天,如(2008-8-10中的10)
	 * 
	 * @param date
	 *            日期
	 * @return int
	 * @throws ParseException
	 */
	public static int dayOfDate(Date date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(defaultDatePattern);
		String d = format.format(date);
		return Integer.parseInt(d.substring(8, 10));
	}

	/**
	 * 获取日期中的小时,如(2008-8-10 14:28:10中的14)
	 * 
	 * @param date
	 *            日期
	 * @return int
	 * @throws ParseException
	 */
	public static int hhOfDate(Date date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(date);
		return Integer.parseInt(d.substring(11, 13));
	}

	/**
	 * 获取某年某月的第一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getFirstDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(defaultDatePattern);
		return format.parse(year + "-" + month + "-1");
	}

	/**
	 * 获取某年某月的最后一天的日期
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getLastDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(defaultDatePattern);
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	/**
	 * 获取两个日期相隔的时间数,即相隔多少个小时
	 * 
	 * @param startDate
	 *            开始时间 yyyy-MM-dd HH:mm:ss
	 * @param endDate
	 *            结束时间 yyyy-MM-dd HH:mm:ss
	 * @return long
	 * @throws ParseException
	 */
	public static long getDistinceTime(String startDate, String endDate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timeCount = 0;
		try {
			Date d1 = d.parse(startDate);
			Date d2 = d.parse(endDate);

			timeCount = (d2.getTime() - d1.getTime()) / (60 * 60 * 1000);

		} catch (ParseException e) {
			System.out.println("Date parse error!");
			throw e;
		}
		return timeCount;
	}

	/**
	 * 获取两个日期相隔的时间数,即相隔多少个小时
	 * 
	 * @param startDate
	 *            开始时间 yyyy-MM-dd HH:mm:ss
	 * @param endDate
	 *            结束时间 yyyy-MM-dd HH:mm:ss
	 * @return long
	 * @throws ParseException
	 */
	public static long getDistinceTimeNEW(String startDate, String endDate)
			throws ParseException {
		long timeCount = 0;
		try {
			Date d1 = DateHelper.getDate(Integer.parseInt(startDate));
			Date d2 = DateHelper.getDate(Integer.parseInt(endDate));

			timeCount = (d2.getTime() - d1.getTime()) / (60 * 60 * 1000);

		} catch (Exception e) {
			System.out.println("Date parse error!");
		}
		return timeCount;
	}

	/**
	 * 在日期上增加数个年
	 */
	public static Date addYear(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个整月
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个整天
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个小时
	 */
	public static Date addHour(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, n);
		return cal.getTime();
	}

	/**
	 * 在日期上分钟
	 */
	public static Date addMin(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, n);
		return cal.getTime();
	}

	/**
	 * 在日期上秒
	 */
	public static Date addSecond(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, n);
		return cal.getTime();
	}

	/* 获取指定月有多少天 */
	public static int getMonthCount(String strDate) {
		int day = 0;
		try {
			// String strDate = "2006-9-1";
			SimpleDateFormat sdf = new SimpleDateFormat(defaultDatePattern);
			Calendar calendar = new GregorianCalendar();
			Date date = sdf.parse(strDate);
			calendar.setTime(date);
			int month = calendar.get(Calendar.MONTH) + 1;
			day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			System.out.println(month + "月：" + day + "天");
		} catch (Exception ex) {
		}
		return day;
	}

	public static String getOnlyID() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMDDhhmmssSSS");
		double dblTmp;
		for (dblTmp = Math.random() * 100000D; dblTmp < 10000D; dblTmp = Math
				.random() * 100000D)
			;
		String strRnd = String.valueOf(dblTmp).substring(0, 4);
		String s = df.format(new Date()) + strRnd;
		return s;
	}

	public static Date getLastDayOfMonth(Date sDate1) {
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTime(sDate1);
		final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date lastDate = cDay1.getTime();
		lastDate.setDate(lastDay);
		return lastDate;
	}

	public static Date getFistDayOfMonth(Date sDate1) {
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTime(sDate1);
		final int firstDay = cDay1.getActualMinimum(Calendar.DAY_OF_MONTH);
		Date lastDate = cDay1.getTime();
		lastDate.setDate(firstDay);
		return lastDate;
	}

	public static Date getDayHourFirstSecond(Date date) {
		String dateString = DateHelper.formatDate(date, "yyyy-MM-dd HH");
		return DateHelper.stringToDate(dateString + ":00:00");
	}

	public static Date getDayFirstSecond(Date date) {
		String dateString = DateHelper.formatDate(date, "yyyy-MM-dd");
		return DateHelper.stringToDate(dateString + " 00:00:00");
	}

	public static Date getDayLastSecond(Date date) {
		String dateString = DateHelper.formatDate(date, "yyyy-MM-dd");
		return DateHelper.stringToDate(dateString + " 23:59:59");
	}

	public static Date getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Long getDiffDay(Date beginDate, Date endDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strBeginDate = format.format(beginDate);

		String strEndDate = format.format(endDate);
		return getDiffDay(strBeginDate, strEndDate);
	}

	/**
	 * 获取日期相差天数
	 * 
	 * @param
	 * @return 日期类型时间
	 * @throws ParseException
	 */
	public static Long getDiffDay(String beginDate, String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Long checkday = 0l;
		// 开始结束相差天数
		try {
			checkday = (formatter.parse(endDate).getTime() - formatter.parse(
					beginDate).getTime())
					/ (1000 * 24 * 60 * 60);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			checkday = null;
		}
		return checkday;
	}

	public static long getMinutes(int sd, int ed) {
		return (ed - sd) / 60;
	}

	// 获得当天0点时间
	public static int getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	// 获得当天24点时间
	public static int getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	// 获得本周一0点时间
	public static int getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal
				.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	// 获得本周日24点时间
	public static int getTimesWeeknight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal
				.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return (int) ((cal.getTime().getTime() + (7 * 24 * 60 * 60 * 1000)) / 1000);
	}

	// 获得本月第一天0点时间
	public static int getTimesMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal
				.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		return (int) (cal.getTimeInMillis() / 1000);
	}

	// 获得本月最后一天24点时间
	public static int getTimesMonthnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal
				.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	/**
	 * 在设定某一段时间内遍历每天的时间
	 */
	public void traverseTimes() {
		Calendar start = Calendar.getInstance();
		start.set(2014, 5, 11);
		Long startTIme = start.getTimeInMillis();

		Calendar end = Calendar.getInstance();
		end.set(2014, 7, 11);
		Long endTime = end.getTimeInMillis();

		Long oneDay = 1000 * 60 * 60 * 24l;

		Long time = startTIme;
		while (time <= endTime) {
			Date d = new Date(time);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00"); // 将每天时间设定为0时0分0秒
			System.out.println(df.format(d));
			try {
				Date t = df.parse(df.format(d));
				System.out.println(DateHelper.getTimestamp(t));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			time += oneDay;
		}
	}

	/**
	 * 计算时间差
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static double getDistinceTime(Integer startDate, Integer endDate)
			throws ParseException {
		double timeCount = 0;
		try {
			Date d1 = getDate(startDate);
			Date d2 = getDate(endDate);
			timeCount = (d2.getTime() - d1.getTime()) / 3600;
		} catch (Exception e) {
			System.out.println("Date parse error!");
		}
		return timeCount;
	}
	
	/**
     * date2比date1多的天数
     * @param date1    
     * @param date2
     * @return    
     */
    public static int differentDays(Date startDate,Date endDate)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startDate);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endDate);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //不同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //同一年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
//	public static Integer getDiffday(Integer startDate, Integer endDate)
//	throws ParseException {
//		int diffDay = 0;
//		try {
//			java.util.Date d1 = getDate(startDate);
//			java.util.Date d2 = getDate(endDate);
//			d = (d2.getTime() - d1.getTime()) / 3600;
//		} catch (Exception e) {
//			System.out.println("Date parse error!");
//		}
//		return timeCount;
//	}

	/**
	 * 判断星期几
	 * 
	 * @param pTime
	 * @return
	 */
	public static int dayForWeek(String pTime) {
		int dayForWeek = 0;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(format.parse(pTime));
			if (c.get(Calendar.DAY_OF_WEEK) == 1) {
				dayForWeek = 7;
			} else {
				dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dayForWeek;
	}

	/**
	 * 通过当前时间获取对应星期几
	 * 
	 * @param timestamp
	 *            当前时间戳
	 * @return
	 * @author zhuangzhijun 2017-2-4
	 */
	public static int dayToWeek(int timestamp) {
		int dayForWeek = 0;
		String pTime = DateHelper.formatDate(getDate(timestamp), "yyyy-MM-dd");
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(format.parse(pTime));
			if (c.get(Calendar.DAY_OF_WEEK) == 1) {
				dayForWeek = 7;
			} else {
				dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dayForWeek;
	}

	/**
	 * 获取传入时间的前一天0点
	 * 
	 * @param time
	 * @return
	 */
	public static Integer getYesterday(Integer time) {
		Date day = addDay(getDate(time), -1);
		return getTimestamp(stringToDate(formatDate(day, "yyyy-MM-dd 00:00:00")));
	}

	/**
	 * 获取传入时间的0点
	 * 
	 * @param time
	 * @return
	 */
	public static Integer getToday(Integer time) {
		return getTimestamp(stringToDate(formatDate(getDate(time),
				"yyyy-MM-dd 00:00:00")));
	}
	
	public static Integer getCurrentTimeOfHour(Integer time){
		return getTimestamp(stringToDate(formatDate(getDate(time),
		"yyyy-MM-dd HH:00:00")));
	}

	/**
	 * 获取传入时间的前一天23点59分59秒
	 * 
	 * @param time
	 * @return
	 */
	public static Integer getYesterdayEndTime(Integer time) {
		Date day = addDay(getDate(time), -1);
		return getTimestamp(stringToDate(formatDate(day, "yyyy-MM-dd 23:59:59")));
	}
}
