package com.icode.library.widgets.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.icode.library.widgets.adapter.ISimpleGridItemAdapter.ISimpleGridItem;

/**
 * pageAdapter的简单实践.
 * 主要实现内部为ISimpleGridItemAdapter的adapter.
 */
public class ISimplePagerGridAdapter extends PagerAdapter {
  
  private List<ISimpleGridItem> gridItems =  new ArrayList<ISimpleGridItemAdapter.ISimpleGridItem>();
  private Context mContext;
  
  private int colNumber = 1 ,rowNumber = 1;
  
  
  
  public ISimplePagerGridAdapter(Context context,
      List<ISimpleGridItem> gridItems,int colNumber ,int rowNumber) {
    this.mContext = context ;
    this.gridItems = gridItems;
    this.colNumber = colNumber;
    this.rowNumber = rowNumber;
  }
  

  @Override
  public int getCount() {
    int  count = gridItems.size()/(colNumber*rowNumber); ;
   if(gridItems.size()%(colNumber*rowNumber)==0){
    
   }else {
    count+=1 ;
  }
    return count;
  }

  @Override
  public boolean isViewFromObject(View arg0, Object arg1) {
    return arg0==arg1;
  }
  
  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    LinearLayout layout = new LinearLayout(mContext);
    layout.setGravity(Gravity.CENTER);
    GridView gridView = new GridView(mContext);
    gridView.setGravity(Gravity.CENTER);
    gridView.setNumColumns(colNumber);
    List<ISimpleGridItem> items = null;
    int start = position*(colNumber*rowNumber);
    int end = start+(colNumber*rowNumber);
    end = end>=gridItems.size()?(gridItems.size()-1):end ;
    items = gridItems.subList(start, end);
    gridView.setSelector(android.R.color.transparent);
    ISimpleGridItemAdapter gridItemAdapter = new ISimpleGridItemAdapter(mContext, items);
    gridView.setAdapter(gridItemAdapter);
    
    layout.addView(gridView);
    container.addView(layout);
    return layout;
  }
  
}
