package com.icode.library.widgets.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.code.icodelibrary.R;
/**
 * 自定义的弹出确认框.包含两个按钮,一个文本信息内容.通过其本身的getInstance()方法进行实例化
 * @version 1.0
 *
 */
public class ISimpleAlertDialog extends Dialog {

  public static class DialogInfo {
    //标题
    private String title;
    //消息
    private String message;
    //确定按钮显示内容
    private String sureMsg;
    //取消按钮显示内容
    private String cancelMsg;

    /**
     * 获取默认的DialogInfo对象
     * @return
     */
    public static DialogInfo getInstance() {
      DialogInfo dialogInfo = new DialogInfo();
      dialogInfo.setCancelMsg("取消");
      dialogInfo.setTitle("温馨提示");
      dialogInfo.setMessage("您是否要继续此操作?");
      dialogInfo.setSureMsg("确定");
      return dialogInfo;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public void setSureMsg(String sureMsg) {
      this.sureMsg = sureMsg;
    }

    public void setCancelMsg(String cancelMsg) {
      this.cancelMsg = cancelMsg;
    }

    public String getTitle() {
      return title;
    }

    public String getMessage() {
      return message;
    }

    public String getSureMsg() {
      return sureMsg;
    }

    public String getCancelMsg() {
      return cancelMsg;
    }

  }

  public ISimpleAlertDialog(Context context, DialogInfo dialogInfo,
      final View.OnClickListener sureListener, final View.OnClickListener cancelListener) {
    super(context, R.style.CommonAlertDialog);
    setContentView(R.layout.common_dialog_alert);

    TextView textView_title = (TextView) findViewById(R.id.common_dialog_title);
    TextView textView_msg = (TextView) findViewById(R.id.common_dialog_msg);
    TextView textView_sure = (TextView) findViewById(R.id.common_dialog_sure);
    TextView textView_cancel = (TextView) findViewById(R.id.common_dialog_cancel);

    textView_title.setText(dialogInfo.getTitle());
    textView_msg.setText(dialogInfo.getMessage());
    textView_sure.setText(dialogInfo.getSureMsg());
    textView_cancel.setText(dialogInfo.getCancelMsg());

    textView_sure.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View arg0) {
        dismiss();
        if (sureListener != null)
          sureListener.onClick(arg0);

      }
    });
    textView_cancel.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View arg0) {
        dismiss();
        if (cancelListener != null)
          cancelListener.onClick(arg0);

      }
    });

  }

  /**
   * 获取信息提示框
   * @param activity 上下文
   * @param dialogInfo 提示信息实体
   * @param isCancel 是否可取消
   * @param sureListener 点击确定按钮时的事件
   * @param cancelListener 点击取消按钮时的事件
   * @return
   */
  public static ISimpleAlertDialog getInstance(Activity activity,DialogInfo dialogInfo,boolean isCancel,
      android.view.View.OnClickListener sureListener,android.view.View.OnClickListener cancelListener){
    ISimpleAlertDialog dialog = new ISimpleAlertDialog(activity,
        dialogInfo, sureListener, cancelListener);
    dialog.setCanceledOnTouchOutside(isCancel);
    return dialog;
  }
  /**
   *  获取信息提示框
   * @param activity 上下文
   * @param dialogInfo 提示信息实体
   * @param sureListener 点击确定按钮时的事件
   * @return
   */
  public static ISimpleAlertDialog getInstance(Activity activity,DialogInfo dialogInfo,
      android.view.View.OnClickListener sureListener){
    ISimpleAlertDialog dialog = new ISimpleAlertDialog(activity,
        dialogInfo, sureListener, null);
    return dialog;
    
  }

  /**
   * 获取信息提示框
   * 
   * @param activity
   *          上下文
   * @param sureListener
   *          点击确定按钮时的事件
   * @return
   */
  public static ISimpleAlertDialog getInstance(Activity activity,
      android.view.View.OnClickListener sureListener){
    ISimpleAlertDialog dialog = new ISimpleAlertDialog(activity,
        DialogInfo.getInstance(), sureListener, null);
    return dialog;
    
  } 
  
}
