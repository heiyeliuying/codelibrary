package com.demo.icodelibrary.demos;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.tools.utils.IToastUtils;
import com.icode.library.widgets.marquee.ISimpleMarqueeLayout;
import com.icode.library.widgets.marquee.ISimpleMarqueeLayout.IMarqueeInfo;
import com.icode.library.widgets.marquee.ISimpleMarqueeLayout.IMarqueeItem;
import com.icode.library.widgets.marquee.ISimpleMarqueeLayout.MarqueeItemSelectedListener;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class RecommandFragment extends IFragment implements IPageRuler {
  
  private  ISimpleMarqueeLayout marqueeLayout ;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_recommand, null);
    initData();
    initViews();
    return mContentView;
  }

  @Override
  public void initViews() {
     marqueeLayout = (ISimpleMarqueeLayout) mContentView.findViewById(R.id.marquee_id);
    
    List<ISimpleMarqueeLayout.IMarqueeItem> iMarqueeItems = new ArrayList<ISimpleMarqueeLayout.IMarqueeItem>();
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01901.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01902.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01903.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01904.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01905.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01906.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01907.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01908.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01911.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01910.jpg", "", ""));
    
    IMarqueeInfo marqueeInfo = 
        IMarqueeInfo.getInstance(ISimpleMarqueeLayout.MarqueeIndicatorPositionStyle.position_center,
            ISimpleMarqueeLayout.MarqueeIndicatorStyle.style_line);
    marqueeLayout.flushWithData(iMarqueeItems,marqueeInfo);
    
    
    resetIndicatorPaintStyle();
  
    
    marqueeLayout.setOnItemSelectedListener(new MarqueeItemSelectedListener() {
      
      @Override
      public void onMarqueeItemSelected(IMarqueeItem marqueeItem, int position) {
        IToastUtils.toast(mActivity, marqueeItem.getUri());
      }
    });
    
  }
  
  private void resetIndicatorPaintStyle(){
    
    //可以获取到indicator,然后对其样式进行重新设置
   PageIndicator indicator =  marqueeLayout.getIndicator();
   
   if(indicator instanceof CirclePageIndicator){
     float density = getResources().getDisplayMetrics().density;
     CirclePageIndicator circlePageIndicator = (CirclePageIndicator)indicator;
     circlePageIndicator.setBackgroundColor(Color.TRANSPARENT);
     circlePageIndicator.setRadius(4 * density);
     circlePageIndicator.setPageColor(Color.GREEN);
     circlePageIndicator.setFillColor(Color.parseColor("#c3c3c3"));
     circlePageIndicator.setStrokeColor(Color.YELLOW);
     circlePageIndicator.setStrokeWidth(1 * density);
     
   }
    
   if(indicator instanceof LinePageIndicator){
     LinePageIndicator linePageIndicator = (LinePageIndicator)indicator;
     final float density = getResources().getDisplayMetrics().density;
     linePageIndicator.setSelectedColor(Color.parseColor("#ff6666"));
     linePageIndicator.setUnselectedColor(Color.parseColor("#663366"));
     linePageIndicator.setStrokeWidth(4 * density);
     linePageIndicator.setLineWidth(20 * density);
   
     
   }
    
  }

  @Override
  public void initData() {
    

  }

  @Override
  public void saveData() {
    

  }

  @Override
  public void flushPage() {
    

  }

}
