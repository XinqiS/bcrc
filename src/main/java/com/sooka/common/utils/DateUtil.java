package com.sooka.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil extends DateUtils {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	/** 毫秒 */
	public final static long MS = 1;
	/** 每秒钟的毫秒数 */
	public final static long SECOND_MS = MS * 1000;
	/** 每分钟的毫秒数 */
	public final static long MINUTE_MS = SECOND_MS * 60;
	/** 每小时的毫秒数 */
	public final static long HOUR_MS = MINUTE_MS * 60;
	/** 每天的毫秒数 */
	public final static long DAY_MS = HOUR_MS * 24;
	
	/** 标准日期（不含时间）格式化器 */
	private final static SimpleDateFormat NORM_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");
	/** 标准日期时间格式化器 */
	private final static SimpleDateFormat NORM_DATETIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/** 汉字日期格式化器 */
	private final static SimpleDateFormat HZ_DATE_FORMAT = new SimpleDateFormat("yyyy年MM月dd日");
	/** HTTP日期时间格式化器 */
	private final static SimpleDateFormat HTTP_DATETIME_FORMAT = new SimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss z", Locale.US);

	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
			"yyyyMMdd");

	/**
	 * 获取YYYYMMDD格式
	 *
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 
	 * 当前时间，格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前时间的标准形式字符串
	 * 
	 */
	public static String now() {
		return formatDateTime(new Date());
	}

	/**
	 * 
	 * 当前日期，格式 yyyy-MM-dd
	 * 
	 * @return 当前日期的标准形式字符串
	 * 
	 */
	public static String today() {
		return formatDate(new Date());
	}

	/**
	 * 
	 * 根据特定格式格式化日期
	 * 
	 * @param date
	 *            被格式化的日期
	 * 
	 * @param format
	 *            格式
	 * 
	 * @return 格式化后的字符串
	 * 
	 */
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 
	 * 格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            被格式化的日期
	 * 
	 * @return 格式化后的日期
	 * 
	 */
	public static String formatDateTime(Date date) {
		// return format(d, "yyyy-MM-dd HH:mm:ss");

		return NORM_DATETIME_FORMAT.format(date);
	}

	/**
	 * 
	 * 格式化为Http的标准日期格式
	 * 
	 * @param date
	 *            被格式化的日期
	 * 
	 * @return HTTP标准形式日期字符串
	 * 
	 */
	public static String formatHttpDate(Date date) {
		// return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",
		// Locale.US).format(date);

		return HTTP_DATETIME_FORMAT.format(date);
	}

	/**
	 * 
	 * 格式 yyyy-MM-dd
	 * 
	 * @param date
	 *            被格式化的日期
	 * 
	 * @return 格式化后的字符串
	 * 
	 */
	public static String formatDate(Date date) {
		// return format(d, "yyyy-MM-dd");

		return NORM_DATE_FORMAT.format(date);
	}

	// ------------------------------------ Format end
	// ----------------------------------------------

	// ------------------------------------ Parse start
	// ----------------------------------------------

	/**
	 * 
	 * 将特定格式的日期转换为Date对象
	 * 
	 * @param dateString
	 *            特定格式的日期
	 * 
	 * @param format
	 *            格式，例如yyyy-MM-dd
	 * 
	 * @return 日期对象
	 * 
	 */
	public static Date parse(String dateString, String format) {
		try {
			return (new SimpleDateFormat(format)).parse(dateString);
		} catch (ParseException e) {
			logger.error("Parse " + dateString + " with format " + format
					+ " error!", e);
		}
		return null;
	}

	/**
	 * 
	 * 格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateString
	 *            标准形式的时间字符串
	 * 
	 * @return 日期对象
	 * 
	 */
	public static Date parseDateTime(String dateString) {
		// return parse(s, "yyyy-MM-dd HH:mm:ss");

		try {
			return NORM_DATETIME_FORMAT.parse(dateString);
		} catch (ParseException e) {
			logger.error("Parse " + dateString + " with format "
					+ NORM_DATETIME_FORMAT.toPattern() + " error!", e);
		}
		return null;
	}

	/**
	 * 
	 * 格式yyyy-MM-dd
	 * 
	 * @param dateString
	 *            标准形式的日期字符串
	 * 
	 * @return 日期对象
	 * 
	 */
	public static Date parseDate(String dateString) {
		// return parse(s, "yyyy-MM-dd");

		try {
			return NORM_DATE_FORMAT.parse(dateString);
		} catch (ParseException e) {
			logger.error("Parse " + dateString + " with format "
					+ NORM_DATE_FORMAT.toPattern() + " error!", e);
		}
		return null;
	}
	/**
	 * 转换汉字日期
	 * @param dateString
	 * @return
	 */
	public static String formatHzDate(String dateStr) {
		// return parse(s, "yyyy-MM-dd");
		return HZ_DATE_FORMAT.format(parseDate(dateStr));
		
	}
	// ------------------------------------ Parse end
	// ----------------------------------------------

	/**
	 * 
	 * 获取指定日期偏移指定时间后的时间
	 * 
	 * @param date
	 *            基准日期
	 * 
	 * @param calendarField
	 *            偏移的粒度大小（小时、天、月等）使用Calendar中的常数
	 * 
	 * @param offsite
	 *            偏移量，正数为向后偏移，负数为向前偏移
	 * 
	 * @return 偏移后的日期
	 * 
	 */
	public static Date getOffsiteDate(Date date, int calendarField, int offsite) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(calendarField, offsite);
		return cal.getTime();
	}

	/**
	 * 
	 * 判断两个日期相差的时长<br/>
	 * 
	 * 返回 minuend - subtrahend 的差
	 * 
	 * @param subtrahend
	 *            减数日期
	 * 
	 * @param minuend
	 *            被减数日期
	 * 
	 * @param diffField
	 *            相差的选项：相差的天、小时
	 * 
	 * @return 日期差
	 * 
	 */
	public static long dateDiff(Date subtrahend, Date minuend, long diffField) {
		long diff = minuend.getTime() - subtrahend.getTime();
		return diff / diffField;
	}

	/**
	 * 转译a标签
	 * 
	 * @param content
	 * @return
	 */
	private String labelFormat(String content) {
		if (StringUtils.isBlank(content)) {
			return "";
		}
		return content.replaceAll("<a", "&lt;a").replaceAll("</a>",
				"&lt;/a&gt;");
	}

	/**
	 * 日期比较（只比较日期部分） date1 > date2 返回true
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static boolean dateCompare(Date date1, Date date2) {
		if (date1 == null) {
			return false;
		}
		if (date2 == null) {
			return true;
		}
		if (date1.getTime() > date2.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 求年龄
	 * 
	 * @param birthDay
	 *            （Date） 出生日期
	 * @return 年龄
	 * @throws ParseException
	 */
	public static int getAgeByBirth(Date birthDay) throws ParseException {
		int age = 0;
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) { // 出生日期晚于当前时间，无法计算
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR); // 当前年份
		int monthNow = cal.get(Calendar.MONTH); // 当前月份
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); // 当前日期
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		age = yearNow - yearBirth; // 计算整岁数
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;// 当前日期在生日之前，年龄减一
			} else {
				age--;// 当前月份在生日之前，年龄减一
			}
		}
		return age;
	}

	public static String dateOfConversion(String dateOne) {
		//         //创建SimpleDateFormat对象sdf1,指定日期模式为yyyy-MM-dd
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		if(dateOne!=null){
		try {
			date = sdf.parse(dateOne);
			DateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
			String str2 = sdf2.format(date);// date类型转换成字符串
			return str2;
		} catch (ParseException e) {

		}// 字符串转成date对象类型
		}
		return dateOne;
		

	}

}
