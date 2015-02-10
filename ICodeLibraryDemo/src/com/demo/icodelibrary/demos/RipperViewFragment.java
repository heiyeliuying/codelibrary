package com.demo.icodelibrary.demos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andexert.library.RippleView;
import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;

public class RipperViewFragment extends IFragment implements IPageRuler {
  
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_ripper, null);
    initData();
    initViews();
    return mContentView;
  }

  @Override
  public void initViews() {
   
    RippleView rippleView = (RippleView) mContentView.findViewById(R.id.ripper_img);
    
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
