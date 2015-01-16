package com.icode.library.widgets.config;

/**
 * 配置样式
 */
public class IThemeConfiguration {

  IThemeStyle themeStyle = IThemeStyle.NONE;

  public static class IThemeBuilder {

    IThemeConfiguration configuration;

    /**
     * build一个config对象
     * 
     * @return
     */
    public IThemeConfiguration build() {
      configuration = new IThemeConfiguration();
      return configuration;
    }

  }

  public IThemeStyle getThemeStyle() {
    return themeStyle;
  }

  public IThemeConfiguration setThemeStyle(IThemeStyle themeStyle) {
    this.themeStyle = themeStyle;
    return this;
  }

}
