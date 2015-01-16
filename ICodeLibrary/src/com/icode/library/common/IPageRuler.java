package com.icode.library.common;
/**
 * 主要用于规范activity或者fragment的代码规范.
 * 所有的activity和fragment应该都继承该接口
 *
 */
public interface IPageRuler {
  
  
  /**
   * 初始化页面元素
   */
  public void initViews();
  

  /**
   * 初始化页面数据
   */
  public void initData();
  
  /**
   * 保存数据
   */
  public void saveData();

  /**
   * 刷新页面
   */
  public void flushPage();
  
  
}
