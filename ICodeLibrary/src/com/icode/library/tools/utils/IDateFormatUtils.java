package com.icode.library.tools.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式
 * 
 * 
 */
public class IDateFormatUtils {
	/**
	 * 默认的时间格式
	 */
	public static final String FORMATSTR_DEFAULT = "yyyy-MM-dd";

	/**
	 * 格式化时间
	 * 
	 * @param date
	 *            当前时间
	 * @return
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATSTR_DEFAULT);
		return dateFormat.format(date);

	}

	/**
	 * 格式化时间
	 * 
	 * @param timeStamp
	 *            当前时间的毫秒数
	 * @return
	 */
	public static String formatDate(long timeStamp) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATSTR_DEFAULT);
		return dateFormat.format(new Date(timeStamp));

	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 *            时间
	 * @param pattern
	 *            时间格式
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);

	}

	/**
	 * 格式化时间
	 * @param timeStamp  当前时间的毫秒数
	 * @param pattern   时间格式
	 * @return
	 */
	public static String formatDate(long timeStamp, String pattern) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(new Date(timeStamp));

	}
}
