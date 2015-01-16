package com.icode.library.widgets.switchButton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.code.icodelibrary.R;
/**
 * 滑动选项按钮
 *
 */
public class ISwitchButton  extends View implements OnTouchListener{
	/**
	 * 左边的图,右边的图,游标的图
	 */
	private Bitmap left_bg,right_bg,cursor_bg ;
	/**
	 * 游标的位置
	 */
	private boolean isLeft = false;
	//是否正在移动
	private boolean onMoving = false;
	//缩放
	private Matrix matrix = new Matrix();
	//画笔
	private Paint paint = new Paint();
	//游标居中位置
	private int cursorCenterX = 0 ;
	//选项发生变化时触发
	private OnChangedListener changedListener ;
	/**
	 * 当滑动开关选项发生变化时触发
	 * @author thank
	 *
	 */
	public interface OnChangedListener{
		void onSwitchChanged(boolean isLeft);
		
	}
	

	public ISwitchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ISwitchButton(Context context) {
		super(context);
		initView();
	}
	
	/**
	 * 初始化背景图
	 */
	private void initBackground(){
		left_bg = BitmapFactory.decodeResource(getResources(),
				R.drawable.common_switch_bg_on);
		right_bg = BitmapFactory.decodeResource(getResources(),
				R.drawable.common_switch_bg_off);
		cursor_bg = BitmapFactory.decodeResource(getResources(),
				R.drawable.common_switch_bg_btn);
		
	}
	/**
	 * 初始化UI
	 */
	 void initView(){
		initBackground();
		setOnTouchListener(this);
	}
	/**
	 * 绘图实现
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(onMoving){
			moving(canvas);
		}else {
			adjust(canvas);
		}
	}
	/**
	 * 运动状态下的绘图
	 * @param canvas
	 */
	private void moving(Canvas canvas){
		if(cursorCenterX<(right_bg.getWidth()/2)){
			canvas.drawBitmap(right_bg, matrix, paint);
		}else {
			canvas.drawBitmap(left_bg, matrix, paint);
		}
		//检查最小值
		int minX = cursor_bg.getWidth()/2;
		cursorCenterX = cursorCenterX<minX?minX:cursorCenterX;
		//检查最大值
		int maxX = right_bg.getWidth()-cursor_bg.getWidth()/2;
		cursorCenterX = cursorCenterX>maxX?maxX:cursorCenterX;
			
		canvas.drawBitmap(cursor_bg, cursorCenterX- cursor_bg.getWidth()/2, 0, paint);
	}
	
	
	
	/**
	 * 自动适配游标位置
	 * @param canvas
	 */
	private void adjust(Canvas canvas){
		int top = 0;
		int left = 0;
		if(isLeft){
			canvas.drawBitmap(right_bg, matrix, paint);
			canvas.drawBitmap(cursor_bg, left, top, paint);
		}else {
			left = right_bg.getWidth()-cursor_bg.getWidth();
			canvas.drawBitmap(left_bg, matrix, paint);
			canvas.drawBitmap(cursor_bg, left, top, paint);
		}
	}
	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			break;
		case MotionEvent.ACTION_MOVE:
			onMoving = true;
			cursorCenterX = Float.valueOf(event.getX()).intValue();
			break;

		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			onMoving = false;
			boolean last = isLeft;
			if(cursorCenterX<(right_bg.getWidth()/2)){
				isLeft = true;
			}else {
				isLeft = false;
			}
		//	Log.e("发生了变化", isLeft+"");
			if(last!=isLeft&&changedListener!=null){
				changedListener.onSwitchChanged(isLeft);
				//setLeft(isLeft);
				
			}
			
			break;

		default:
			break;
		}
		invalidate();
		return true;
	}
	/**
	 * 获取触发监听接口
	 * @return
	 */
	public OnChangedListener getChangedListener() {
		return changedListener;
	}
	/**
	 * 设置触发监听接口
	 * @param changedListener
	 */
	public void setChangedListener(OnChangedListener changedListener) {
		this.changedListener = changedListener;
	}

	/**
	 * 宽度处理
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(right_bg.getWidth(), right_bg.getHeight());
	}

	/**
	 * 设置当前选项
	 * @param isLeft 游标是否在左边
	 */
	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
		invalidate();
	}
	
	/**
	 * 设置样式
	 * @param resBg_on 打开状态时的背景 图
	 * @param resBg_off 关闭状态时的背景图
	 * @param resBg_cursor 游标
	 */
	public void setStyle(int resBg_on,int resBg_off,int resBg_cursor){
	  left_bg = BitmapFactory.decodeResource(getResources(),
	      resBg_on);
    right_bg = BitmapFactory.decodeResource(getResources(),
        resBg_off);
    cursor_bg = BitmapFactory.decodeResource(getResources(),
        resBg_cursor);
    invalidate();
	  
	}
	
}
