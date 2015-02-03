package com.icode.library.widgets.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.icodelibrary.R;
import com.icode.library.tools.utils.IStateDrawableUtils;
/**
 * 一个简单的GridView的ITEM的实现.
 * ITEM主要包含一个图标,一个文字说明. 图标在上,文字在下
 */
public class ISimpleGridItemAdapter extends BaseAdapter {
  
  private Context mContext;
  private List<ISimpleGridItem> gridItems = new ArrayList<ISimpleGridItemAdapter.ISimpleGridItem>();
  public ISimpleGridItemAdapter(Context context,List<ISimpleGridItem> items ) {
   this.mContext = context ;
   this.gridItems = items;
  }
  
  public static interface ISimpleGridItemClickedListener{
    
    void gridItemClicked(String itemId,String itemName,Object extraInfo);
    
  }
  

  
  public static class ISimpleGridItem{
    
    public String itemId ;
    public String itemName;
    public int iconResourceNormal ;
    public int iconResourcePressed ;
    public Object extraInfo ;
    public int fontColor = -1 ;
    public ColorStateList fontColorStateList = null ;
    public int fontSize = 16 ;
    public ISimpleGridItemClickedListener itemClickedListener;
    
    
    public static ISimpleGridItem getInstance(String name,int iconNormal,int iconPressed){
      ISimpleGridItem gridItem = new ISimpleGridItem();
      gridItem.itemName = name;
      gridItem.iconResourceNormal = iconNormal ;
      gridItem.iconResourcePressed = iconPressed;
      return gridItem;
    }
    
    public static ISimpleGridItem getInstance(String id,String name,int iconNormal,int iconPressed,Object extra){
      ISimpleGridItem gridItem = new ISimpleGridItem();
      gridItem.itemName = name;
      gridItem.iconResourceNormal = iconNormal ;
      gridItem.iconResourcePressed = iconPressed;
      gridItem.extraInfo = extra;
      gridItem.itemId = id ;
      return gridItem;
    }
    
  }
  

  @Override
  public int getCount() {
    return gridItems.size();
  }

  @Override
  public Object getItem(int position) {
    return gridItems.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    
    GridItemHolder itemHolder ;
   final ISimpleGridItem simpleGridItem  = gridItems.get(position);
    
    if(convertView==null){
      itemHolder = new GridItemHolder();
      convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_adapter_griditem, null);
      itemHolder.textView_name = (TextView) convertView.findViewById(R.id.adapter_griditem_name);
      itemHolder.imageView_icon = (ImageView)convertView.findViewById(R.id.adapter_griditem_icon);
      convertView.setTag(itemHolder);
    }else {
      itemHolder = (GridItemHolder) convertView.getTag();
    }
    if(simpleGridItem.fontColor!=-1)
    itemHolder.textView_name.setTextColor(simpleGridItem.fontColor);
    if(simpleGridItem.fontColorStateList!=null)
      itemHolder.textView_name.setTextColor(simpleGridItem.fontColorStateList);
    itemHolder.textView_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, simpleGridItem.fontSize);
    
    itemHolder.imageView_icon.setBackgroundDrawable(
        IStateDrawableUtils.newSelector(mContext, simpleGridItem.iconResourceNormal, simpleGridItem.iconResourcePressed));
    itemHolder.textView_name.setText(simpleGridItem.itemName);
    
    convertView.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        if(simpleGridItem.itemClickedListener!=null)
          simpleGridItem.itemClickedListener.gridItemClicked(
              simpleGridItem.itemId, simpleGridItem.itemName, simpleGridItem.extraInfo);
      }
    });
    
    return convertView;
    
  }
  
  private class GridItemHolder{
    ImageView imageView_icon ;
    TextView textView_name ;
    
  }
  /**
   * 刷新数据
   * @param items
   */
  public void notifyWithData(List<ISimpleGridItem> items){
    this.gridItems.clear();
    this.gridItems.addAll(items);
    notifyDataSetChanged();
  }

}
