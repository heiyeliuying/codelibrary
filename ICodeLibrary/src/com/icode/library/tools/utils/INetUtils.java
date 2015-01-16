package com.icode.library.tools.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.provider.Settings;

/***
 * 和网络相关的功能
 * 
 * @version 2.0
 */
public class INetUtils {

	/***
	 * 检测wifi网络是否已经连接正常
	 * 
	 * @param context
	 *            上下文
	 * @return 连接正常则返回true，否则返回false
	 */
	public static boolean isNetConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo.State state = networkInfo.getState();
		if (state == State.CONNECTED) {
			return true;
		} else {
			return false;
		}

	}

	/***
	 * 跳转到wifi设置界面
	 * 
	 * @param activity
	 *            当前activity
	 */
	public static void goWifiSetting(Activity activity) {
		Intent intent = new Intent();
		intent.setAction(Settings.ACTION_WIFI_SETTINGS);
		activity.startActivityForResult(intent, 10);

	}

	/***
	 * 访问指定的url,返回输入流
	 * 
	 * @param path
	 *            一个可被访问的url地址
	 * @return path可以被访问，返回输入流；path不可以被访问，返回null
	 */
	public static InputStream connectUrl(String path) {
		InputStream is = null;
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestProperty("Charsert", "UTF-8");
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				is = conn.getInputStream();
			} else {
				is = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			is = null;
		}
		return is;
	}

	/***
	 * 从指定的url中获取到输出流
	 * 
	 * @param path
	 *            一个url路径
	 * @return path可以被访问，返回输出流；path不可以被访问，返回null
	 */
	public static OutputStream connectUrlForOutput(String path) {
		OutputStream os = null;

		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5 * 1000); // 缓存的最长时间
			conn.setDoInput(true);// 允许输入
			conn.setDoOutput(true);// 允许输出
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			os = conn.getOutputStream();
		} catch (Exception e) {
			os = null;
			e.printStackTrace();
		}
		return os;

	}

	/***
	 * 检测wifi是否连接正常
	 * @param context
	 */
	public static boolean checkWifi(final Context context) {
		if (!INetUtils.isNetConnected(context)) {
			
			return false;
		}else{
			return true;
		}

	}
	
}
