package com.demo.icodelibrary.demos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;

public class SpeicalEditTextFragment extends IFragment implements IPageRuler {
  
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    
    mContentView  = LayoutInflater.from(mActivity).inflate(R.layout.demo_special_edittext, null);
    initData();
    initViews();
    return mContentView;
  }

  @Override
  public void initViews() {
    
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
