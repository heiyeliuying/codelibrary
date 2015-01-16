package com.demo.icodelibrary.demos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.tools.utils.IToastUtils;
import com.icode.library.widgets.setting.ISimpleSettingItemView;
import com.icode.library.widgets.setting.ISimpleSettingItemView.SettingItemInfo;
/**
 * 设置ITEM
 * @author chenzheng
 *
 */
public class SettingItemFragment extends IFragment implements IPageRuler {
  
  private ISimpleSettingItemView itemView ;
  private LinearLayout layout_parent ;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
   mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_settingitem, null);
   initData();
   initViews();
    return mContentView;
  }

  @Override
  public void initViews() {
    ISimpleSettingItemView.SettingItemInfo itemInfo =null;
    ISimpleSettingItemView settingItemView ;
    View.OnClickListener clickListener = new OnClickListener() {
      
      @Override
      public void onClick(View arg0) {
        IToastUtils.toast(mActivity, "点击成功");
      }
    };
    
    
    
    itemView = (ISimpleSettingItemView) mContentView.findViewById(R.id.setting_item_view);
        itemInfo =   SettingItemInfo.getDoubleLineItem("我的资料", "点击进入详情");
        itemInfo.setClickListener(clickListener);
    itemView.setItemInfo(itemInfo);
    
    
    layout_parent = (LinearLayout) mContentView.findViewById(R.id.setting_item_parent);
    itemInfo = SettingItemInfo.getDoubleLineItem("发表的贴", "5");
    itemInfo.setClickListener(clickListener);
    itemInfo.setIconRes(R.drawable.setting_item_icon1);
    settingItemView  = ISimpleSettingItemView.getInstance(mActivity, itemInfo, true);
    layout_parent.addView(settingItemView);
    
    itemInfo = SettingItemInfo.getSingleLineItem("回复的贴", "15");
    itemInfo.setClickListener(clickListener);
    itemInfo.setIconRes(R.drawable.setting_item_icon2);
    settingItemView  = ISimpleSettingItemView.getInstance(mActivity, itemInfo, true);
    layout_parent.addView(settingItemView);
    
    itemInfo = SettingItemInfo.getSingleLineItem("消息提醒", "点击查看详情");
    itemInfo.setClickListener(clickListener);
    settingItemView  = ISimpleSettingItemView.getInstance(mActivity, itemInfo, true);
    layout_parent.addView(settingItemView);
    
    itemInfo = SettingItemInfo.getSingleLineItem("应用设置", "");
    itemInfo.setClickListener(clickListener);
    settingItemView  = ISimpleSettingItemView.getInstance(mActivity, itemInfo, false);
    layout_parent.addView(settingItemView);
    
    
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
