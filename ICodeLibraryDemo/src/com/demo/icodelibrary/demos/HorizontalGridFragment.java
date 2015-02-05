package com.demo.icodelibrary.demos;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.tools.utils.IColorStateUtils;
import com.icode.library.tools.utils.IDestinyUtils;
import com.icode.library.widgets.adapter.ISimpleGridItemAdapter;
import com.icode.library.widgets.adapter.ISimpleGridItemAdapter.ISimpleGridItem;
import com.icode.library.widgets.adapter.ISimplePagerGridAdapter;
import com.icode.library.widgets.toast.ISimpleToast;
import com.viewpagerindicator.CirclePageIndicator;

public class HorizontalGridFragment extends IFragment implements IPageRuler {
  
  private ViewPager gridViewPager;
  private List<ISimpleGridItem> gridItems ;
  private CirclePageIndicator circlePageIndicator ;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_horizontal_gridview
        , null);
    initData();
    initViews();
    return mContentView;
  }
  

  @Override
  public void initViews() {
    
   gridViewPager = (ViewPager) mContentView.findViewById(R.id.horizontal_grid_viewpager);
   ISimplePagerGridAdapter gridAdapter = new ISimplePagerGridAdapter(mActivity, gridItems,
       5, 2);
   gridViewPager.setAdapter(gridAdapter);
   
   //底部导航圆圈
   circlePageIndicator = (CirclePageIndicator) mContentView.findViewById(R.id.horizontal_grid_indicator);
   int padding = (int)IDestinyUtils.dp2px(mActivity, 10);
   circlePageIndicator.setPadding(padding, padding, padding, padding);
   final float density = getResources().getDisplayMetrics().density;
   circlePageIndicator.setBackgroundColor(Color.TRANSPARENT);
   circlePageIndicator.setRadius(4 * density);
   circlePageIndicator.setPageColor(Color.WHITE);
   circlePageIndicator.setFillColor(Color.parseColor("#c3c3c3"));
   circlePageIndicator.setStrokeColor(Color.GRAY);
   circlePageIndicator.setStrokeWidth(1 * density);
   circlePageIndicator.setViewPager(gridViewPager);
   
  }

  
  private class ILItemClickedListener implements ISimpleGridItemAdapter.ISimpleGridItemClickedListener{

    @Override
    public void gridItemClicked(String itemId, String itemName, Object extraInfo) {
      ISimpleToast.showCenterToast(mActivity, "itemId="+itemId+" itemName="+itemName);
    }
    
    
  }
  
  @Override
  public void initData() {
    gridItems = new ArrayList<ISimpleGridItemAdapter.ISimpleGridItem>();
    ILItemClickedListener clickedListener = new ILItemClickedListener();
    ISimpleGridItem gridItem ;
    
    
    gridItem = ISimpleGridItem.getInstance("美食", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.fontColorStateList = IColorStateUtils.newSelector(mActivity, Color.parseColor("#ff6666"),
        Color.parseColor("#993399"));
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    
    gridItem = ISimpleGridItem.getInstance("咖啡", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    gridItem = ISimpleGridItem.getInstance("火锅", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    
    gridItem = ISimpleGridItem.getInstance("电影", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    
    gridItem = ISimpleGridItem.getInstance("购物", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    
    gridItem = ISimpleGridItem.getInstance("奢侈品", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    
    gridItem = ISimpleGridItem.getInstance("团购", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    
    gridItem = ISimpleGridItem.getInstance("女人", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    
    gridItem = ISimpleGridItem.getInstance("亲子", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    
    gridItem = ISimpleGridItem.getInstance("足疗", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    
    gridItem = ISimpleGridItem.getInstance("美食", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    
    gridItem = ISimpleGridItem.getInstance("美食", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    
    gridItem = ISimpleGridItem.getInstance("美食", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    
    
    gridItem = ISimpleGridItem.getInstance("美食", R.drawable.viewpager_grid_item_1_normal,R.drawable.viewpager_grid_item_1_pressed);
    gridItem.itemClickedListener = clickedListener ;
    gridItems.add(gridItem);
    

  }

  @Override
  public void saveData() {
   

  }

  @Override
  public void flushPage() {
   

  }

}
