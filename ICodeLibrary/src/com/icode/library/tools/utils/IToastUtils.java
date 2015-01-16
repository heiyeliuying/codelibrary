package com.icode.library.tools.utils;
import android.content.Context;
import android.widget.Toast;
/**
 * toast提示
 *
 */
public class IToastUtils {

	/**
	 * 正式的toast提示
	 * @param context
	 * @param info
	 */
	public static void toast(Context context,String info){
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}
	/**
	 * 测试的toast提示
	 * @param context
	 * @param info
	 */
	public static void toast_test(Context context,String info){
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}
	
}
