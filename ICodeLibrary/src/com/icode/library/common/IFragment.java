package com.icode.library.common;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
/**
 * 公共的fragment.里面提供了很多便捷的方法以及统一的规范.
 * 原则上,所有的fragment都应该继承该类
 *
 */
public class IFragment extends Fragment {
  
  protected View mContentView ;
  
  protected Activity mActivity ;
  
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    this.mActivity  = activity ;
  }
  

}
