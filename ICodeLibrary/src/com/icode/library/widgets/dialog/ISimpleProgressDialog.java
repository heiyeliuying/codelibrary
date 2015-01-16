package com.icode.library.widgets.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.code.icodelibrary.R;
/**
 * 简单的加载提示框.展现形式为一个旋转的进度圈和一个描述文本.
 * 通过其本身的getInstance()方法进行实例化
 * @version 1.0
 *
 */
public class ISimpleProgressDialog extends Dialog {

  private TextView tvMsg ;
  
  public ISimpleProgressDialog(Context context,String message) {
    super(context,R.style.CommonProgressDialog);
    
    setContentView(R.layout.common_progress);  
    
    getWindow().getAttributes().gravity = Gravity.CENTER;  
    
    tvMsg =  (TextView) this.findViewById(R.id.id_tv_loadingmsg);  
    tvMsg.setTextColor(Color.parseColor("#d8d8d8"));
    if(message.equals(""))
        message = "内容正在加载,请稍候~";
    tvMsg.setText(message);
  }
 
  /**
   * 获取加载框
   * @param activity 上下文
   * @param message 提示信息
   * @param isCancel 是否可以取消
   * @return
   */
  public static ISimpleProgressDialog getInstance(Activity activity,String message,boolean isCancel){
    ISimpleProgressDialog dialog = new ISimpleProgressDialog(activity, message);
    dialog.setCancelable(isCancel);
    return dialog;
    
  }
  /**
   *  获取加载框
   * @param activity 上下文
   * @param message 提示信息
   * @return
   */
 public static ISimpleProgressDialog getInstance(Activity activity,String message){
    ISimpleProgressDialog dialog = getInstance(activity, message,true);
    return dialog;
    
  }

  /**
   * 获取加载框
   * 
   * @param activity
   *          上下文
   * @return
   */
 public static ISimpleProgressDialog getInstance(Activity activity){
   ISimpleProgressDialog dialog = getInstance(activity, "",true);
   return dialog;
   
 }
}
