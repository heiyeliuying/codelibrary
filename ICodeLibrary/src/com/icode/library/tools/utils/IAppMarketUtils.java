package com.icode.library.tools.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
/**
 * 应用商店相关
 * @version 1.0
 */
public class IAppMarketUtils {

	/**
	 * 跳转到AppMarket中指定的应用
	 * @param context
	 * @param packageName 应用包名
	 */
	public static void skipToAppMarket(Context context,String packageName){
		String mAddress = "market://details?id=" +packageName;
		openMarket(context, mAddress,"");
		
	}
	
	/**
	 * 跳转到googlePlay网页版的应用地址
	 * @param context
	 * @param packageName 应用名称
	 */
	public static void skipToGooglePlayWebMarket(Context context, String packageName){
		String mAddress = "http://market.android.com/details?id="+packageName;
		openMarket(context, mAddress,"");
	}
	/**
	 * 跳转到指定的market页面
	 * @param context
	 * @param httpUrl 网络地址
	 */
	public static void skipToWebMarket(Context context, String httpUrl){
		openMarket(context, httpUrl,"");
	}
	
	/**
	 * 跳转到指定的market
	 * @param context
	 * @param packageName 目标APP的包名
	 * @param marketPackageName 应用商店的应用包名
	 */
	public static void skipToAppMarket(Context context,String packageName,String marketPackageName){
		String mAddress = "market://details?id=" +packageName;
		openMarket(context, mAddress, marketPackageName);
	}
	/**
	 * 搜索应用
	 * @param context
	 * @param packageName
	 */
	public static void searchAppInMarket(Context context ,String packageName){
		String mAddress = "market://search?q=pname:"+packageName;
		openMarket(context, mAddress, "");
	}
	/**
	 * 在指定的market里搜索应用
	 * @param context
	 * @param packageName
	 * @param marketPackageName
	 */
	public static void searchAppInMarket(Context context ,String packageName,String marketPackageName){
		String mAddress = "market://search?q=pname:"+packageName;
		openMarket(context, mAddress, marketPackageName);
	}
	/**
	 * 在浏览器GooglePlay中搜索应用
	 * @param context
	 * @param packageName
	 */
	public static void searchAppInWebGooglePlayMarket(Context context,String packageName){
		String mAddress = "http://market.android.com/search?q=pname:"+packageName;
		openMarket(context, mAddress, "");
	}
	
	/**
	 * 在浏览器GooglePlay中搜索应用
	 * @param context
	 * @param authorName 开发者名称
	 */
	public static void searchAppWithWebGooglePlayAuthor(Context context ,String authorName){
		String mAddress = "http://market.android.com/search?q=pub:"+authorName;
		openMarket(context, mAddress, "");
	}
	/**
	 * 在应用商店中搜索应用
	 * @param context
	 * @param authorName 开发者名称
	 */
	public static void searchAppWithAuthor(Context context,String authorName){
		String mAddress =  "market://search?q=pub:"+authorName;
		openMarket(context, mAddress, "");
	}
	/**
	 * 在应用商店中搜索应用
	 * @param context
	 * @param authorName 开发者名称
	 * @param marketPackageName 应用商店的包名
	 */
	public static void searchAppWithAuthor(Context context,String authorName,String marketPackageName ){
		String mAddress =  "market://search?q=pub:"+authorName;
		openMarket(context, mAddress, marketPackageName);
	}
	
	/**
	 * 打开指定的Market
	 * @param context
	 * @param url 
	 */
	private static void openMarket(Context context ,String url,String marketPackageName){
		Intent marketIntent = new Intent(Intent.ACTION_VIEW);
		marketIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		marketIntent.setData(Uri.parse(url));
		if(marketPackageName!=null&&!"".equals(marketPackageName)){
			marketIntent.setPackage(marketPackageName);
		}
		context.startActivity(marketIntent);
		
	}
	
	
}
