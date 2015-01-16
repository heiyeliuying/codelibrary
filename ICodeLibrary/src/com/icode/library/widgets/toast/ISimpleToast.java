package com.icode.library.widgets.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.code.icodelibrary.R;
/**
 * 提供一种简单的Toast
 *
 */
public class ISimpleToast extends Toast {

  public ISimpleToast(Context context) {
    super(context);
  }
  
 
  /**
   * 在屏幕的中央位置弹出toast
   * @param context
   * @param msg toast信息内容
   */
  public static void showCenterToast(Context context ,String msg){
    Toast toast = Toast.makeText(context, msg, LENGTH_LONG);
    View view =  LayoutInflater.from(context).inflate(R.layout.common_toast_item, null);
    TextView textView = (TextView) view.findViewById(R.id.common_toast_msg);
    textView.setText(msg);
    toast.setView(view);
    toast.setGravity(Gravity.CENTER, 0, 0);
    toast.show();
  }
  
  /**
   * 在屏幕的底部位置弹出toast
   * @param context
   * @param msg toast信息内容
   */
  public static void showBottomToast(Context context,String msg){
    Toast toast = Toast.makeText(context, msg, LENGTH_LONG);
    View view =  LayoutInflater.from(context).inflate(R.layout.common_toast_item, null);
    TextView textView = (TextView) view.findViewById(R.id.common_toast_msg);
    textView.setText(msg);
    toast.setView(view);
    toast.setGravity(Gravity.BOTTOM, 0, 100);
    toast.show();
    
  }
  
  /**
   * 在屏幕的顶部位置弹出toast
   * @param context
   * @param msg toast信息内容
   */
  public static void showTopToast(Context context,String msg){
    Toast toast = Toast.makeText(context, msg, LENGTH_LONG);
    View view =  LayoutInflater.from(context).inflate(R.layout.common_toast_item, null);
    TextView textView = (TextView) view.findViewById(R.id.common_toast_msg);
    textView.setText(msg);
    toast.setView(view);
    toast.setGravity(Gravity.TOP, 0, 100);
    toast.show();
    
  }
  

}
