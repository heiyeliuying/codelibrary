package com.demo.icodelibrary.demos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.icodelibrary.R;
import com.icode.library.moudle.introduce.ISimpleIntroduceFragment;
import com.icode.library.widgets.toast.ISimpleToast;

public class IntroduceFragment  extends ISimpleIntroduceFragment{
  
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    int imageRes[] = new int[]{
        R.drawable.loading_introduce_page1,
        R.drawable.loading_introduce_page2,
        R.drawable.loading_introduce_page3
        
    };
    flushData(imageRes);
    return super.onCreateView(inflater, container, savedInstanceState);
  }
  
  
  @Override
  public void flushData(int[] imageRes) {
    super.flushData(imageRes);
  }
  
  @Override
  protected void skipToInit() {
    super.skipToInit();
    ISimpleToast.showCenterToast(mActivity, "页面跳转");
  }

}
