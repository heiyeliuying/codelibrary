package com.icode.library.widgets.lock;



  import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Debug;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;

import com.code.icodelibrary.R;

  /**
   * 脱胎于android framework中的https://github.com/android/platform_frameworks_base/blob/master/core/java/com/android/internal/widget/LockPatternView.java
   * 展示一个3*3的密码锁屏界面.
   * Displays and detects the user's unlock attempt, which is a drag of a finger
   * across 9 regions of the screen.
   * 
   * Is also capable of displaying a static pattern in "in progress", "wrong" or
   * "correct" states.
   */
  public class ISimpleLockPatternView extends View {

    // Aspect to use when rendering this view
    private static final int ASPECT_SQUARE = 0; // View will be the minimum of
                                                // width/height
    private static final int ASPECT_LOCK_WIDTH = 1; // Fixed width; height will
                                                    // be minimum of (w,h)
    private static final int ASPECT_LOCK_HEIGHT = 2; // Fixed height; width will
                                                     // be minimum of (w,h)

    private static final boolean PROFILE_DRAWING = false;
    private  CellState[][] mCellStates;

    private  int mDotSize;
    private  int mDotSizeActivated;
    private int mPathWidth;

    private boolean mDrawingProfilingStarted = false;

    private Paint mPaint = new Paint();
    private Paint mPathPaint = new Paint();

    /**
     * How many milliseconds we spend animating each circle of a lock pattern if
     * the animating mode is set. The entire animation should take this constant *
     * the length of the pattern to complete.
     */
    private static final int MILLIS_PER_CIRCLE_ANIMATING = 700;

    /**
     * This can be used to avoid updating the display for very small motions or
     * noisy panels. It didn't seem to have much impact on the devices tested, so
     * currently set to 0.
     */
    private static final float DRAG_THRESHHOLD = 0.0f;

    private OnPatternListener mOnPatternListener;
    private ArrayList<Cell> mPattern = new ArrayList<Cell>(Cell.MAX_ROWS * Cell.MAX_COLS);

    /**
     * Lookup table for the circles of the pattern we are currently drawing. This
     * will be the cells of the complete pattern unless we are animating, in which
     * case we use this to hold the cells we are drawing for the in progress
     * animation. 已经被选中的图案节点
     */
    private boolean[][] mPatternDrawLookup = new boolean[Cell.MAX_ROWS][Cell.MAX_COLS];

    /**
     * the in progress point: - during interaction: where the user's finger is -
     * during animation: the current tip of the animating line
     */
    private float mInProgressX = -1;
    private float mInProgressY = -1;

    private long mAnimatingPeriodStart;

    private DisplayMode mPatternDisplayMode = DisplayMode.Normal;
    private boolean mInputEnabled = true;
    private boolean mInStealthMode = false;
    private boolean mEnableHapticFeedback = true;
    private boolean mPatternInProgress = false;

    private float mHitFactor = 0.6f;

    private float mSquareWidth;
    private float mSquareHeight;

    private final Path mCurrentPath = new Path();
    private final Rect mInvalidate = new Rect();
    private final Rect mTmpInvalidateRect = new Rect();

    private int mAspect;
    private int mRegularColor;
    private int mErrorColor;
    private int mSuccessColor;

    private Interpolator mFastOutSlowInInterpolator;
    private Interpolator mLinearOutSlowInInterpolator;

    private LockPatternItem mPatternItem = LockPatternItem.getInstance(getContext());
    
    

    public LockPatternItem getPatternItem() {
      return mPatternItem;
    }

    public void setPatternItem(LockPatternItem mPatternItem) {
      this.mPatternItem = mPatternItem;
      initViews();
    }

    /**
     * Represents a cell in the 3 X 3 matrix of the unlock pattern view.
     */
    public static class Cell {

      static final int MAX_ROWS = 3;
      static final int MAX_COLS = 3;

      int row;
      int column;

      // keep # objects limited to 9
      static Cell[][] sCells = new Cell[MAX_ROWS][MAX_COLS];
      static {
        for (int i = 0; i < MAX_ROWS; i++) {
          for (int j = 0; j < MAX_COLS; j++) {
            sCells[i][j] = new Cell(i, j);
          }
        }
      }

      /**
       * @param row
       *          The row of the cell.
       * @param column
       *          The column of the cell.
       */
      private Cell(int row, int column) {
        checkRange(row, column);
        this.row = row;
        this.column = column;
      }

      public int getRow() {
        return row;
      }

      public int getColumn() {
        return column;
      }

      /**
       * @param row
       *          The row of the cell.
       * @param column
       *          The column of the cell.
       */
      public static synchronized Cell of(int row, int column) {
        checkRange(row, column);
        return sCells[row][column];
      }

      private static void checkRange(int row, int column) {
        if (row < 0 || row > MAX_ROWS) {
          throw new IllegalArgumentException("row must be in range 0-" + (MAX_ROWS - 1));
        }
        if (column < 0 || column > MAX_COLS) {
          throw new IllegalArgumentException("column must be in range 0-" + (MAX_COLS - 1));
        }
      }

      public String toString() {
        return "(row=" + row + ",clmn=" + column + ")";
      }
    }

    public static class CellState {

      public float scale = 1.0f;
      public float translateY = 0.0f;
      public float alpha = 1.0f;
      public float size;
      public float lineEndX = Float.MIN_VALUE;
      public float lineEndY = Float.MIN_VALUE;
      public ValueAnimator lineAnimator;
    }

    /**
     * How to display the current pattern.
     */
    public enum DisplayMode {

      /**
       * The pattern drawn is correct (i.e draw it in a friendly color)
       * 正确,相当于success
       */
      Correct,

      /**
       * Animate the pattern (for demo, and help). 动画
       */
      Animate,

      /**
       * The pattern is wrong (i.e draw a foreboding color) 错误
       */
      Wrong,
      /**
       * 默认状态
       */
      Normal;
    }

    /**
     * The call back interface for detecting patterns entered by the user.
     */
    public static interface OnPatternListener {

      /**
       * A new pattern has begun.
       */
      void onPatternStart();

      /**
       * The pattern was cleared.
       */
      void onPatternCleared();

      /**
       * The user extended the pattern currently being drawn by one cell.
       * 
       * @param pattern
       *          The pattern with newly added cell.
       */
      void onPatternCellAdded(List<Cell> pattern);

      /**
       * A pattern was detected from the user.
       * 
       * @param pattern
       *          The pattern.
       */
      void onPatternDetected(List<Cell> pattern);
    }

    public ISimpleLockPatternView(Context context) {
      this(context, null);
      initViews();
    }

    public ISimpleLockPatternView(Context context, AttributeSet attrs) {
      super(context, attrs);
      initViews();
    }

    private void initViews(){
      

    

      final String aspect = "square";

      if ("square".equals(aspect)) {
        mAspect = ASPECT_SQUARE;
      } else if ("lock_width".equals(aspect)) {
        mAspect = ASPECT_LOCK_WIDTH;
      } else if ("lock_height".equals(aspect)) {
        mAspect = ASPECT_LOCK_HEIGHT;
      } else {
        mAspect = ASPECT_SQUARE;
      }

      setClickable(true);

      mPathPaint.setAntiAlias(true);
      mPathPaint.setDither(true);

      mRegularColor = mPatternItem.mRegularColor;
      mErrorColor = mPatternItem.mErrorColor;
      mSuccessColor = mPatternItem.mSuccessColor;

      mPathPaint.setColor(mPatternItem.mRegularColor);

      mPathPaint.setStyle(Paint.Style.STROKE);
      mPathPaint.setStrokeJoin(Paint.Join.ROUND);
      mPathPaint.setStrokeCap(Paint.Cap.ROUND);

      mPathWidth = dp2px(mPatternItem.lineWidthInDp);
      mPathPaint.setStrokeWidth(mPathWidth);

      mDotSize = dp2px(mPatternItem.mDotSizeInDp);

      mDotSizeActivated = dp2px(mPatternItem.mDotSizeActivatedInDp);

      mPaint.setAntiAlias(true);
      mPaint.setDither(true);

      // 设置每个节点的尺寸
      mCellStates = new CellState[Cell.MAX_ROWS][Cell.MAX_COLS];
      for (int i = 0; i < Cell.MAX_ROWS; i++) {
        for (int j = 0; j < Cell.MAX_COLS; j++) {
          mCellStates[i][j] = new CellState();
          mCellStates[i][j].size = mDotSize;
        }
      }

    
      
    }
    
    
    
    public CellState[][] getCellStates() {
      return mCellStates;
    }

    /**
     * @return Whether the view is in stealth mode.
     */
    public boolean isInStealthMode() {
      return mInStealthMode;
    }

    /**
     * @return Whether the view has tactile feedback enabled.
     */
    public boolean isTactileFeedbackEnabled() {
      return mEnableHapticFeedback;
    }

    /**
     * Set whether the view is in stealth mode. If true, there will be no visible
     * feedback as the user enters the pattern.
     * 
     * @param inStealthMode
     *          Whether in stealth mode.
     */
    public void setInStealthMode(boolean inStealthMode) {
      mInStealthMode = inStealthMode;
    }

    /**
     * Set whether the view will use tactile feedback. If true, there will be
     * tactile feedback as the user enters the pattern.
     * 
     * @param tactileFeedbackEnabled
     *          Whether tactile feedback is enabled
     */
    public void setTactileFeedbackEnabled(boolean tactileFeedbackEnabled) {
      mEnableHapticFeedback = tactileFeedbackEnabled;
    }

    /**
     * Set the call back for pattern detection.
     * 
     * @param onPatternListener
     *          The call back.
     */
    public void setOnPatternListener(OnPatternListener onPatternListener) {
      mOnPatternListener = onPatternListener;
    }

    /**
     * Set the pattern explicitely (rather than waiting for the user to input a
     * pattern). 设置图案,并且以指定的样式绘制该图案
     * 
     * @param displayMode
     *          How to display the pattern.
     * @param pattern
     *          The pattern.
     */
    public void setPattern(DisplayMode displayMode, List<Cell> pattern) {
      mPattern.clear();
      mPattern.addAll(pattern);
      clearPatternDrawLookup();
      for (Cell cell : pattern) {
        mPatternDrawLookup[cell.getRow()][cell.getColumn()] = true;
      }

      setDisplayMode(displayMode);
    }

    /**
     * Set the display mode of the current pattern. This can be useful, for
     * instance, after detecting a pattern to tell this view whether change the in
     * progress result to correct or wrong.
     * 设置当前已绘制图案的展示样式.该方法用处非常大,当我们绘制了一个图案后,如果判断该图案失败,可以调用此方法,绘制不同的颜色来警示用户
     * 
     * @param displayMode
     *          The display mode.
     */
    public void setDisplayMode(DisplayMode displayMode) {
      mPatternDisplayMode = displayMode;
      if (displayMode == DisplayMode.Animate) {
        if (mPattern.size() == 0) {
          throw new IllegalStateException("you must have a pattern to "
              + "animate if you want to set the display mode to animate");
        }
        mAnimatingPeriodStart = SystemClock.elapsedRealtime();
        final Cell first = mPattern.get(0);
        mInProgressX = getCenterXForColumn(first.getColumn());
        mInProgressY = getCenterYForRow(first.getRow());
        clearPatternDrawLookup();
      }
      invalidate();
    }

    private void notifyCellAdded() {
      if (mOnPatternListener != null) {
        mOnPatternListener.onPatternCellAdded(mPattern);
      }
    }

    private void notifyPatternStarted() {
      if (mOnPatternListener != null) {
        mOnPatternListener.onPatternStart();
      }
    }

    private void notifyPatternDetected() {
      if (mOnPatternListener != null) {
        mOnPatternListener.onPatternDetected(mPattern);
      }
    }

    private void notifyPatternCleared() {
      if (mOnPatternListener != null) {
        mOnPatternListener.onPatternCleared();
      }
    }

    /**
     * Clear the pattern. 清除图案,重置画笔,同resetPattern()
     */
    public void clearPattern() {
      resetPattern();
    }

    /**
     * Reset all pattern state. 清除图案,重置画笔,同clearPattern()
     */
    private void resetPattern() {
      mPattern.clear();
      clearPatternDrawLookup();
      mPatternDisplayMode = DisplayMode.Normal;
      invalidate();
    }

    /**
     * Clear the pattern lookup table.
     */
    private void clearPatternDrawLookup() {
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          mPatternDrawLookup[i][j] = false;
        }
      }
    }

    /**
     * Disable input (for instance when displaying a message that will timeout so
     * user doesn't get view into messy state). 禁用输入
     */
    public void disableInput() {
      mInputEnabled = false;
    }

    /**
     * Enable input. 启用输入
     */
    public void enableInput() {
      mInputEnabled = true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
      final int width = w - getPaddingLeft() - getPaddingRight();
      mSquareWidth = width / Cell.MAX_COLS;

      final int height = h - getPaddingTop() - getPaddingBottom();
      mSquareHeight = height / Cell.MAX_ROWS;
    }

    private int resolveMeasured(int measureSpec, int desired) {
      int result = 0;
      int specSize = MeasureSpec.getSize(measureSpec);
      switch (MeasureSpec.getMode(measureSpec)) {
      case MeasureSpec.UNSPECIFIED:
        result = desired;
        break;
      case MeasureSpec.AT_MOST:
        result = Math.max(specSize, desired);
        break;
      case MeasureSpec.EXACTLY:
      default:
        result = specSize;
      }
      return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      final int minimumWidth = getSuggestedMinimumWidth();
      final int minimumHeight = getSuggestedMinimumHeight();
      int viewWidth = resolveMeasured(widthMeasureSpec, minimumWidth);
      int viewHeight = resolveMeasured(heightMeasureSpec, minimumHeight);

      switch (mAspect) {
      case ASPECT_SQUARE:
        viewWidth = viewHeight = Math.min(viewWidth, viewHeight);
        break;
      case ASPECT_LOCK_WIDTH:
        viewHeight = Math.min(viewWidth, viewHeight);
        break;
      case ASPECT_LOCK_HEIGHT:
        viewWidth = Math.min(viewWidth, viewHeight);
        break;
      }
      // Log.v(TAG, "LockPatternView dimensions: " + viewWidth + "x" +
      // viewHeight);
      setMeasuredDimension(viewWidth, viewHeight);
    }

    /**
     * Determines whether the point x, y will add a new point to the current
     * pattern (in addition to finding the cell, also makes heuristic choices such
     * as filling in gaps based on current pattern).
     * 
     * @param x
     *          The x coordinate.
     * @param y
     *          The y coordinate.
     */
    private Cell detectAndAddHit(float x, float y) {
      final Cell cell = checkForNewHit(x, y);
      if (cell != null) {

        // check for gaps in existing pattern
        Cell fillInGapCell = null;
        final ArrayList<Cell> pattern = mPattern;
        if (!pattern.isEmpty()) {
          final Cell lastCell = pattern.get(pattern.size() - 1);
          int dRow = cell.row - lastCell.row;
          int dColumn = cell.column - lastCell.column;

          int fillInRow = lastCell.row;
          int fillInColumn = lastCell.column;

          if (Math.abs(dRow) == 2 && Math.abs(dColumn) != 1) {
            fillInRow = lastCell.row + ((dRow > 0) ? 1 : -1);
          }

          if (Math.abs(dColumn) == 2 && Math.abs(dRow) != 1) {
            fillInColumn = lastCell.column + ((dColumn > 0) ? 1 : -1);
          }

          fillInGapCell = Cell.of(fillInRow, fillInColumn);
        }

        if (fillInGapCell != null && !mPatternDrawLookup[fillInGapCell.row][fillInGapCell.column]) {
          addCellToPattern(fillInGapCell);
        }
        addCellToPattern(cell);
        if (mEnableHapticFeedback) {
          performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY,
              HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING
                  | HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
        }
        return cell;
      }
      return null;
    }

    private void addCellToPattern(Cell newCell) {
      mPatternDrawLookup[newCell.getRow()][newCell.getColumn()] = true;
      mPattern.add(newCell);
      if (!mInStealthMode) {
        startCellActivatedAnimation(newCell);
      }
      notifyCellAdded();
    }

    private void startCellActivatedAnimation(Cell cell) {
      final CellState cellState = mCellStates[cell.row][cell.column];
      startSizeAnimation(mDotSize, mDotSizeActivated, 96, mLinearOutSlowInInterpolator, cellState,
          new Runnable() {

            @Override
            public void run() {
              startSizeAnimation(mDotSizeActivated, mDotSize, 192, mFastOutSlowInInterpolator,
                  cellState, null);
            }
          });
      startLineEndAnimation(cellState, mInProgressX, mInProgressY, getCenterXForColumn(cell.column),
          getCenterYForRow(cell.row));
    }

    private void startLineEndAnimation(final CellState state, final float startX, final float startY,
        final float targetX, final float targetY) {
      ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
      valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
          float t = (Float) animation.getAnimatedValue();
          state.lineEndX = (1 - t) * startX + t * targetX;
          state.lineEndY = (1 - t) * startY + t * targetY;
          invalidate();
        }
      });
      valueAnimator.addListener(new AnimatorListenerAdapter() {

        @Override
        public void onAnimationEnd(Animator animation) {
          state.lineAnimator = null;
        }
      });
      valueAnimator.setInterpolator(mFastOutSlowInInterpolator);
      valueAnimator.setDuration(100);
      valueAnimator.start();
      state.lineAnimator = valueAnimator;
    }

    private void startSizeAnimation(float start, float end, long duration, Interpolator interpolator,
        final CellState state, final Runnable endRunnable) {
      ValueAnimator valueAnimator = ValueAnimator.ofFloat(start, end);
      valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
          state.size = (Float) animation.getAnimatedValue();
          invalidate();
        }
      });
      if (endRunnable != null) {
        valueAnimator.addListener(new AnimatorListenerAdapter() {

          @Override
          public void onAnimationEnd(Animator animation) {
            endRunnable.run();
          }
        });
      }
      valueAnimator.setInterpolator(interpolator);
      valueAnimator.setDuration(duration);
      valueAnimator.start();
    }

    // helper method to find which cell a point maps to
    private Cell checkForNewHit(float x, float y) {

      final int rowHit = getRowHit(y);
      if (rowHit < 0) {
        return null;
      }
      final int columnHit = getColumnHit(x);
      if (columnHit < 0) {
        return null;
      }

      if (mPatternDrawLookup[rowHit][columnHit]) {
        return null;
      }
      if(mPatternItem.isVibrateEnable)
        vibrate();
      return Cell.of(rowHit, columnHit);
    }

    /**
     * 震动
     */
    private void vibrate() {
      Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
      vibrator.vibrate(300);

    }

    /**
     * Helper method to find the row that y falls into.
     * 
     * @param y
     *          The y coordinate
     * @return The row that y falls in, or -1 if it falls in no row.
     */
    private int getRowHit(float y) {

      final float squareHeight = mSquareHeight;
      float hitSize = squareHeight * mHitFactor;

      float offset = getPaddingTop() + (squareHeight - hitSize) / 2f;
      for (int i = 0; i < 3; i++) {

        final float hitTop = offset + squareHeight * i;
        if (y >= hitTop && y <= hitTop + hitSize) {
          return i;
        }
      }
      return -1;
    }

    /**
     * Helper method to find the column x fallis into.
     * 
     * @param x
     *          The x coordinate.
     * @return The column that x falls in, or -1 if it falls in no column.
     */
    private int getColumnHit(float x) {
      final float squareWidth = mSquareWidth;
      float hitSize = squareWidth * mHitFactor;

      float offset = getPaddingLeft() + (squareWidth - hitSize) / 2f;
      for (int i = 0; i < 3; i++) {

        final float hitLeft = offset + squareWidth * i;
        if (x >= hitLeft && x <= hitLeft + hitSize) {
          return i;
        }
      }
      return -1;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean onHoverEvent(MotionEvent event) {
      if (((AccessibilityManager) getContext().getSystemService(Context.ACCESSIBILITY_SERVICE))
          .isTouchExplorationEnabled()) {
        final int action = event.getAction();
        switch (action) {
        case MotionEvent.ACTION_HOVER_ENTER:
          event.setAction(MotionEvent.ACTION_DOWN);
          break;
        case MotionEvent.ACTION_HOVER_MOVE:
          event.setAction(MotionEvent.ACTION_MOVE);
          break;
        case MotionEvent.ACTION_HOVER_EXIT:
          event.setAction(MotionEvent.ACTION_UP);
          break;
        }
        onTouchEvent(event);
        event.setAction(action);
      }
      return super.onHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
      if (!mInputEnabled || !isEnabled()) {
        return false;
      }

      switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        handleActionDown(event);
        return true;
      case MotionEvent.ACTION_UP:
        handleActionUp(event);
        return true;
      case MotionEvent.ACTION_MOVE:
        handleActionMove(event);
        return true;
      case MotionEvent.ACTION_CANCEL:
        if (mPatternInProgress) {
          mPatternInProgress = false;
          resetPattern();
          notifyPatternCleared();
        }
        if (PROFILE_DRAWING) {
          if (mDrawingProfilingStarted) {
            Debug.stopMethodTracing();
            mDrawingProfilingStarted = false;
          }
        }
        return true;
      }
      return false;
    }

    private void handleActionMove(MotionEvent event) {
      // Handle all recent motion events so we don't skip any cells even when
      // the device
      // is busy...
      final float radius = mPathWidth;
      final int historySize = event.getHistorySize();
      mTmpInvalidateRect.setEmpty();
      boolean invalidateNow = false;
      for (int i = 0; i < historySize + 1; i++) {
        final float x = i < historySize ? event.getHistoricalX(i) : event.getX();
        final float y = i < historySize ? event.getHistoricalY(i) : event.getY();
        Cell hitCell = detectAndAddHit(x, y);
        final int patternSize = mPattern.size();
        if (hitCell != null && patternSize == 1) {
          mPatternInProgress = true;
          notifyPatternStarted();
        }
        // note current x and y for rubber banding of in progress patterns
        final float dx = Math.abs(x - mInProgressX);
        final float dy = Math.abs(y - mInProgressY);
        if (dx > DRAG_THRESHHOLD || dy > DRAG_THRESHHOLD) {
          invalidateNow = true;
        }

        if (mPatternInProgress && patternSize > 0) {
          final ArrayList<Cell> pattern = mPattern;
          final Cell lastCell = pattern.get(patternSize - 1);
          float lastCellCenterX = getCenterXForColumn(lastCell.column);
          float lastCellCenterY = getCenterYForRow(lastCell.row);

          // Adjust for drawn segment from last cell to (x,y). Radius
          // accounts for line width.
          float left = Math.min(lastCellCenterX, x) - radius;
          float right = Math.max(lastCellCenterX, x) + radius;
          float top = Math.min(lastCellCenterY, y) - radius;
          float bottom = Math.max(lastCellCenterY, y) + radius;

          // Invalidate between the pattern's new cell and the pattern's
          // previous cell
          if (hitCell != null) {
            final float width = mSquareWidth * 0.5f;
            final float height = mSquareHeight * 0.5f;
            final float hitCellCenterX = getCenterXForColumn(hitCell.column);
            final float hitCellCenterY = getCenterYForRow(hitCell.row);

            left = Math.min(hitCellCenterX - width, left);
            right = Math.max(hitCellCenterX + width, right);
            top = Math.min(hitCellCenterY - height, top);
            bottom = Math.max(hitCellCenterY + height, bottom);
          }

          // Invalidate between the pattern's last cell and the previous
          // location
          mTmpInvalidateRect.union(Math.round(left), Math.round(top), Math.round(right),
              Math.round(bottom));
        }
      }
      mInProgressX = event.getX();
      mInProgressY = event.getY();

      // To save updates, we only invalidate if the user moved beyond a
      // certain amount.
      if (invalidateNow) {
        mInvalidate.union(mTmpInvalidateRect);
        invalidate(mInvalidate);
        mInvalidate.set(mTmpInvalidateRect);
      }
    }


    private void handleActionUp(MotionEvent event) {
      // report pattern detected
      if (!mPattern.isEmpty()) {
        mPatternInProgress = false;
        cancelLineAnimations();
        notifyPatternDetected();
        invalidate();
      }
      if (PROFILE_DRAWING) {
        if (mDrawingProfilingStarted) {
          Debug.stopMethodTracing();
          mDrawingProfilingStarted = false;
        }
      }
    }

    private void cancelLineAnimations() {
      for (int i = 0; i < Cell.MAX_ROWS; i++) {
        for (int j = 0; j < Cell.MAX_COLS; j++) {
          CellState state = mCellStates[i][j];
          if (state.lineAnimator != null) {
            state.lineAnimator.cancel();
            state.lineEndX = Float.MIN_VALUE;
            state.lineEndY = Float.MIN_VALUE;
          }
        }
      }
    }

    private void handleActionDown(MotionEvent event) {
      resetPattern();
      final float x = event.getX();
      final float y = event.getY();
      final Cell hitCell = detectAndAddHit(x, y);
      if (hitCell != null) {
        mPatternInProgress = true;
        mPatternDisplayMode = DisplayMode.Normal;
        notifyPatternStarted();
      } else if (mPatternInProgress) {
        mPatternInProgress = false;
        notifyPatternCleared();
      }
      if (hitCell != null) {
        final float startX = getCenterXForColumn(hitCell.column);
        final float startY = getCenterYForRow(hitCell.row);

        final float widthOffset = mSquareWidth / 2f;
        final float heightOffset = mSquareHeight / 2f;

        invalidate((int) (startX - widthOffset), (int) (startY - heightOffset),
            (int) (startX + widthOffset), (int) (startY + heightOffset));
      }
      mInProgressX = x;
      mInProgressY = y;
      if (PROFILE_DRAWING) {
        if (!mDrawingProfilingStarted) {
          Debug.startMethodTracing("LockPatternDrawing");
          mDrawingProfilingStarted = true;
        }
      }
    }

    private float getCenterXForColumn(int column) {
      return getPaddingLeft() + column * mSquareWidth + mSquareWidth / 2f;
    }

    private float getCenterYForRow(int row) {
      return getPaddingTop() + row * mSquareHeight + mSquareHeight / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
      final ArrayList<Cell> pattern = mPattern;
      final int count = pattern.size();
      final boolean[][] drawLookup = mPatternDrawLookup;

      if (mPatternDisplayMode == DisplayMode.Animate) {

        // figure out which circles to draw

        // + 1 so we pause on complete pattern
        final int oneCycle = (count + 1) * MILLIS_PER_CIRCLE_ANIMATING;
        final int spotInCycle = (int) (SystemClock.elapsedRealtime() - mAnimatingPeriodStart)
            % oneCycle;
        final int numCircles = spotInCycle / MILLIS_PER_CIRCLE_ANIMATING;

        clearPatternDrawLookup();
        for (int i = 0; i < numCircles; i++) {
          final Cell cell = pattern.get(i);
          drawLookup[cell.getRow()][cell.getColumn()] = true;
        }

        // figure out in progress portion of ghosting line

        final boolean needToUpdateInProgressPoint = numCircles > 0 && numCircles < count;

        if (needToUpdateInProgressPoint) {
          final float percentageOfNextCircle = ((float) (spotInCycle % MILLIS_PER_CIRCLE_ANIMATING))
              / MILLIS_PER_CIRCLE_ANIMATING;

          final Cell currentCell = pattern.get(numCircles - 1);
          final float centerX = getCenterXForColumn(currentCell.column);
          final float centerY = getCenterYForRow(currentCell.row);

          final Cell nextCell = pattern.get(numCircles);
          final float dx = percentageOfNextCircle * (getCenterXForColumn(nextCell.column) - centerX);
          final float dy = percentageOfNextCircle * (getCenterYForRow(nextCell.row) - centerY);
          mInProgressX = centerX + dx;
          mInProgressY = centerY + dy;
        }
        // TODO: Infinite loop here...
        invalidate();
      }

      final Path currentPath = mCurrentPath;
      currentPath.rewind();

      // draw the circles
      for (int i = 0; i < 3; i++) {
        float centerY = getCenterYForRow(i);
        for (int j = 0; j < 3; j++) {
          CellState cellState = mCellStates[i][j];
          float centerX = getCenterXForColumn(j);
          float size = cellState.size * cellState.scale;
          float translationY = cellState.translateY;
          drawCircleNode(canvas, (int) centerX, (int) centerY + translationY, size, drawLookup[i][j],
              cellState.alpha);
        }
      }

      // TODO: the path should be created and cached every time we hit-detect
      // a cell
      // only the last segment of the path should be computed here
      // draw the path of the pattern (unless we are in stealth mode)
      final boolean drawPath = !mInStealthMode;

      if (drawPath) {
        mPathPaint.setColor(getCurrentColor(true /* partOfPattern */));

        boolean anyCircles = false;
        float lastX = 0f;
        float lastY = 0f;
        for (int i = 0; i < count; i++) {
          Cell cell = pattern.get(i);

          // only draw the part of the pattern stored in
          // the lookup table (this is only different in the case
          // of animation).
          if (!drawLookup[cell.row][cell.column]) {
            break;
          }
          anyCircles = true;

          float centerX = getCenterXForColumn(cell.column);
          float centerY = getCenterYForRow(cell.row);
          if (i != 0) {
            CellState state = mCellStates[cell.row][cell.column];
            currentPath.rewind();
            currentPath.moveTo(lastX, lastY);
            if (state.lineEndX != Float.MIN_VALUE && state.lineEndY != Float.MIN_VALUE) {
              currentPath.lineTo(state.lineEndX, state.lineEndY);
            } else {
              currentPath.lineTo(centerX, centerY);
            }
            canvas.drawPath(currentPath, mPathPaint);
          }
          lastX = centerX;
          lastY = centerY;
        }

        // draw last in progress section
        if ((mPatternInProgress || mPatternDisplayMode == DisplayMode.Animate) && anyCircles) {
          currentPath.rewind();
          currentPath.moveTo(lastX, lastY);
          currentPath.lineTo(mInProgressX, mInProgressY);

          mPathPaint.setAlpha((int) (calculateLastSegmentAlpha(mInProgressX, mInProgressY, lastX,
              lastY) * 255f));
          canvas.drawPath(currentPath, mPathPaint);
        }
      }
    }

    private float calculateLastSegmentAlpha(float x, float y, float lastX, float lastY) {
      float diffX = x - lastX;
      float diffY = y - lastY;
      float dist = (float) Math.sqrt(diffX * diffX + diffY * diffY);
      float frac = dist / mSquareWidth;
      return Math.min(1f, Math.max(0f, (frac - 0.3f) * 4f));
    }

    /**
     * 根据展现模式获取画笔颜色
     * 
     * @param partOfPattern
     * @return
     */
    private int getCurrentColor(boolean partOfPattern) {

      if (!partOfPattern || mInStealthMode || mPatternInProgress) {
        return mRegularColor;
      }
      if (mPatternDisplayMode == DisplayMode.Wrong) {
        // the pattern is wrong
        return mErrorColor;
      }
      if (mPatternDisplayMode == DisplayMode.Correct) {
        return mSuccessColor;
      }
      if (mPatternDisplayMode == DisplayMode.Animate) {
        return mRegularColor;
      }
      return mRegularColor;

      /*
       * if (!partOfPattern || mInStealthMode || mPatternInProgress) { //
       * unselected circle return mRegularColor; } else if (mPatternDisplayMode ==
       * DisplayMode.Wrong) { // the pattern is wrong return mErrorColor; } else
       * if (mPatternDisplayMode == DisplayMode.Correct ) { return mSuccessColor;
       * } else if(mPatternDisplayMode == DisplayMode.Animate){ return
       * mRegularColor; }else{ return mRegularColor; }
       */
    }

    /**
     * @param partOfPattern
     *          Whether this circle is part of the pattern.
     */
    private void drawCircleNode(Canvas canvas, float centerX, float centerY, float size,
        boolean partOfPattern, float alpha) {
      mPaint.setColor(getCurrentColor(partOfPattern));
      mPaint.setAlpha((int) (alpha * 255));
      canvas.drawCircle(centerX, centerY, size / 2, mPaint);

      if (partOfPattern) {
        switch (mPatternDisplayMode) {
        case Correct:
          canvas.drawBitmap(mPatternItem.successNodeBitmap,
              centerX - mPatternItem.successNodeBitmap.getWidth() / 2, centerY
                  - mPatternItem.successNodeBitmap.getHeight() / 2, mPaint);
          break;
        case Wrong:
          canvas.drawBitmap(mPatternItem.errorNodeBitmap,
              centerX - mPatternItem.errorNodeBitmap.getWidth() / 2, centerY
                  - mPatternItem.errorNodeBitmap.getHeight() / 2, mPaint);
          break;
        case Normal:
          canvas.drawBitmap(mPatternItem.activeNodeBitmap,
              centerX - mPatternItem.activeNodeBitmap.getWidth() / 2, centerY
                  - mPatternItem.activeNodeBitmap.getHeight() / 2, mPaint);
          break;
        default:
          canvas.drawBitmap(mPatternItem.activeNodeBitmap,
              centerX - mPatternItem.activeNodeBitmap.getWidth() / 2, centerY
                  - mPatternItem.activeNodeBitmap.getHeight() / 2, mPaint);
          break;
        }

      }
    }

    /**
     * 将dp转换为px
     * 
     * @param number
     * @return
     */
    int dp2px(int number) {
      DisplayMetrics displaysMetrics = new DisplayMetrics();
      WindowManager windowManager = (WindowManager) getContext().getSystemService(
          Context.WINDOW_SERVICE);
      windowManager.getDefaultDisplay().getMetrics(displaysMetrics);
      return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, number, displaysMetrics);
    }

    /**
     * LockPatternView的属性条目
     */
    public static class LockPatternItem {
      

      /**
       * 线条宽度
       */
      public int lineWidthInDp = 5 ;
      
      /**
       * 绘制节点的颜色
       */
      public int mRegularColor = Color.parseColor("#007eff");
      /**
       * 错误时颜色
       */
      public int  mErrorColor =Color.parseColor("#ff0000");
      /**
       * 成功时颜色
       */
      public int mSuccessColor  =  Color.parseColor("#00ff00");
      
      
      /**
       * 普通的激活状态的bitmap
       */
      public Bitmap activeNodeBitmap = null;
      /**
       * 成功状态的bitmap
       */
      public Bitmap successNodeBitmap = null;
      /**
       * 错误级别的bitmap
       */
      public Bitmap errorNodeBitmap = null;
      /**
       * 是否允许震动
       */
      public boolean isVibrateEnable = true ;
      
      /**
       * 节点的大小
       */
      public int mDotSizeInDp = 12 ;
      /**
       * 节点被激活时的大小
       */
      public int mDotSizeActivatedInDp = 28;
      
      
      public static LockPatternItem getInstance(Context context){
        LockPatternItem lockPatternItem = new LockPatternItem();
        lockPatternItem.activeNodeBitmap =
            BitmapFactory.decodeResource(context.getResources(), R.drawable.lock_pattern_normal);
        lockPatternItem.errorNodeBitmap =
            BitmapFactory.decodeResource(context.getResources(), R.drawable.lock_pattern_error);
        lockPatternItem.successNodeBitmap =
            BitmapFactory.decodeResource(context.getResources(), R.drawable.lock_pattern_success);
        return lockPatternItem ;
        
      }
      
      
    }  
    
  }
