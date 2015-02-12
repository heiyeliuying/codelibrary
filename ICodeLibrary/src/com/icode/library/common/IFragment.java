package com.icode.library.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 公共的fragment.里面提供了很多便捷的方法以及统一的规范.
 * 原则上,所有的fragment都应该继承该类
 *
 */
@SuppressLint("NewApi")
public class IFragment extends Fragment implements IViewFinder{
  
  protected View mContentView ;
  
  protected Activity mActivity ;
  
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    this.mActivity  = activity ;
  }

  
  @Override
  public View findViewById(int id) {
    return mContentView.findViewById(id);
  }

  @Override
  public View findViewById(int id, OnClickListener clickListener) {
    View view = findViewById(id);
    view.setOnClickListener(clickListener);
    return view;
  }
  
  
  @Override
  public Button findButtonById(int id) {
    return (Button) findViewById(id);
  }

  @Override
  public Button findButtonById(int id, OnClickListener clickListener) {
    Button button = (Button) findViewById(id) ;
    button.setOnClickListener(clickListener);
    return button;
  }
  

}
