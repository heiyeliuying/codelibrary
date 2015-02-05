package com.demo.icodelibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.demo.icodelibrary.demos.DemoDetailActivity;
import com.icode.library.common.IActivity;
import com.icode.library.common.IPageRuler;
import com.icode.library.widgets.adapter.ISimpleTextAdapter;
/**
 * 
 * @author chenzheng
 *
 */
public class DemoActivity extends IActivity  implements IPageRuler {
  
  private ListView mListView ;
  private String[] demoNames; 
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo);
    initData();
    initViews();
    
  }
  
  @Override
  public void initData() {
    demoNames = new String[]{
        "1手势缩放图片 PhotoViewFragment",
        "2各种弹出框 DialogFragment",
        "3顶部导航栏  只能和ViewPager配合使用  SlidingTabTripFragment",
        "4 toast提示 ToastFragment",
        "5 底部导航栏 IndicatorFragment",
        "6 滑动式开关按钮 SwitchButtonFragment",
        "7 设置页面的ITEM展示 SettingItemFragment",
        "8 文件选择 FileSelectFragment",
        "9 图片裁剪 PictureCropFragment",
        "10 http请求获取数据 HttpQueryFragment",
        "11 HTTP请求图片 ImageLoadFragment",
        "12 首次进入时宣传介绍页面 IntroduceFragment",
        "13 圆角图片   RoundConerFragment ",
        "14 轮流播放精品推荐  RecommandFragment",
        "15 手动签名  SignatureFragment",
        "16 首字母快速检索   AlphabetSectionFragment",
        "17 分类展示列表 ExpandableFragment",
        "18 左右滑动的导航列表 HorizontalGridFragment",
        "19 3*3九宫格手势密码 LockPatternFragment"
        
        
    };
  }
  
  @Override
  public void initViews() {
    mListView = (ListView) findViewById(R.id.demo_listview);
    ISimpleTextAdapter adapter = new ISimpleTextAdapter(this, demoNames);
    mListView.setAdapter(adapter);
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        
        DemoConstant constant = null ;
        switch (arg2) {
        case 0:
          constant =   DemoConstant.demo_photoview;
          break;
        case 1:
          constant  = DemoConstant.demo_dialogs;
          break;
        case 2:
          constant  = DemoConstant.demo_slidingTabStrip;
          break;
        case 3:
          constant = DemoConstant.demo_toast;
          break;
        case 4:
          constant = DemoConstant.demo_indicator;
          break;
        case 5:
          constant = DemoConstant.demo_switchButton;
          break;
        case 6:
          constant = DemoConstant.demo_settingItem;
          break;
        case 7:
          constant = DemoConstant.demo_fileselect;
          break;
        case 8:
          constant = DemoConstant.demo_pictureCrop;
          break;
        case 9:
          constant = DemoConstant.demo_httpQuery;
          break;
        case 10:
          constant = DemoConstant.demo_imageLoader;
          break;
        case 11:
          constant = DemoConstant.demo_introduce;
          break;
          case 12:
            constant = DemoConstant.demo_roundCorner;
            break;
          case 13:
            constant = DemoConstant.demo_recommand;
            break;
          case 14:
            constant = DemoConstant.demo_signature;
            break;
          case 15:
            constant = DemoConstant.demo_alphabetSection;
            break;
          case 16:
            constant = DemoConstant.demo_expandable;
            break;
          case 17:
            constant = DemoConstant.demo_horizontalGrid;
            break;
          case 18:
            constant = DemoConstant.demo_lockpattern;
            break;

        default:
          break;
        }
        
        skipToDemos(constant);
      }
    });
    
  }
  
  @Override
  public void saveData() {
    
  }
  @Override
  public void flushPage() {
    
  }

  
  private void skipToDemos(DemoConstant demoConstant){
    
    Intent intent = new Intent(getBaseContext(), DemoDetailActivity.class);
    intent.putExtra("type", demoConstant);
    startActivityForResult(intent, 0x11);
    
  }
  
  
}
