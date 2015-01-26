package com.icode.library.widgets.sectionIndexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
/**
 * 快速检索导航栏的简单实现,仿微信通讯录导航栏
 */
public class ISimpleSectionIndicator extends View {
  
  private SectionIndexerChangedListener sectionIndexerChangedListener;
  
  private static final int POSITION_DISABLE = -1 ;
  //首字符
  private List<String> alphabets = new ArrayList<String>();
 
  //高度
  private int totalHeight =  0;
  //条目高度
  private int itemHeight = 0 ;
  //条目宽度
  private int itemWidth = 0;
  
  private Paint paint ;
  //背景颜色
  private int color_bg = Color.parseColor("#55515151");
  //section字体的颜色
  private int color_section = Color.parseColor("#424242");
  private int fontSize = 17;

  public ISimpleSectionIndicator(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initViews();
  }

  public ISimpleSectionIndicator(Context context, AttributeSet attrs) {
    super(context, attrs);
    initViews();
  }

  public ISimpleSectionIndicator(Context context) {
    super(context);
    initViews();
  }

  
  private void initViews(){
    initData();
    initPaint();
    setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        
      }
    });
  }
  
  private void initData(){
     String [] sections = new String []{
        "↑","☆","A","B","C","D","E","F","G","H","I",
        "J","K","L","M","N","O","P","Q","R",
        "S","T","U","V","W","X","Y","Z","#"
      };
    
    this.alphabets =  Arrays.asList(sections);
     
  }
  
 
  /**
   * 初始化画笔
   */
  private void initPaint(){
    
    paint = new Paint();
    paint.setColor(color_section);
    paint.setTextAlign(Align.CENTER);
    paint.setAntiAlias(true);
    paint.setTextSize(getPxBySp(fontSize));
    
  }
  
  /**
   * 根据sp获取px
   * @param numberInSp
   * @return
   */
  private Float getPxBySp(int numberInSp){
    DisplayMetrics metrics = new DisplayMetrics();
    ( (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
   return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
       numberInSp,metrics );
  }
  
  
  
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
    totalHeight = measuredHeight;
    itemHeight = totalHeight/alphabets.size();
    itemWidth =  MeasureSpec.getSize(widthMeasureSpec);
    setMeasuredDimension(itemWidth, measuredHeight);
  }
  
  private int currentPosition  = POSITION_DISABLE ;
  private float tempY = 0 ;
  
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    
    switch (event.getAction()) {
    case MotionEvent.ACTION_DOWN:
      setBackgroundColor(color_bg);
      
      tempY =  event.getY();
     notifyWithPosition( (int) (tempY/itemHeight));
      break;
      
    case MotionEvent.ACTION_MOVE:
      tempY =  event.getY();
      notifyWithPosition((int) (tempY/itemHeight));
      break;
    case MotionEvent.ACTION_UP:
      setBackgroundColor(Color.TRANSPARENT);
      currentPosition = POSITION_DISABLE ;
      break;
    case MotionEvent.ACTION_CANCEL:
      setBackgroundColor(Color.TRANSPARENT);
      currentPosition = POSITION_DISABLE ;
      break;

    default:
      break;
    }
    
    return true;
  }
  
  /**
   * 检测是否越界,不越界则提示.
   * @param position
   */
  private void notifyWithPosition(int position){
    
    if(currentPosition==position||position==POSITION_DISABLE){
      return ;
    }
    
    if(position<0||position>(alphabets.size()-1)){
      return ;
    }
    
    
    currentPosition = position;
    if(sectionIndexerChangedListener!=null)
      sectionIndexerChangedListener.onSectionIndexerChanged(position, alphabets.get(position));
    
  }
  
  
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    for (int i = 0; i < alphabets.size(); i++) { 
        canvas.drawText(alphabets.get(i),itemWidth/2
            ,itemHeight*(1+i), paint);  
    }  
    
  }
  
  /**
   * 设置section的数据
   * @param sections
   */
  public void setSections(String sections[]){
    this.alphabets =  Arrays.asList(sections);
    postInvalidate();
  }
  /**
   * 设置section的数据
   * @param sections
   */
  public void setSections(List<String> sections){
    this.alphabets = sections;
    postInvalidate();
    
  }
  
  /**
   * 设置背景色
   * @param bgColor
   */
  public void setIndexerBgColor(int bgColor){
    this.color_bg = bgColor ;
    initPaint();
    postInvalidate();
    
  }
  
  /**
   * 设置section字体颜色
   */
  public void setSectionColor(int fontColor){
    this.color_section =  fontColor;
    initPaint(); 
    postInvalidate();
    
  }
  
  /**
   * 设置section字体大小
   * @param numInSP sp单位的大小
   */
  public void setSectionFontSize(int numInSP){
    
    this.fontSize = numInSP;
    initPaint();
    postInvalidate();
    
  }
  
  public SectionIndexerChangedListener getSectionIndexerChangedListener() {
    return sectionIndexerChangedListener;
  }

  public void setSectionIndexerChangedListener(
      SectionIndexerChangedListener sectionIndexerChangedListener) {
    this.sectionIndexerChangedListener = sectionIndexerChangedListener;
  }






  /**
   * 当section中的某一项被选中时触发
   */
  public static interface SectionIndexerChangedListener{
    
    /**
     * 当section中的某一项被选中时触发
     * @param position section的位置
     * @param section 标签的内容
     */
    void onSectionIndexerChanged(int position,String section);
    
  }
  
}
