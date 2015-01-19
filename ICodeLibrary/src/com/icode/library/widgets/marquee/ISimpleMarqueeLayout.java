package com.icode.library.widgets.marquee;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.code.icodelibrary.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.CirclePageIndicator;
/**
 * 一个简单的精彩推荐的实现.自动的轮换图片
 *
 */
public class ISimpleMarqueeLayout extends RelativeLayout {

  private View mContainerView;
  private AutoScrollViewPager scrollViewPager;
  private ISimlpeMarqueeViewAdapter marqueeViewAdapter;
  private List<IMarqueeItem> marqueeItems = new ArrayList<ISimpleMarqueeLayout.IMarqueeItem>();
  private IMarqueeInfo marqueeInfo;
  private CirclePageIndicator indicator ;
  private MarqueeItemSelectedListener listener;

  public ISimpleMarqueeLayout(Context context) {
    super(context);
    initViews();
  }

  public ISimpleMarqueeLayout(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initViews();
  }

  public ISimpleMarqueeLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    initViews();
  }

  /**
   * 设置推荐内容的展示属性.
   */
  public static class IMarqueeInfo {

    /**
     * 加载时的图片
     */
    Drawable loadingDrawable = null;
    /**
     * 加载失败时的图片
     */
    Drawable FailedDrawable = null;
    /**
     * 图片缩放模式
     */
    ScaleType scaleType = ScaleType.FIT_XY;
    /**
     * 图片的目标宽度
     */
    int destWidth = 0;
    /**
     * 宽度和高度的比例
     */
    int widthDivHeight = 2;

    public static IMarqueeInfo getInstance() {
      IMarqueeInfo marqueeInfo = new IMarqueeInfo();
      return marqueeInfo;
    }

    /**
     * 
     * @param destWidth 目标宽度
     * @param widthDivHeight 宽高比
     * @return
     */
    public static IMarqueeInfo getInstance(int destWidth, int widthDivHeight ){
      IMarqueeInfo marqueeInfo = getInstance();
      marqueeInfo.destWidth = destWidth;
      marqueeInfo.widthDivHeight = widthDivHeight;
      return marqueeInfo ;
      
    }
    
  }

  //获取屏幕宽度
  private int getScreenWidth() {
    WindowManager windowManager = (WindowManager) getContext().getSystemService(
        Context.WINDOW_SERVICE);
    return windowManager.getDefaultDisplay().getWidth();

  }

  /**
   * 滚动推荐内容实体.
   * 图片加载使用的为ImageUniversalLoader
   */
  public static class IMarqueeItem {
    /**
     * 图片路径.这里可以是本地图片也可以是网络图片.
     * 这类图片的加载使用的框架为ImageUniversalLoader,
     * 路径规则按照其格式即可
     */
    String uri;
    /**
     * 描述信息
     */
    String description = "";
    /**
     * id
     */
    String id = "";

    public String getUri() {
      return uri;
    }

    public void setUri(String uri) {
      this.uri = uri;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public IMarqueeItem(String uri, String description, String id) {
      super();
      this.uri = uri;
      this.description = description;
      this.id = id;
    }

  }

  private void initViews() {
    if (marqueeInfo == null)
      marqueeInfo = IMarqueeInfo.getInstance();
    mContainerView = LayoutInflater.from(getContext()).inflate(R.layout.common_marquee, null);
    scrollViewPager = (AutoScrollViewPager) mContainerView.findViewById(R.id.marquee_viewpager);
    marqueeViewAdapter = new ISimlpeMarqueeViewAdapter();
    scrollViewPager.setAdapter(marqueeViewAdapter);
    scrollViewPager.startAutoScroll(1000 * 1);
    
    
    addView(mContainerView);
    setLayout();

  }
  
  //viewpager需要设置宽高,否则默认占满全屏.
  private void setLayout(){
    if(marqueeInfo.destWidth==0){
      marqueeInfo.destWidth = getScreenWidth();
    }
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
        marqueeInfo.destWidth, marqueeInfo.destWidth / marqueeInfo.widthDivHeight);
    scrollViewPager.setLayoutParams(layoutParams);
    indicator = (CirclePageIndicator) mContainerView.findViewById(R.id.marquee_viewpager_indicator);
   
    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams( marqueeInfo.destWidth, marqueeInfo.destWidth / marqueeInfo.widthDivHeight);
    setLayoutParams(layoutParams2);
    
    indicator.setViewPager(scrollViewPager);
    final float density = getResources().getDisplayMetrics().density;
    indicator.setBackgroundColor(Color.TRANSPARENT);
    indicator.setRadius(4 * density);
    indicator.setPageColor(Color.WHITE);
    indicator.setFillColor(Color.parseColor("#c3c3c3"));
    indicator.setStrokeColor(Color.GRAY);
    indicator.setStrokeWidth(1 * density);
    
    
    
  }

  private class ISimlpeMarqueeViewAdapter extends PagerAdapter {

    @Override
    public int getCount() {
      return marqueeItems.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
      return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container,final int position) {
     final IMarqueeItem marqueeItem = marqueeItems.get(position);
      ImageView imageView = new ImageView(getContext());
      imageView.setScaleType(marqueeInfo.scaleType);
      imageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(marqueeInfo.destWidth,
          marqueeInfo.destWidth / marqueeInfo.widthDivHeight));
      ImageLoader.getInstance().displayImage(marqueeItem.getUri(), imageView, getImageOptions());
      container.addView(imageView, new LayoutParams(LayoutParams.WRAP_CONTENT,
          LayoutParams.WRAP_CONTENT));
      imageView.setOnClickListener(new OnClickListener() {
        
        @Override
        public void onClick(View v) {
          if(listener!=null)
            listener.onMarqueeItemSelected(marqueeItem, position);
        }
      });
      return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }

  }

  //获取图片展示时的options设置
  private DisplayImageOptions getImageOptions() {
    DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
    if (marqueeInfo.loadingDrawable != null) {
      builder.showImageOnLoading(marqueeInfo.loadingDrawable);
    }
    if (marqueeInfo.FailedDrawable != null) {
      builder.showImageOnFail(marqueeInfo.loadingDrawable);
    }
    return builder.build();

  }

  /**
   * 刷新页面
   * @param infos 推荐内容
   */
  public void flushWithData(List<IMarqueeItem> infos) {
    this.marqueeItems = infos;
    setLayout();
    marqueeViewAdapter.notifyDataSetChanged();

  }
  /**
   * 刷新页面
   * @param infos 推荐内容
   * @param marqueeInfo 展示样式
   */
  public void flushWithData(List<IMarqueeItem> infos,IMarqueeInfo marqueeInfo){
    this.marqueeInfo = marqueeInfo;
    flushWithData(infos);
    
  }
  
  /**
   * 设置viewpager的changeListener
   * @param changeListener
   */
  public void setChangeListener(OnPageChangeListener changeListener){
    scrollViewPager.setOnPageChangeListener(changeListener);
    
  }
  
  public void setOnItemSelectedListener(MarqueeItemSelectedListener listener){
    this.listener = listener;
    
    
  }

  public static interface MarqueeItemSelectedListener{
    
   void onMarqueeItemSelected(IMarqueeItem marqueeItem,int position);
    
  }
  
}
