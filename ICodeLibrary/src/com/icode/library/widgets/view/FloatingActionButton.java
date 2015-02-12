package com.icode.library.widgets.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;

import com.code.icodelibrary.R;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
/**
 *  附带特效效果的按钮
 *  主要用于悬浮时按钮.例如listview较长时,需要一个快捷按钮方便用户迅速回到顶部,这时候就需要该实现了.
 */
@SuppressLint("NewApi")
public class FloatingActionButton extends View {

    private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    private final Paint mButtonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mDrawablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap;
    private int mColor;
    private boolean mHidden = false;
    private Rect rect;
    private int mLeftDisplayed = -1;
    private int mRightDisplayed = -1;
    private int mTopDisplayed = -1;
    private int mBottomDisplayed = -1;
    /**
     * The FAB button's Y position when it is displayed.
     */
    private float mYDisplayed = -1;
    /**
     * The FAB button's Y position when it is hidden.
     */
    private float mYHidden = -1;

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }


    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FloatingActionButton);
        mColor = a.getColor(R.styleable.FloatingActionButton_color, Color.WHITE);
        mButtonPaint.setStyle(Paint.Style.FILL);
        mButtonPaint.setColor(mColor);
        float radius, dx, dy;
        radius = a.getFloat(R.styleable.FloatingActionButton_shadowRadius, 10.0f);
        dx = a.getFloat(R.styleable.FloatingActionButton_shadowDx, 0.0f);
        dy = a.getFloat(R.styleable.FloatingActionButton_shadowDy, 3.5f);
        int color = a.getInteger(R.styleable.FloatingActionButton_shadowColor, Color.argb(100, 0, 0, 0));
        mButtonPaint.setShadowLayer(radius, dx, dy, color);

        Drawable drawable = a.getDrawable(R.styleable.FloatingActionButton_drawable);
        if (null != drawable) {
            mBitmap = ((BitmapDrawable) drawable).getBitmap();
        }
        setWillNotDraw(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        WindowManager mWindowManager = (WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE);
        Display display = mWindowManager.getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
            mYHidden = size.y;
        } else mYHidden = display.getHeight();
    }

    public static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }

    public void setColor(int color) {
        mColor = color;
        mButtonPaint.setColor(mColor);
        invalidate();
    }

    public void setDrawable(Drawable drawable) {
        mBitmap = ((BitmapDrawable) drawable).getBitmap();
        invalidate();
    }

    public void setShadow(float radius, float dx, float dy, int color) {
        mButtonPaint.setShadowLayer(radius, dx, dy, color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) (getWidth() / 2.6), mButtonPaint);
        if (null != mBitmap) {
            canvas.drawBitmap(mBitmap, (getWidth() - mBitmap.getWidth()) / 2,
                    (getHeight() - mBitmap.getHeight()) / 2, mDrawablePaint);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // Perform the default behavior
        super.onLayout(changed, left, top, right, bottom);
        if (mLeftDisplayed == -1) {
            mLeftDisplayed = left;
            mRightDisplayed = right;
            mTopDisplayed = top;
            mBottomDisplayed = bottom;
        }

        // Store the FAB button's displayed Y position if we are not already aware of it
        if (mYDisplayed == -1) {
            mYDisplayed = ViewHelper.getY(this);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int color;
        if (event.getAction() == MotionEvent.ACTION_UP) {
            color = mColor;
        } else {
            color = darkenColor(mColor);
            rect = new Rect(mLeftDisplayed, mTopDisplayed, mRightDisplayed, mBottomDisplayed);
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE){
            if (!rect.contains(mLeftDisplayed + (int) event.getX(), mTopDisplayed + (int) event.getY())){
                color = mColor;
            }
        }
        mButtonPaint.setColor(color);
        invalidate();
        return super.onTouchEvent(event);
    }

    public void hide(boolean hide) {
        // If the hidden state is being updated
        if (mHidden != hide) {

            // Store the new hidden state
            mHidden = hide;

            // Animate the FAB to it's new Y position
            ObjectAnimator animator = ObjectAnimator.ofFloat(this, "y", mHidden ? mYHidden : mYDisplayed).setDuration(500);
            animator.setInterpolator(mInterpolator);
            animator.start();
        }
    }

    /**
     * 简单的监听listview的滚动事件,然后做出响应.这里只是简单实践,实际应用中请根据具体情况,做出响应.
     * 可为listView添加setOnScrollListener的方式添加监听事件.
     * @param listView
     */
    public void listenTo(AbsListView listView) {
        if (null != listView) {
            listView.setOnScrollListener(new ISimpleFloatingListener(this));
        }
    }
    
   /**
    * 
    */
  private static class ISimpleFloatingListener implements AbsListView.OnScrollListener {

      private static final int DIRECTION_CHANGE_THRESHOLD = 1;
      private final FloatingActionButton mFloatingActionButton;
      private int mPrevPosition;
      private int mPrevTop;
      private boolean mUpdated;

      ISimpleFloatingListener(FloatingActionButton floatingActionButton) {
          mFloatingActionButton = floatingActionButton;
      }

      @Override
      public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
          final View topChild = view.getChildAt(0);
          int firstViewTop = 0;
          if (topChild != null) {
              firstViewTop = topChild.getTop();
          }
          boolean goingDown;
          boolean changed = true;
          if (mPrevPosition == firstVisibleItem) {
              final int topDelta = mPrevTop - firstViewTop;
              goingDown = firstViewTop < mPrevTop;
              changed = Math.abs(topDelta) > DIRECTION_CHANGE_THRESHOLD;
          } else {
              goingDown = firstVisibleItem > mPrevPosition;
          }
          if (changed && mUpdated) {
              mFloatingActionButton.hide(goingDown);
          }
          mPrevPosition = firstVisibleItem;
          mPrevTop = firstViewTop;
          mUpdated = true;
      }

      @Override
      public void onScrollStateChanged(AbsListView view, int scrollState) {
      }
  }
    
    
    
}
