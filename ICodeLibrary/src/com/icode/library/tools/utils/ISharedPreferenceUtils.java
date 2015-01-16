package com.icode.library.tools.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreference 存取相关
 * version 1.0
 *
 */
public class ISharedPreferenceUtils {
	
	public static final String SHARED_ID = "sharedPerferenceCodes";

	/***
	 * 将sharedPreferences中的指定键设为指定的值
	 * 
	 * @param context
	 *            上下文
	 * @param sharedName
	 *            键值对中的key
	 * @param sharedValue
	 *            指定的值,键值对中的value
	 */
	public static void addToSharedPreferences(Context context,
			String sharedName, String sharedValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_ID, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(sharedName, sharedValue);
		editor.commit();
	}

	/***
	 * 将sharedPreferences中的指定键设为指定的值
	 * 
	 * @param context
	 *            上下文
	 * @param sharedName
	 *            键值对中的key
	 * @param sharedValue
	 *            指定的值,键值对中的value
	 */
	public static void addToSharedPreferences(Context context,
			String sharedName, int sharedValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_ID, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt(sharedName, sharedValue);
		editor.commit();
	}

	/***
	 * 将sharedPreferences中的指定键设为指定的值
	 * 
	 * @param context
	 *            上下文
	 * @param sharedName
	 *            键值对中的key
	 * @param sharedValue
	 *            指定的值,键值对中的value
	 */
	public static void addToSharedPreferences(Context context,
			String sharedName, boolean sharedValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_ID, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean(sharedName, sharedValue);
		editor.commit();
	}
	/***
	 * 将sharedPreferences中的指定键设为指定的值
	 * 
	 * @param context
	 *            上下文
	 * @param sharedName
	 *            键值对中的key
	 * @param sharedValue
	 *            指定的值,键值对中的value
	 */
	public static void addToSharedPreferences(Context context,
			String sharedName, long sharedValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_ID, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putLong(sharedName, sharedValue);
		editor.commit();
	}

	public static void addToSharedPreferences(Context context,
			String sharedName, float sharedValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_ID, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putFloat(sharedName, sharedValue);
		editor.commit();
	}
	

	/***
	 * 获取sharedPreferences中的指定键位的值
	 * 
	 * @param context
	 * @param sharedName
	 *            key
	 * @param defaultValue
	 *            当该键位不存在时，返回到默认值
	 * @return
	 */
	public static String getValueOfSharedPreferences(Context context,
			String sharedName, String defaultValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_ID, Context.MODE_PRIVATE);
		return sharedPreferences.getString(sharedName, defaultValue);

	}
	
	public static float getValueOfSharedPreferences(Context context,
			String sharedName, float defaultValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_ID, Context.MODE_PRIVATE);
		return sharedPreferences.getFloat(sharedName, defaultValue);

	}
	
	public static long getValueOfSharedPreferences(Context context,
			String sharedName, long defaultValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_ID, Context.MODE_PRIVATE);
		return sharedPreferences.getLong(sharedName, defaultValue);

	}
	/***
	 * 获取sharedPreferences中的指定键位的值
	 * 
	 * @param context
	 * @param sharedName
	 *            key
	 * @param defaultValue
	 *            当该键位不存在时，返回到默认值
	 * @return
	 */
	public static boolean getValueOfSharedPreferences(Context context,
			String sharedName, boolean defaultValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_ID, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(sharedName, defaultValue);

	}

	/***
	 * 获取sharedPreferences中的指定键位的值
	 * 
	 * @param context
	 * @param sharedName
	 *            key
	 * @param defaultValue
	 *            当该键位不存在时，返回到默认值
	 * @return
	 */
	public static int getValueOfSharedPreferences(Context context,
			String sharedName, int defaultValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				SHARED_ID, Context.MODE_PRIVATE);
		return sharedPreferences.getInt(sharedName, defaultValue);

	}

	
	
}
