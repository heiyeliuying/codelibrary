package com.demo.icodelibrary.demos;

import pagerSlidingTabStrip.PagerSlidingTabStrip;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;

/**
 * 顶部导航栏
 * 
 * @author chenzheng
 * 
 */
public class SlidingTabTripFragment extends IFragment implements IPageRuler {
  private ViewPager mViewPager;
  private PagerSlidingTabStrip mTabStrip;
  private String[] infos = null;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_slidingtabstrip, null);
    initData();
    initViews();
    return mContentView;
  }


  @Override
  public void initViews() {
    mViewPager = (ViewPager) mContentView.findViewById(R.id.sliding_viewpager);
    mTabStrip = (PagerSlidingTabStrip) mContentView.findViewById(R.id.sliding_id);
    mViewPager.setAdapter(new MyPagerAdapter());
    mTabStrip.setViewPager(mViewPager);
  /*  
    //是否全部大写
    mTabStrip.setAllCaps(false);
    //设置分割线颜色
    mTabStrip.setDividerColor(Color.RED);
    //20像素间隔
    mTabStrip.setDividerPadding(20);
    //尺标的颜色
    mTabStrip.setIndicatorColor(Color.BLUE);
    //字体颜色
    mTabStrip.setTextColor(Color.BLACK);
    mTabStrip.setShouldExpand(true);
  */
  }
  @Override
  public void initData() {
    infos = new String[]{
        "新闻",
        "科技",
        "教育",
        "八卦",
        "汽车",
        "民生",
        "黄页"
        
    };
  }

  @Override
  public void saveData() {

  }

  @Override
  public void flushPage() {

  }

  private class MyPagerAdapter extends PagerAdapter{

    @Override
    public int getCount() {
      return infos.length;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
      return arg0 == arg1;
    }
    
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
      return infos[position];
    }

    
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      String title = infos[position];
      TextView textView = new TextView(mActivity);
      textView.setGravity(Gravity.CENTER);
      textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
      textView.setText(title);
      
      container.addView(textView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
      
      return textView;
    }
  }
  
  
  
}
