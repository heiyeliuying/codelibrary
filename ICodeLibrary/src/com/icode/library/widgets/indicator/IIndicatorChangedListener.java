package com.icode.library.widgets.indicator;
/**
 * 选项卡被选中时触发
 * @author thank
 * @param <T>
 *
 */
public interface IIndicatorChangedListener<T> {

	/**
	 * 切换了底层导航项
	 * @param indicatorParam
	 * @param direction 方向
	 */
	void onIndicatorChanged(IIndicatorInfo<T> indicatorInfo,IIndicatorParam indicatorParam,IIndicatorDirection direction);
	/**
	 * 重复点击已经选中的导航项
	 * @param indicatorParam
	 */
	void onIndicatorRepeated(IIndicatorInfo<T> indicatorInfo,IIndicatorParam indicatorParam);
	
	
}
