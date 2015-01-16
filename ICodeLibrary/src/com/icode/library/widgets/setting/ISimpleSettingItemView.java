package com.icode.library.widgets.setting;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.code.icodelibrary.R;
/**
 * 设置页条目组件.从左到右为: 图标 -标题---文本提示--图标(箭头)
 */
public class ISimpleSettingItemView extends RelativeLayout {
  
  private Context mContext ;

  private ImageView imageView_icon,imageView_arrow;
  
  private TextView textView_title,textView_info ;
  
  private SettingItemInfo itemInfo;
  private View contentView ;
  
  
  public ISimpleSettingItemView(Context context) {
    super(context);
    this.mContext = context ;
    initViews();
  }

  public ISimpleSettingItemView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    this.mContext = context ;
    initViews();
  }

  public ISimpleSettingItemView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.mContext = context ;
    initViews();
  }
  
  
  
  
  
  
  private void initViews(){
    
    contentView =  LayoutInflater.from(mContext).inflate(R.layout.common_setting_item, null);
   imageView_icon = (ImageView)contentView.findViewById(R.id.setting_item_icon);
   imageView_arrow = (ImageView)contentView.findViewById(R.id.setting_item_operate);
   
   textView_title = (TextView)contentView.findViewById(R.id.setting_item_title);
   textView_info = (TextView)contentView.findViewById(R.id.setting_item_info);
   
   RelativeLayout.LayoutParams layoutParams =
       new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
   addView(contentView,layoutParams);
  }
  
  private void flushRightNow(){
    
    if(itemInfo.iconRes==SettingItemInfo.RES_DISABLE){
      imageView_icon.setVisibility(View.INVISIBLE);
    }else {
      imageView_icon.setImageResource(itemInfo.iconRes);
    }
    
    if(itemInfo.arrowRes==SettingItemInfo.RES_DISABLE){
      imageView_arrow.setVisibility(View.INVISIBLE);
    }else {
      imageView_arrow.setImageResource(itemInfo.arrowRes);
    }
    
    textView_title.setText(itemInfo.title);
    textView_info.setText(itemInfo.info);
    
      contentView.setBackgroundDrawable(newDrawableSelector(mContext, 
          itemInfo.bgNormal, itemInfo.bgPressed));
   
      if(itemInfo.clickListener!=null)
      contentView.setOnClickListener(itemInfo.clickListener);
    
  }
  
  /**
   * 获取ISimpleSettingItemView 对象.
   * @param context
   * @param itemInfo
   * @return
   */
  public static ISimpleSettingItemView getInstance(Context context,SettingItemInfo itemInfo ){
    ISimpleSettingItemView itemView = new ISimpleSettingItemView(context);
    itemView.setItemInfo(itemInfo);
    return itemView;
  }
  
  
  /**
   * 
   * @param context
   * @param itemInfo ITEM
   * @param isShowArrow 是否展示Arrow
   * @return
   */
  public static ISimpleSettingItemView getInstance(Context context,SettingItemInfo itemInfo,boolean isShowArrow ){
    ISimpleSettingItemView itemView = new ISimpleSettingItemView(context);
    if(!isShowArrow)
    itemInfo.setArrowRes(SettingItemInfo.RES_DISABLE);
    itemView.setItemInfo(itemInfo);
    return itemView;
  }
  
  private  StateListDrawable newDrawableSelector(Context context,int normal,int pressed) {
    StateListDrawable bg = new StateListDrawable();
    Drawable pressedDrawable = context.getResources().getDrawable(
      pressed);
    Drawable normalDrawable = context.getResources().getDrawable(
        normal);
    bg.addState(new int[] { android.R.attr.state_pressed }, pressedDrawable);
    bg.addState(new int[] {}, normalDrawable);
    return bg;

  }
  
  public SettingItemInfo getItemInfo() {
    return itemInfo;
  }

  public void setItemInfo(SettingItemInfo itemInfo) {
    this.itemInfo = itemInfo;
    flushRightNow();
  }





  /**
   * 每一个设置项对应着一个SettingItemInfo,其主要负责统管样式和内容
   */
  public static class SettingItemInfo{
    
    private int iconRes = RES_DISABLE ;
    private int arrowRes = R.drawable.common_setting_item_arrow ;
    private String title;
    private String info ;
    private ColorStateList titleColor ;
    private ColorStateList infoColor ;
    private int bgNormal = R.drawable.common_setting_item_doubleline_normal ;
    private int bgPressed =R.drawable.common_setting_item_doubleline_pressed ;
    private View.OnClickListener clickListener;
    
    public static final int RES_DISABLE = -1 ;
    
    /**
     * 获取默认的双线的SettingItemInfo,双线背景包含底部和顶部分割线,适合单独使用
     * @param title 标题
     * @param info  辅助信息
     * @return 
     */
    public static SettingItemInfo getDoubleLineItem(String title,String info){
      SettingItemInfo settingItemInfo = new SettingItemInfo();
      settingItemInfo.setTitle(title);
      settingItemInfo.setInfo(info);
      return settingItemInfo;
    }
    
    /**
     * 获取默认的单线的SettingItemInfo.单线背景只有底部的分割线,适合多个ITEM一起时布局
     * 
     * @param title
     *          标题
     * @param info
     *          辅助信息
     * @return
     */
    public static SettingItemInfo getSingleLineItem(String title,String info){
      SettingItemInfo settingItemInfo = new SettingItemInfo();
      settingItemInfo.setBgNormal(R.drawable.common_setting_item_singleline_normal);
      settingItemInfo.setBgPressed(R.drawable.common_setting_item_singleline_pressed);
      settingItemInfo.setTitle(title);
      settingItemInfo.setInfo(info);
      return settingItemInfo;
    }
    
    
    
    
    public int getIconRes() {
      return iconRes;
    }
    public void setIconRes(int iconRes) {
      this.iconRes = iconRes;
    }
    public int getArrowRes() {
      return arrowRes;
    }
    public void setArrowRes(int arrowRes) {
      this.arrowRes = arrowRes;
    }
    public String getTitle() {
      return title;
    }
    public void setTitle(String title) {
      this.title = title;
    }
    public String getInfo() {
      return info;
    }
    public void setInfo(String info) {
      this.info = info;
    }
    public ColorStateList getTitleColor() {
      return titleColor;
    }
    public void setTitleColor(ColorStateList titleColor) {
      this.titleColor = titleColor;
    }
    public ColorStateList getInfoColor() {
      return infoColor;
    }
    public void setInfoColor(ColorStateList infoColor) {
      this.infoColor = infoColor;
    }
    public int getBgNormal() {
      return bgNormal;
    }
    public void setBgNormal(int bgNormal) {
      this.bgNormal = bgNormal;
    }
    public int getBgPressed() {
      return bgPressed;
    }
    public void setBgPressed(int bgPressed) {
      this.bgPressed = bgPressed;
    }

    public View.OnClickListener getClickListener() {
      return clickListener;
    }

    public void setClickListener(View.OnClickListener clickListener) {
      this.clickListener = clickListener;
    }
    
    
    
    
    
  }
  
}
