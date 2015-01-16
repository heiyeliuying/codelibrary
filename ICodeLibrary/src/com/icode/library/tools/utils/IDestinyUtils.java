package com.icode.library.tools.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.TypedValue;

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
  public float dp2px(Activity activity, int number) {
    DisplayMetrics displaysMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(displaysMetrics);
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, number, displaysMetrics);
  }

}
