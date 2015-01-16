package com.icode.library.tools.utils;

import android.util.Log;

/**
 * log信息
 *
 */
public class ILogUtils {
	
	/**
	 * 打印LOG Error级别的日志
	 * @param msg
	 */
	public static void logError(String msg){
			Log.e("logError", msg);
	}
	/**
	 * 打印LOG Error级别的日志
	 * @param tag
	 * @param msg
	 */
	public static void logError(String tag,String msg){
		Log.e(tag, msg);
	}

	
	
}
