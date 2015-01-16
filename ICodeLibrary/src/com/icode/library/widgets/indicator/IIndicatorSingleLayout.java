package com.icode.library.widgets.indicator;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.icode.library.tools.utils.IColorStateUtils;
import com.icode.library.tools.utils.IStateDrawableUtils;

/**
 * 底部导航栏
 * 
 * @author thank
 * 
 */
public class IIndicatorSingleLayout extends IIndicatorLayoutBase {
	//当前选中的导航
	private IIndicatorParam mCurrentIndicator = IIndicatorParam.param_1;
	//当前选中的导航索引
	private int mCurrentIndicatorPosition = 0;
	//导航标识集合
	private ArrayList<IIndicatorParam >params =new ArrayList<IIndicatorParam>();
	//默认位置
	private int defaultPosition = 0 ;

	public IIndicatorSingleLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public IIndicatorSingleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public IIndicatorSingleLayout(Context context) {
		super(context);
	}

	
	@Override
	protected void indicatorDataChangedComplete() {
		super.indicatorDataChangedComplete();
		IndicatorHolder indicatorHolder = mIndicatorHolders.get(defaultPosition);
		IIndicatorInfo indicatorInfo = mIndicatorInfos.get(defaultPosition);
		indicatorHolder.mIndicatorIcon
		.setBackgroundResource(indicatorInfo
				.getIndicatorIconSelected());
		indicatorHolder.mIdicatorName.setTextColor(indicatorInfo
		.getIndicatorNameColorSelected());
		flushNotification();
	}

	@Override
	protected void indicatorClicked(int positionIndicator) {
		super.indicatorClicked(positionIndicator);
		IIndicatorDirection direction = IIndicatorDirection.direction_none;
		IIndicatorParam param = mIndicatorInfos.get(positionIndicator)
				.getIndicatorParam();
		if (param == mCurrentIndicator) {
			if (mIndicatorChangedListener != null)
				mIndicatorChangedListener.onIndicatorRepeated(mIndicatorInfos.get(positionIndicator),param);
			return;
		} else {
			if (mCurrentIndicatorPosition > positionIndicator) {
				direction = IIndicatorDirection.direction_rightToLeft;
			}
			if (mCurrentIndicatorPosition < positionIndicator) {
				direction = IIndicatorDirection.direction_leftToRight;
			}
			mCurrentIndicator = param;
			mCurrentIndicatorPosition = positionIndicator;
		}
		if (mIndicatorChangedListener != null) {

		}
		for (int index = 0; index < mIndicatorHolders.size(); index++) {
			IndicatorHolder indicatorHolder = mIndicatorHolders.get(index);
			if (mCurrentIndicator == indicatorHolder.indicatorInfo
					.getIndicatorParam()) {
				indicatorHolder.mIndicatorIcon
						.setBackgroundResource(indicatorHolder.indicatorInfo
								.getIndicatorIconSelected());
				indicatorHolder.mIdicatorName
						.setTextColor(indicatorHolder.indicatorInfo
								.getIndicatorNameColorSelected());
				
			} else {
				indicatorHolder.mIndicatorIcon
						.setBackgroundDrawable(IStateDrawableUtils.newSelector(
								getContext(), indicatorHolder.indicatorInfo
										.getIndicatorIconNormal(),
								indicatorHolder.indicatorInfo
										.getIndicatorIconSelected()));
				indicatorHolder.mIdicatorName.setTextColor(
						IColorStateUtils.newSelector(getContext(), 
								indicatorHolder.indicatorInfo
								.getIndicatorNameColorNormal(),
								indicatorHolder.indicatorInfo
								.getIndicatorNameColorSelected()));
				
				
			}
		}
		if (mIndicatorChangedListener != null)
			mIndicatorChangedListener.onIndicatorChanged(mIndicatorInfos.get(positionIndicator),param, direction);
	}

	/**
	 * 展示右上角提示信息
	 * 
	 * @param indicatorParam
	 */
	public void showNotification(IIndicatorParam indicatorParam) {
		params.add(indicatorParam);
		flushNotification();
		
	}
	/**
	 * 更新内容提示图标
	 */
	private void flushNotification(){
		for (int index = 0; index < mIndicatorHolders.size(); index++) {
			IndicatorHolder indicatorHolder = mIndicatorHolders.get(index);
			boolean isVisible = params.contains(mIndicatorHolders.get(index).indicatorInfo
					.getIndicatorParam());
			if(isVisible)
				indicatorHolder.mIndicatorNotify.setVisibility(View.VISIBLE);
			else
				indicatorHolder.mIndicatorNotify.setVisibility(View.GONE);
		}
	}

	/**
	 * 隐藏右上角的提示信息
	 * 
	 * @param indicatorParam
	 */
	public void hideNotification(IIndicatorParam indicatorParam) {
		params.remove(indicatorParam);
	}

	/**
	 * 切换到某一项
	 * @param indicatorParam
	 */
	public void switchTo(IIndicatorParam indicatorParam){
		int positionIndicator = 0;
		for (int i = 0; i < mIndicatorInfos.size(); i++) {
			if(indicatorParam==mIndicatorInfos.get(i).getIndicatorParam()){
				positionIndicator = i;
				break;
			}
		}
		
		indicatorClicked(positionIndicator);
	}
	/**
	 * 切换到某个选项
	 * @param position
	 */
	public void switchTo(int position){
		indicatorClicked(position);
	}
	
}
