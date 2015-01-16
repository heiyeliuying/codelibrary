package com.icode.library.widgets.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.code.icodelibrary.R;
/**
 * 简单的文本展示的Adapter.主要用于ListView的展示
 */
public class ISimpleTextAdapter extends BaseAdapter {
  private Context mContext;
  private List<String> itemNames ;
  
  
  public ISimpleTextAdapter(Context context ,String [] itemNames) {
    this.mContext = context ;
    this.itemNames = Arrays.asList(itemNames);
  }

  public ISimpleTextAdapter(Context context ,List<String> items){
    this.mContext = context ;
    this.itemNames = items;
    if(this.itemNames==null)
      this.itemNames = new ArrayList<String>();
  }
  
  @Override
  public int getCount() {
    return itemNames.size();
  }

  @Override
  public Object getItem(int position) {
    return itemNames.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    ISimpleListHolder listHolder;
    String title = itemNames.get(position);
    if (convertView == null) {
      listHolder = new ISimpleListHolder();
      convertView = (View) LayoutInflater.from(mContext).inflate(R.layout.common_list_item_text,
          null);
      listHolder.textView_name = (TextView) convertView
          .findViewById(R.id.common_list_item_text_info);
      convertView.setTag(listHolder);
    } else {
      listHolder = (ISimpleListHolder) convertView.getTag();
    }

    listHolder.textView_name.setText(title);
    listHolder.textView_name.setBackgroundResource(R.drawable.common_listitem_selector);
    return convertView;
  }
  
  private class ISimpleListHolder {

    TextView textView_name;

  }

}
