package com.icode.library.tools.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 *  像素相关
 */
public class IDestinyUtils {
  
  /**
   * dp转px
   * @param activity
   * @param number
   * @return
   */
  public static float dp2px(Activity activity, int number) {
    DisplayMetrics displaysMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(displaysMetrics);
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, number, displaysMetrics);
  }

  /**
   *  dp转px
   * @param context
   * @param number
   * @return
   */
  public static float dp2px(Context context,int number){
    DisplayMetrics displaysMetrics = new DisplayMetrics();
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    windowManager.getDefaultDisplay().getMetrics(displaysMetrics);
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, number, displaysMetrics);
  }
  
}
