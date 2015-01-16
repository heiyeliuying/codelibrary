package com.icode.library.widgets;
/**
 *定义命名和调用规则
 */
public interface IThemeRule {
  /**
   * 初始化主题
   */
  void initTheme();
  /**
   * 初始化背景主题
   */
  void initBackgroudTheme();
  /**
   * 初始化文本主题
   */
  void initTextTheme();

}
