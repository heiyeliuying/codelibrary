package com.demo.icodelibrary;

import com.icode.library.widgets.config.IThemeApplication;
import com.icode.library.widgets.config.IThemeConfiguration;
import com.icode.library.widgets.config.IThemeStyle;

public class IApplication extends IThemeApplication{

  @Override
  public IThemeConfiguration getThemeConfiguration() {
    IThemeConfiguration configuration = new IThemeConfiguration.IThemeBuilder().build()
        .setThemeStyle(IThemeStyle.BLUE);
    return configuration;
  }
  
  
}
