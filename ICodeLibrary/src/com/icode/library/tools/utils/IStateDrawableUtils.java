package com.icode.library.tools.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
/**
 * 获取StateListDrawable对象,该对象可用于View背景,可实现按下时变色的效果
 */
public class IStateDrawableUtils {
	/**
	 * 构建新的selector
	 * @param context
	 * @param normal
	 * @param pressed
	 * @return
	 */
	public static StateListDrawable newSelector(Context context,int normal,int pressed) {
		StateListDrawable bg = new StateListDrawable();
		Drawable pressedDrawable = context.getResources().getDrawable(
			pressed);
		Drawable normalDrawable = context.getResources().getDrawable(
				normal);
		bg.addState(new int[] { android.R.attr.state_pressed,
				android.R.attr.state_enabled }, pressedDrawable);
		bg.addState(new int[] {}, normalDrawable);
		return bg;

	}
	
	/**
	 *  使用颜色构建新的selector
	 * @param context
	 * @param normalColor 普通状态下的颜色
	 * @param pressColor 按下状态下的颜色
	 * @return
	 */
	public static StateListDrawable newColorSelector(Context context,int normalColor,int pressColor){
	  StateListDrawable bg = new StateListDrawable();
    Drawable pressedDrawable = new ColorDrawable(pressColor);
    Drawable normalDrawable =new ColorDrawable(normalColor);
    bg.addState(new int[] { android.R.attr.state_pressed,
        android.R.attr.state_enabled }, pressedDrawable);
    bg.addState(new int[] {}, normalDrawable);
    return bg;
	  
	}

}
