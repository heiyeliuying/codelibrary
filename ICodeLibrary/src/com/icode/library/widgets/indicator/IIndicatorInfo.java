package com.icode.library.widgets.indicator;

/**
 * 导航项
 * 
 * @author thank
 * @param <T>
 * 
 */
public class IIndicatorInfo<T> {
	// 名称
	private String indicatorName;
	// 默认颜色
	private int indicatorNameColorNormal;
	// 按下颜色
	private int indicatorNameColorSelected;
	// 默认图标
	private int indicatorIconNormal;
	// 按下图标
	private int indicatorIconSelected;
	// 唯一标识
	private IIndicatorParam indicatorParam;
	
	private T indicatorExtra ;

	/**
	 * 获取名称
	 * 
	 * @return
	 */
	public String getIndicatorName() {
		return indicatorName;
	}

	/**
	 * 设置名称
	 * 
	 * @param indicatorName
	 */
	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	/**
	 * 获取默认图标ID
	 * 
	 * @return
	 */
	public int getIndicatorIconNormal() {
		return indicatorIconNormal;
	}

	/**
	 * 设置默认图标资源ID
	 * 
	 * @param indicatorIconNormal
	 */
	public void setIndicatorIconNormal(int indicatorIconNormal) {
		this.indicatorIconNormal = indicatorIconNormal;
	}

	/**
	 * 获取被选中项的图标ID
	 * 
	 * @return
	 */
	public int getIndicatorIconSelected() {
		return indicatorIconSelected;
	}

	/**
	 * 设置被选中项的图标ID
	 * 
	 * @param indicatorIconSelected
	 */
	public void setIndicatorIconSelected(int indicatorIconSelected) {
		this.indicatorIconSelected = indicatorIconSelected;
	}

	/**
	 * 获取唯一标识
	 * 
	 * @return
	 */
	public IIndicatorParam getIndicatorParam() {
		return indicatorParam;
	}

	/**
	 * 设置唯一标识
	 * 
	 * @param indicatorParam
	 */
	public void setIndicatorParam(IIndicatorParam indicatorParam) {
		this.indicatorParam = indicatorParam;
	}

	/**
	 * 获取默认状态的颜色
	 * 
	 * @return
	 */
	public int getIndicatorNameColorNormal() {
		return indicatorNameColorNormal;
	}

	/**
	 * 设置默认状态的颜色
	 * 
	 * @param indicatorNameColorNormal
	 */
	public void setIndicatorNameColorNormal(int indicatorNameColorNormal) {
		this.indicatorNameColorNormal = indicatorNameColorNormal;
	}

	/**
	 * 获取选中状态的颜色
	 * 
	 * @return
	 */
	public int getIndicatorNameColorSelected() {
		return indicatorNameColorSelected;
	}

	/**
	 * 设置选中状态的颜色
	 * 
	 * @param indicatorNameColorSelected
	 */
	public void setIndicatorNameColorSelected(int indicatorNameColorSelected) {
		this.indicatorNameColorSelected = indicatorNameColorSelected;
	}

  public T getIndicatorExtra() {
    return indicatorExtra;
  }

  public void setIndicatorExtra(T indicatorExtra) {
    this.indicatorExtra = indicatorExtra;
  }

	
	
	
}
