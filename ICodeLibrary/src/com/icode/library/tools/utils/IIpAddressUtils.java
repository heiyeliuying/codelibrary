package com.icode.library.tools.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
/**
 * IP地址相关的辅助类
 */
public class IIpAddressUtils {
	/**
	 * 获取本机IP地址
	 * 
	 * @return
	 */
	public static String getLocalIpAddress(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);  
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();  
		int ipAddress = wifiInfo.getIpAddress();  
		return int2ip(ipAddress);
	}
	
	/**
	 * 转化为IP
	 * @param longIp
	 * @return
	 */
	public static String int2ip(long ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
}
	
}
