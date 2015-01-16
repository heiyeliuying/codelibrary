package com.icode.library.tools.utils;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 软键盘相关
 * 
 * 
 */
public class IKeyboradUtils {
	/**
	 * 隐藏软键盘
	 * 
	 * @param context
	 * @param iBinder
	 */
	public static void hideSoftKeyInput(Context context, IBinder iBinder) {
		InputMethodManager inputMethodManager = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(iBinder, 0);
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param mActivity
	 */
	public static void hideSoftKeyInputCurrentFocus(Activity mActivity) {
		InputMethodManager inputMethodManager = (InputMethodManager) mActivity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		View view = mActivity.getCurrentFocus();
		if (view != null)
			inputMethodManager
					.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/**
	 * 打开输入法
	 */
	public static void openSoftKeyInputCurrentFocus(Activity mActivity) {
		InputMethodManager inputMethodManager = (InputMethodManager) mActivity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		View view = mActivity.getCurrentFocus();
		if (view != null)
			inputMethodManager.showSoftInput(mActivity.getCurrentFocus(),
					InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * 打开输入法
	 */
	public static void openSoftKeyInput(Activity mActivity, View view) {
		InputMethodManager inputMethodManager = (InputMethodManager) mActivity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.showSoftInput(view,
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

}
