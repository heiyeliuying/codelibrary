package com.icode.library.widgets.config;

import android.app.Application;
/**
 * 实现了主题样式的Application
 */
public abstract class IThemeApplication extends Application {

  private IThemeConfiguration themeConfiguration ;

  public abstract IThemeConfiguration getThemeConfiguration() ;

  public void setThemeConfiguration(IThemeConfiguration themeConfiguration) {
    this.themeConfiguration = themeConfiguration;
  }
  
  
  
  
}
