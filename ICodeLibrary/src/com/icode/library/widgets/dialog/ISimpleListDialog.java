package com.icode.library.widgets.dialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.code.icodelibrary.R;
import com.icode.library.tools.utils.ILogUtils;

/**
 * 列表类型展示的Dialog.
 * 弹出式显示选择列表,该列表的内容为一行文本内容.
 * 
 * @param <T>
 */
public class ISimpleListDialog<T> extends Dialog {

  private Context mContext;
  private TextView textView_title;
  //分割线
  private View view_divider;
  private ListView mListView;
  //标题颜色
  private int colorTitle;
  //顶部分割线颜色
  private int colorDivider;
  //ListView Item里的文本颜色
  private int colorItem;
  //对齐方式
  private int gravity;
  //标题
  private String title ;
  private List<DialogListItem<T>> listItems;
  private ISimpleListAdapter simpleListAdapter;
  private DialogListItemClickListener mClickListener;

  public ISimpleListDialog(Context context, DialogInfo dialogInfo) {
    super(context, R.style.CommonAlertDialog);
    this.mContext = context;
    this.colorTitle = dialogInfo.getTitleColor();
    this.colorDivider = dialogInfo.getDividerColor();
    this.colorItem = dialogInfo.getItemColor();
    this.listItems = dialogInfo.getListItems();
    this.title = dialogInfo.getTitle();
    this.mClickListener = dialogInfo.getClickListener();
    this.gravity = dialogInfo.getGravity();
    initViews();

  }

  /**
   * 获取dialog
   * @param context
   * @param dialogInfo
   * @return
   */
  public  static <T> ISimpleListDialog<T> getInstance(Context context,DialogInfo<T> dialogInfo){
    ISimpleListDialog<T> dialog = new ISimpleListDialog<T>(context, dialogInfo);
    return dialog;
  }
  
  
  /**
   * 当列表中的某一个ITEM被点击时触发
   */
  public interface DialogListItemClickListener {
    /**
     * 当列表中的某一个ITEM被点击时触发
     * 
     * @param listItem
     */
    public <T> void onDialogListItemClicked(DialogListItem<T> listItem);

  }

  private void initViews() {
    View view = LayoutInflater.from(mContext).inflate(R.layout.common_dialog_list, null);
    LayoutParams params= new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    setContentView(view, params);
    textView_title = (TextView) findViewById(R.id.dialog_list_title);
    textView_title.setText(title);
    textView_title.setTextColor(colorTitle);
    textView_title.setGravity(gravity);
    
    view_divider = (View) findViewById(R.id.dialog_list_divider);
    view_divider.setBackgroundColor(colorDivider);
    
    mListView = (ListView) findViewById(R.id.dialog_list_listview);
    mListView.setSelector(android.R.color.transparent);
    mListView.setDivider(mContext.getResources().getDrawable(R.drawable.common_list_divider));
    simpleListAdapter = new ISimpleListAdapter();
    mListView.setAdapter(simpleListAdapter);
    
    
  
    
    mListView.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dismiss();
        if (mClickListener != null) {
          mClickListener.onDialogListItemClicked(listItems.get(position));

        }

      }
    });

    
    adjustListViewHeight();
    
  }
  
  
  /**
   * 自动计算listview的高度,动态匹配高度
   */
  private void adjustListViewHeight(){
    
   int count =  simpleListAdapter.getCount();
   
 WindowManager windowManager =   (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
 int screenWidth  = windowManager.getDefaultDisplay().getWidth();
 
   
  int  totalHeight = 0;
  View itemView;
    for (int i = 0; i < count; i++) {
      itemView = simpleListAdapter.getView(i, null, null);
      itemView.measure(0, 0);
      ILogUtils.logError("ITEM高度:"+itemView.getMeasuredHeight()+" screenWidth="+screenWidth);
     totalHeight+= itemView.getMeasuredHeight();
     
    }
    
    
    
    float factor = 0.8f;
    
   int orientation =  mContext.getResources().getConfiguration().orientation;
   switch (orientation) {
  case Configuration.ORIENTATION_LANDSCAPE:
    factor = 0.4f;
    break;
  case Configuration.ORIENTATION_PORTRAIT:
    factor = 0.8f;
    break;

  default:
    break;
  }
   
    
    if(totalHeight>screenWidth*factor){
      totalHeight = (int) (screenWidth*factor);
    }
    mListView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
        totalHeight));
    
  }

  /*******************************************************************/
  private class ISimpleListAdapter extends BaseAdapter {

    @Override
    public int getCount() {
      return listItems.size();
    }

    @Override
    public Object getItem(int position) {
      return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      ISimpleListHolder listHolder;
      DialogListItem<T> listItem = listItems.get(position);

      if (convertView == null) {
        listHolder = new ISimpleListHolder();
         convertView = (View) LayoutInflater.from(mContext).inflate(R.layout.common_list_item_text, null);
       listHolder.textView_name = (TextView) convertView.findViewById(R.id.common_list_item_text_info);
        convertView.setTag(listHolder);
      } else {
        listHolder = (ISimpleListHolder) convertView.getTag();
      }

      listHolder.textView_name.setText(listItem.getItemName());
      listHolder.textView_name.setTextColor(colorItem);
      listHolder.textView_name.setGravity(gravity);
      listHolder.textView_name.setBackgroundResource(R.drawable.common_listitem_selector);
      return convertView;
    }

  }

  private class ISimpleListHolder {

    TextView textView_name;

  }

  /**
   * 描述dialog展现方式的实体,可以设置颜色和数据源等信息
   * 
   * @param <T>
   */
  public static class DialogInfo<T> {

    //标题
    private String title = "温馨提示";
    //标题颜色
    private int titleColor;
    //选项颜色
    private int itemColor;
    //顶部分割线颜色
    private int dividerColor;
    //对齐方式
    private int gravity = Gravity.LEFT|Gravity.CENTER_VERTICAL;
    private ArrayList<DialogListItem<T>> listItems;
    private DialogListItemClickListener clickListener;
    
    private static final int defaultColorDivider = Color.parseColor("#cbccd0");

    public DialogInfo(String title, int titleColor, int itemColor, int dividerColor,
        ArrayList<DialogListItem<T>> listItems, DialogListItemClickListener clickListener) {
      super();
      this.title = title;
      this.titleColor = titleColor;
      this.itemColor = itemColor;
      this.listItems = listItems;
      this.dividerColor = dividerColor;
      this.clickListener = clickListener;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public int getTitleColor() {
      return titleColor;
    }

    public void setTitleColor(int titleColor) {
      this.titleColor = titleColor;
    }

    public int getItemColor() {
      return itemColor;
    }

    public void setItemColor(int itemColor) {
      this.itemColor = itemColor;
    }

    public ArrayList<DialogListItem<T>> getListItems() {
      return listItems;
    }

    public void setListItems(ArrayList<DialogListItem<T>> listItems) {
      this.listItems = listItems;
    }

    public int getDividerColor() {
      return dividerColor;
    }

    public void setDividerColor(int dividerColor) {
      this.dividerColor = dividerColor;
    }

    public DialogListItemClickListener getClickListener() {
      return clickListener;
    }

    public void setClickListener(DialogListItemClickListener clickListener) {
      this.clickListener = clickListener;
    }

    
    
    
    public int getGravity() {
      return gravity;
    }

    public void setGravity(int gravity) {
      this.gravity = gravity;
    }

    /**
     * 获取默认的DialogInfo对象
     * 
     * @param context
     * @return
     */
    public static DialogInfo getInstance(Context context) {

      Resources resources = context.getResources();
      int color = resources.getColor(R.color.common_text_primary);
      DialogInfo dialogInfo = new DialogInfo("温馨提示", color, color, defaultColorDivider,
          new ArrayList<ISimpleListDialog.DialogListItem>(), null);
      return dialogInfo;
    }

    public static DialogInfo getInstance(Context context, DialogListItemClickListener clickListener) {

      Resources resources = context.getResources();
      int color = resources.getColor(R.color.common_text_primary);
      DialogInfo dialogInfo = new DialogInfo("温馨提示", color, color, defaultColorDivider,
          new ArrayList<ISimpleListDialog.DialogListItem>(), clickListener);
      return dialogInfo;
    }

    /**
     * 根据指定的文本数组获取DialogInfo
     * 
     * @param context
     * @param items
     *          需要展示的数组ITEM
     * @return
     */
    public static DialogInfo<String> getInstance(Context context, String[] items) {

      Resources resources = context.getResources();
      int color = resources.getColor(R.color.common_text_primary);

      ArrayList<DialogListItem<String>> dialogListItems = new ArrayList<ISimpleListDialog.DialogListItem<String>>();

      DialogListItem<String> listItem;
      for (int i = 0; i < items.length; i++) {
        listItem = new DialogListItem<String>();
        listItem.extraInfo = items[i];
        listItem.itemName = items[i];
        listItem.position = i;
        dialogListItems.add(listItem);

      }

      DialogInfo<String> dialogInfo = new DialogInfo<String>("温馨提示", color, color, defaultColorDivider,
          dialogListItems, null);
      return dialogInfo;
    }

    public static DialogInfo<String> getInstance(Context context, String[] items,
        DialogListItemClickListener clickListener) {

      Resources resources = context.getResources();
      int color = resources.getColor(R.color.common_text_primary);

      ArrayList<DialogListItem<String>> dialogListItems = new ArrayList<ISimpleListDialog.DialogListItem<String>>();

      DialogListItem<String> listItem;
      for (int i = 0; i < items.length; i++) {
        listItem = new DialogListItem<String>();
        listItem.extraInfo = items[i];
        listItem.itemName = items[i];
        listItem.position = i;
        dialogListItems.add(listItem);

      }

      DialogInfo<String> dialogInfo = new DialogInfo<String>("温馨提示", color, color, defaultColorDivider,
          dialogListItems, clickListener);
      return dialogInfo;
    }

  }

  /**
   * 每一个ITEM所对应的实体
   * 
   * @param <T>
   */
  public static class DialogListItem<T> {
    private String itemName;
    private int position;
    private T extraInfo;

    public DialogListItem(String itemName, int position, T extraInfo) {
      super();
      this.itemName = itemName;
      this.position = position;
      this.extraInfo = extraInfo;
    }

    public DialogListItem() {
      super();
    }

    public String getItemName() {
      return itemName;
    }

    public void setItemName(String itemName) {
      this.itemName = itemName;
    }

    public int getPosition() {
      return position;
    }

    public void setPosition(int position) {
      this.position = position;
    }

    public T getExtraInfo() {
      return extraInfo;
    }

    public void setExtraInfo(T extraInfo) {
      this.extraInfo = extraInfo;
    }

  }

}
