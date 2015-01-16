package com.icode.library.tools.utils;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

/**
 * 屏幕待机相关
 *
 */
public class IScreenLightOnUtils {
	static WakeLock wakeLock ;
	/**
	 * 点亮屏幕不待机
	 * @param context
	 */
	public static void startScreenOn(Context context){
		PowerManager powerManager = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
		 wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"屏幕不待机");
		wakeLock.acquire();
		
	}
	/**
	 * 关闭持续点亮屏幕
	 */
	public static void closeScreenOn(){
		if(wakeLock!=null)
		wakeLock.release();
	}
	

}
