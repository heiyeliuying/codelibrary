package com.demo.icodelibrary.demos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.widgets.toast.ISimpleToast;
/**
 * Toast相关
 * @author chenzheng
 *
 */
public class ToastFragment extends IFragment implements IPageRuler,OnClickListener {
  
  private String toastMsg = "各种样式的自定义TOAST,Toast展示的形式有很多种,这里只给出最常用的几种,方便使用.";
  
  private Button button1,button2,button3 ;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_toast, null);
    initData();
    initViews();
    return mContentView;
  }
  
  

  @Override
  public void initViews() {
   button1 = (Button)mContentView.findViewById(R.id.toast_1);
   button1.setOnClickListener(this);
   
   button2 = (Button)mContentView.findViewById(R.id.toast_2);
   button2.setOnClickListener(this);

   button3 = (Button)mContentView.findViewById(R.id.toast_3);
   button3.setOnClickListener(this);


  }

  @Override
  public void initData() {
   

  }

  @Override
  public void saveData() {
   

  }

  @Override
  public void flushPage() {
   

  }



  @Override
  public void onClick(View view) {
    switch (view.getId()) {
    case R.id.toast_1:
      ISimpleToast.showTopToast(mActivity, toastMsg);
      break;
      
    case R.id.toast_2:
      ISimpleToast.showCenterToast(mActivity, toastMsg);
      break;
      
    case R.id.toast_3:
      ISimpleToast.showBottomToast(mActivity, toastMsg);
      break;

    default:
      break;
    }
    
  }

}
