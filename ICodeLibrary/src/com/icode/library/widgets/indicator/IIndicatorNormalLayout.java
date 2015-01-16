package com.icode.library.widgets.indicator;

import android.content.Context;
import android.util.AttributeSet;
/**
 * 默认的导航,不存在单选或多选
 * @author thank
 *
 */
public class IIndicatorNormalLayout extends IIndicatorLayoutBase {
	
	public IIndicatorNormalLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public IIndicatorNormalLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public IIndicatorNormalLayout(Context context) {
		super(context);
	}
	
	@Override
	protected void indicatorDataChangedComplete() {
		super.indicatorDataChangedComplete();
		}
	
	/**
	 * 某一项被选中时触发
	 */
	protected void indicatorClicked(int positionIndicator) {
		super.indicatorClicked(positionIndicator);
		if (mIndicatorChangedListener != null){
			IIndicatorParam indicatorParam = mIndicatorInfos.get(positionIndicator)
					.getIndicatorParam();
			mIndicatorChangedListener.onIndicatorChanged( mIndicatorInfos.get(positionIndicator),indicatorParam, IIndicatorDirection.direction_none);
		}
			
		}
}
