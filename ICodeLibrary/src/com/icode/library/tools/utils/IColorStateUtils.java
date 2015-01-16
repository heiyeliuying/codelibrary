package com.icode.library.tools.utils;

import android.content.Context;
import android.content.res.ColorStateList;
/**
 * 获取ColorStateList对象.可以赋值给textView等组件,
 * 实现文本按下时变色的效果
 */
public class IColorStateUtils {

  /**
   * 获取ColorStateList对象
   * @param context
   * @param normalColor 普通颜色
   * @param pressedColor 按下的颜色
   * @return
   */
  public static ColorStateList newSelector(Context context,int normalColor,int pressedColor){
    int[] colors = new int[] { pressedColor,normalColor };  
    int[][] states = new int[2][];  
    states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };  
    states[1] = new int[] {};  
    ColorStateList colorList = new ColorStateList(states, colors);  
    return colorList;  
  }
  
}
