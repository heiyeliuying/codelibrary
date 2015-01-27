package com.demo.icodelibrary.demos;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.widgets.adapter.ISimpleExpandableAdapter;
import com.icode.library.widgets.adapter.ISimpleExpandableAdapter.IExpandableInfo;
import com.icode.library.widgets.adapter.ISimpleExpandableAdapter.IExpandableItem;
import com.icode.library.widgets.adapter.ISimpleExpandableAdapter.IExpandableItemClickedListener;
import com.icode.library.widgets.toast.ISimpleToast;

public class ExpandableFragment extends IFragment implements IPageRuler {
  private ExpandableListView expandableListView;
  private ISimpleExpandableAdapter expandableAdapter ;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_expandable_list, null);
    initData();
    initViews();
    return mContentView;
  }
  

  @Override
  public void initViews() {
    expandableListView = (ExpandableListView) mContentView.findViewById(R.id.expandable_listview);
    expandableListView.setGroupIndicator(null);
    expandableListView.setDivider(null);
    IExpandableInfo expandableInfo = IExpandableInfo.getExpandableInfo(mActivity);
    expandableInfo.childIndicator = R.drawable.expandable_indicator;
    expandableInfo.groupTitleColor = Color.parseColor("#ff6666");
    expandableInfo.groupTitleSize = 20;
    expandableInfo.groupIndicator = R.drawable.expandable_group_indicator;
    
    expandableInfo.childTitleColor  = Color.parseColor("#ff9966");
    expandableInfo.childTitleSize = 18 ;
    
    expandableInfo.clickedListener = new IExpandableItemClickedListener() {
      
      @Override
      public void expandableItemClicked(boolean isGroup, int groupPosition, int childPosition,
          IExpandableItem expandableItem) {
        ISimpleToast.showCenterToast(mActivity, "isGroup="+isGroup+" groupPosition="+
          groupPosition+" childPosition="+childPosition );
        
      }
    };
    expandableAdapter= new ISimpleExpandableAdapter(mActivity, expandableInfo,getExpandableItems());
    expandableListView.setAdapter(expandableAdapter);
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
  
 private ArrayList<IExpandableItem> getExpandableItems(){
    
    ArrayList<IExpandableItem>  items = new ArrayList<ISimpleExpandableAdapter.IExpandableItem>();
    
    IExpandableItem item_group ,item_child;
    
    ArrayList<IExpandableItem> items_child  ;
    for (int i = 0; i <getTempSection().length; i++) {
      
      items_child = new ArrayList<ISimpleExpandableAdapter.IExpandableItem>();
      int random = (int) (Math.random()*10);
      for (int j = 0; j <random; j++) {
        item_child = IExpandableItem.getInstance("你好");
        items_child.add(item_child);
        
      }
      item_group = IExpandableItem.getInstance(getTempSection()[i]+"-------group", items_child);
      items.add(item_group);
      
    }
    
    
    
    return items;
  }
 
 private String[] getTempSection (){
   String [] sections = new String []{
      "A","B","C","D","E","F","G","H","I",
       "J","K","L","M","N","O","P","Q","R",
       "S","T","U","V","W","X","Y","Z","#"
     };
   return sections;
 }

}
