package com.icode.library.tools.utils;

import java.security.MessageDigest;
/**
 * MD5加密相关的辅助类
 *
 */
public class IMD5Utils {
	
	/**
	 * 获取文字的MD5加密字符串
	 * @param source
	 * @return
	 */
	public static String md5(String source){
		String result = "";
		  try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			 md5.update(source.getBytes());    
		        byte[] m = md5.digest();//加密   
		        result = new String(m);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return result;
		}
		  
	
	}

}
