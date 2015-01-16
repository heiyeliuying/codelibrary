package com.icode.library.widgets.indicator;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.code.icodelibrary.R;
import com.icode.library.tools.utils.IColorStateUtils;
import com.icode.library.tools.utils.IStateDrawableUtils;
/**
 * 导航基类,里面定义了基本的内容元素
 * @author thank
 *
 */
public class IIndicatorLayoutBase extends HorizontalScrollView {
	protected ArrayList<IndicatorHolder> mIndicatorHolders = new ArrayList<IndicatorHolder>();
	//字体
	protected  int mFontSize = 12;
	//监听
	protected IIndicatorChangedListener mIndicatorChangedListener;
	//导航信息
	protected  ArrayList<IIndicatorInfo> mIndicatorInfos = new ArrayList<IIndicatorInfo>();
	//默认背景
	protected int mResourceIdBg = R.drawable.common_indicator_bg;
	//提示图标
	protected int mResourceNotify = R.drawable.common_indicator_notification;
	//每页的数量
	protected int mNumberPerPage =4;
	
	private LinearLayout mLinearLayoutParent ;
	
	public IIndicatorLayoutBase(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		initView(false);
	}
	public IIndicatorLayoutBase(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(false);
	}

	public IIndicatorLayoutBase(Context context) {
		super(context);
		initView(false);
	}
	
	
	/**
	 * 初始化UI
	 */
	protected void initView(boolean isFlushNow){
		removeAllViews();
		mLinearLayoutParent = new LinearLayout(getContext());
		mLinearLayoutParent.setGravity(Gravity.CENTER);
		mLinearLayoutParent.setLayoutParams(new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT));
		mLinearLayoutParent.setBackgroundResource(mResourceIdBg);
		addView(mLinearLayoutParent);
		if(isFlushNow){
			flushDataNow();
		}
	}
	
	/**
	 * 设置数据源
	 * @param indicatorInfos
	 */
	public void setIndicatorItems(ArrayList<IIndicatorInfo> indicatorInfos){
		mIndicatorInfos.clear();
		mIndicatorInfos.addAll(indicatorInfos);
		flushDataNow();
	}
	
	/**
	 * 刷新数据源的改变
	 */
	private void flushDataNow(){
		LayoutParams layoutParams = new LayoutParams(getItemWidth(),
				LayoutParams.WRAP_CONTENT);
		mLinearLayoutParent.removeAllViews();
		mIndicatorHolders.clear();
		IndicatorHolder indicatorHolder = null;
		IIndicatorInfo indicatorInfo = null;
		for (int index = 0; index < mIndicatorInfos.size(); index++) {
			indicatorHolder = new IndicatorHolder();
			indicatorInfo = mIndicatorInfos.get(index);
			View contentViewItem = LayoutInflater.from(getContext()).inflate(
					R.layout.common_indicator_item, null);
			contentViewItem.setId(index);
			indicatorHolder.mContentView = contentViewItem;
			indicatorHolder.indicatorInfo = indicatorInfo;
			indicatorHolder.mIndicatorIcon = (ImageView) contentViewItem
					.findViewById(R.id.custom_indicator_item_icon);
			indicatorHolder.mIdicatorName = (TextView) contentViewItem
					.findViewById(R.id.custom_indicator_item_name);
			indicatorHolder.mIndicatorNotify = (ImageView) contentViewItem
					.findViewById(R.id.custom_indicator_item_notify);
			
			indicatorHolder.mIndicatorIcon
			.setBackgroundDrawable(IStateDrawableUtils.newSelector(
					getContext(),
					indicatorInfo.getIndicatorIconNormal(),
					indicatorInfo.getIndicatorIconSelected()));
			indicatorHolder.mIdicatorName.setTextColor(
					IColorStateUtils.newSelector(getContext(), 
							indicatorHolder.indicatorInfo
							.getIndicatorNameColorNormal(),
							indicatorHolder.indicatorInfo
							.getIndicatorNameColorSelected()));
			
			
			indicatorHolder.mIndicatorNotify
					.setBackgroundResource(mResourceNotify);

			indicatorHolder.mIdicatorName.setText(indicatorInfo
					.getIndicatorName());
			indicatorHolder.mIdicatorName.setTextSize(
					TypedValue.COMPLEX_UNIT_SP, mFontSize);
			final int index_ = index ;
			indicatorHolder.mContentView
					.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							indicatorClicked(index_);
						}
					});
			mIndicatorHolders.add(indicatorHolder);
			mLinearLayoutParent.addView(contentViewItem, layoutParams);
			indicatorDataChangedComplete();
		}
	}
	
	/**
	 * 导航数据源设置完毕时回调
	 */
	protected void indicatorDataChangedComplete(){
		
		
	}
	
	
	/**
	 * 某个选项被点击时触发
	 * @param position
	 */
	protected void indicatorClicked(int position){
		
	}
	
	
	
	
	/**
	 * 获取每个ITEM的宽度
	 * @return
	 */
	private int getItemWidth(){
		// 获取屏幕宽度
		WindowManager windowManager = (WindowManager) getContext()
				.getSystemService(Context.WINDOW_SERVICE);
		Point outSize = new Point();
		int mScreenWidth=	windowManager.getDefaultDisplay().getWidth();
	//	int mScreenWidth = outSize.x;
		return mScreenWidth/mNumberPerPage;
	}
	
	
	protected class IndicatorHolder {
		 View mContentView;
		 ImageView mIndicatorIcon;
		 TextView mIdicatorName;
		 ImageView mIndicatorNotify;
		 IIndicatorInfo indicatorInfo;

	}

	/**
	 * 获取回调函数
	 * @return
	 */
	public IIndicatorChangedListener getmIndicatorChangedListener() {
		return mIndicatorChangedListener;
	}
	/**
	 * 设置回调函数
	 * @param mIndicatorChangedListener
	 */
	public void setmIndicatorChangedListener(
			IIndicatorChangedListener mIndicatorChangedListener) {
		this.mIndicatorChangedListener = mIndicatorChangedListener;
	}
	/**
	 * 获取每页展示的个数
	 * @return
	 */
	public int getNumberPerPage() {
		return mNumberPerPage;
	}
	/**
	 * 设置每页展示的个数
	 * @param mNumberPerPage
	 */
	public void setNumberPerPage(int mNumberPerPage) {
		this.mNumberPerPage = mNumberPerPage;
		initView(true);
	}
	/**
	 * 获取背景图片的ID
	 * @return
	 */
	public int getResourceIdBg() {
		return mResourceIdBg;
	}
	/**
	 * 设置背景图片的ID
	 * @param mResourceIdBg
	 */
	public void setResourceIdBg(int mResourceIdBg) {
		this.mResourceIdBg = mResourceIdBg;
		initView(true);
	}
	
	
	
	
}
