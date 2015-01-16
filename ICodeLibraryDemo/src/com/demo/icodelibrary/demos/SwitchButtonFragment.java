package com.demo.icodelibrary.demos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.widgets.switchButton.ISwitchButton;
import com.icode.library.widgets.switchButton.ISwitchButton.OnChangedListener;
import com.icode.library.widgets.toast.ISimpleToast;
/**
 * 滑动式按钮开关
 * @author chenzheng
 *
 */
public class SwitchButtonFragment extends IFragment implements IPageRuler {
  
  protected ISwitchButton switchButton;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_switch_button, null); 
    initData();
    initViews();
    return mContentView;
  }
  

  @Override
  public void initViews() {

    switchButton = (ISwitchButton) mContentView.findViewById(R.id.switch_button);
    switchButton.setStyle(R.drawable.common_switch_bg_off, 
        R.drawable.common_switch_bg_on, R.drawable.common_switch_bg_btn);
    switchButton.setChangedListener(new OnChangedListener() {
      
      @Override
      public void onSwitchChanged(boolean isLeft) {
        ISimpleToast.showCenterToast(mActivity, "isLeft = "+isLeft);  
        
      }
    });
    
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

}
