package com.icode.library.common;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 *  页面元素获取快捷方式
 */
public interface IViewFinder {
  
  View  findViewById(int id );
  
  View findViewById(int id, OnClickListener clickListener);
  
  Button findButtonById(int id);
  
  Button findButtonById(int id ,OnClickListener clickListener);
  

}
