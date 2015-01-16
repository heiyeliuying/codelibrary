package com.icode.library.moudle.introduce;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.code.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.tools.utils.IScreenUtils;
/**
 * 多张图片滑动展示APP内容,最终进入加载界面
 *
 */
public class ISimpleIntroduceFragment extends IFragment implements IPageRuler {

  private View mContainView;
  // 策划栏Item
  private ArrayList<MyPagerItem> pagerItems;
  private int position = 0 ;
  private boolean toSkip =false;
  private float startX= 0 ;
  private ViewPager viewPager;
  private MyPagerAdapter pagerAdapter;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContainView = LayoutInflater.from(mActivity).inflate(R.layout.introduce_main, null);
    initData();
    initViews();
    return mContainView;
  }
  
  @Override
  public void initViews() {
     viewPager = (ViewPager) mContainView
        .findViewById(R.id.introduce_viewPager);
     pagerAdapter = new MyPagerAdapter();
    viewPager.setAdapter(pagerAdapter);
    viewPager.setOnPageChangeListener(new OnPageChangeListener() {

      @Override
      public void onPageSelected(int arg0) {

      }

      @Override
      public void onPageScrolled(int arg0, float arg1, int arg2) {
        position = arg0;
        
      }

      @Override
      public void onPageScrollStateChanged(int arg0) {
      }
    });
    viewPager.setOnTouchListener(new OnTouchListener() {
      
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if(position!=(pagerItems.size()-1)){
          toSkip = false;
          return false;
        }
        
        switch (event.getAction()) {
        
        case MotionEvent.ACTION_DOWN:
          //Log.e("down", event.getRawX() + "");
          startX = event.getRawX();
          break;
        case MotionEvent.ACTION_UP:
          //Log.e("UP", event.getRawX() + " startX="+startX);
          float dx = startX-event.getRawX();
          if(dx>IScreenUtils.getScreenWidth(mActivity)/5){
            skipToInit();
          }
          
          break;

        default:
          break;
        }
        return false;
      }
    });
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
  
 
  
  

  /*
   * 策划项目适配器
   */
  private class MyPagerAdapter extends PagerAdapter {

    @Override
    public int getCount() {
      return pagerItems.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
      return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      ImageView imageView = new ImageView(mActivity);

      imageView
          .setBackgroundResource(pagerItems.get(position).drawableId);
      container.addView(imageView, new LayoutParams(
          LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
      return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      object = null;
    }
  }

  private class MyPagerItem {
    int drawableId;

    public MyPagerItem(int drawableId) {
      this.drawableId = drawableId;
    }

  }

  
  /**
   * 跳转到加载页面
   */
  protected void skipToInit(){
    
    
  }
  
  /**
   * 调用此方法实现刷新页面
   * @param imageRes
   */
  public void flushData(int [] imageRes){
    
    pagerItems = new ArrayList<ISimpleIntroduceFragment.MyPagerItem>();
    for (int i = 0; i < imageRes.length; i++) {
      pagerItems.add(new MyPagerItem(imageRes[i]));
    }
    
  }
  
}
