package com.icode.library.tools.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * 和系统屏幕相关的操作
 * 
 */
public class IScreenUtils {

	//屏幕宽度
	private static int screenWidth = 0;
	//屏幕高度
	private static int screenHeight = 0;
	//每英寸有多少个显示点
	private static float density = 0; 
	//Standard quantized DPI for  screens.
	private static int densityDIP = 0;
	
	
	

	public static int getScreenWidth(Activity activity) {
		if(screenWidth==0)
			initScreen(activity);
		return screenWidth;
	}

	public static int getScreenHeight(Activity activity) {
		if(screenHeight==0)
			initScreen(activity);
		return screenHeight;
	}

	private  static void initScreen(Activity activity){
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		screenWidth = displayMetrics.widthPixels;
		screenHeight = displayMetrics.heightPixels;
		 density = displayMetrics.density;
		 densityDIP = displayMetrics.densityDpi;
		
	}

	public static DisplayMetrics getDisplayMetrics(Activity activity){
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics;
	}
	
	public static float getDensity(Activity activity) {
		if(density==0)
			initScreen(activity);
		return density;
	}

	public static int getDensityDIP(Activity activity) {
		if(densityDIP==0)
			initScreen(activity);
		return densityDIP;
	}
	
	
	
}
