package com.icode.library.tools.utils;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.MeasureSpec;
/**
 * 公共的操作
 *
 */
public class ICommonUtils {
	
	/**
	 * 退出当前应用
	 * @param context
	 */
	public static void exitApplication(Activity context) {
		Intent MyIntent = new Intent(Intent.ACTION_MAIN);
		MyIntent.addCategory(Intent.CATEGORY_HOME);
		context.startActivity(MyIntent);
		System.exit(0);
		context.finish();
	}

	/**
	 * 计算组件的宽度
	 * @param view
	 * @return
	 */
	public static int measureViewWidth(View view) {
		int width_ = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		int height_ = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		view.measure(width_, height_);
		return view.getMeasuredWidth();

	}

	/**
	 * 计算组件的高度
	 * @param view
	 * @return
	 */
	public static int measureViewHeight(View view) {
		if (view == null)
			return 0;
		int width_ = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		int height_ = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		view.measure(width_, height_);
		return view.getMeasuredHeight();

	}

	/**
	 * 获取URI的实际路径
	 * @param uri
	 * @param context
	 * @return
	 */
	public static String getAbsoluteImagePath(Uri uri, Activity context) {
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = context.managedQuery(uri, proj, 
														
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)
		if (cursor != null) {
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();

			return cursor.getString(column_index);
		} else {

			return uri.getPath();
		}
	}
	
}
