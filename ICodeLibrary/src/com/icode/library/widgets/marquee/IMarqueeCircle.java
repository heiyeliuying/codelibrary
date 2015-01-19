package com.icode.library.widgets.marquee;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
/**
 * 推荐信息右下角的圆圈
 * @author thank
 *
 */
public class IMarqueeCircle extends View {
	private Paint mPaint_normal, mPaint_selected;
	// 半径
	private Float mCircleRadio = 4f;
	// 圆圈之间的width间隙
	private Float mPaddingWidthSpace = 6f;
	//圆圈之间的height间隙
	private Float mPaddingHeightSpace = 3f;
	// 圆圈的个数
	private int mCircleNumber = 5;
	// 圆圈的颜色
	private int mColorCircle_normal = Color.parseColor("#88FFFFFF");
	private int mColorCircle_selected = Color.parseColor("#ff66a6");
	
	// 当前的圆圈
	private int mCurrentSelected = 0;

	private DisplayMetrics displayMetrics;

	public IMarqueeCircle(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initViews();
	}

	public IMarqueeCircle(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews();
	}

	public IMarqueeCircle(Context context) {
		super(context);
		initViews();
	}

	/**
	 * 初始化布局UI
	 */
	 void initViews() {
		mPaint_normal = new Paint();
		mPaint_normal.setAntiAlias(true);
		mPaint_normal.setColor(mColorCircle_normal);
		mPaint_normal.setStyle(Paint.Style.FILL_AND_STROKE);
		mPaint_normal.setStrokeCap(Cap.ROUND);

		mPaint_selected = new Paint();
		mPaint_selected.setAntiAlias(true);
		mPaint_selected.setColor(mColorCircle_selected);
		mPaint_selected.setStyle(Paint.Style.FILL_AND_STROKE);
		mPaint_selected.setStrokeCap(Cap.ROUND);


		WindowManager windowManager = (WindowManager) getContext()
				.getSystemService(Context.WINDOW_SERVICE);
		displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		mCircleRadio = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				mCircleRadio, displayMetrics);
		
		mPaddingWidthSpace =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				mPaddingWidthSpace, displayMetrics);
		
		mPaddingHeightSpace =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				mPaddingHeightSpace, displayMetrics);

		
	}
	 /**
	  * 处理高宽
	  */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Float viewHeight = (mCircleRadio +mPaddingHeightSpace)* 2;
		Float viewWidth = mCircleRadio*2*mCircleNumber+mPaddingWidthSpace*(mCircleNumber+1);
		setMeasuredDimension(viewWidth.intValue(), viewHeight.intValue());

	}
	/**
	 * 切换到指定位置
	 * @param position
	 */
	public void selectPosition(int position){
		mCurrentSelected = position;
		mCurrentSelected = mCurrentSelected>mCircleNumber?mCircleNumber:mCurrentSelected;
		invalidate();
	}

	/**
	 * 初始化圆圈导航条
	 * @param circleNumber 圆圈的数量
	 */
	public void initCircle(int circleNumber){
		mCircleNumber = circleNumber;
		invalidate();
	}
	/**
	 * 绘图实现
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		for (int index = 0; index < mCircleNumber; index++) {

			float centerX = index * (mPaddingWidthSpace + mCircleRadio * 2)
					+ mCircleRadio+mPaddingWidthSpace;
			
			float centerY = mCircleRadio+mPaddingHeightSpace;

			if (index == mCurrentSelected) {
				canvas.drawCircle(centerX, centerY, mCircleRadio, mPaint_selected);
			} else {
				canvas.drawCircle(centerX, centerY, mCircleRadio, mPaint_normal);
			}

		}

	}

}
