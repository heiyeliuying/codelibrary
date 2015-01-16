package com.icode.library.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/**
 * ListView
 */
public class IThemeListView extends ListView implements IThemeRule {
  
  

  public IThemeListView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initTheme();
  }

  public IThemeListView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initTheme();
  }

  public IThemeListView(Context context) {
    super(context);
    initTheme();
  }

  @Override
  public void initTheme() {

    initBackgroudTheme();
    initTextTheme();
    
  }

  @Override
  public void initBackgroudTheme() {
    
  }

  @Override
  public void initTextTheme() {
    
  }

}
