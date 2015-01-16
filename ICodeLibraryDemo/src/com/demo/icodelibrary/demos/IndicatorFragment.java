package com.demo.icodelibrary.demos;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.widgets.indicator.IIndicatorChangedListener;
import com.icode.library.widgets.indicator.IIndicatorDirection;
import com.icode.library.widgets.indicator.IIndicatorInfo;
import com.icode.library.widgets.indicator.IIndicatorNormalLayout;
import com.icode.library.widgets.indicator.IIndicatorParam;
import com.icode.library.widgets.indicator.IIndicatorSingleLayout;
import com.icode.library.widgets.toast.ISimpleToast;

/**
 * 导航栏
 * 
 * @author chenzheng
 * 
 */
public class IndicatorFragment extends IFragment implements IPageRuler {
  private ArrayList<IIndicatorInfo> indicatorInfos = new ArrayList<IIndicatorInfo>();
  private IIndicatorSingleLayout singleLayout;
  private IIndicatorNormalLayout normalLayout;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_indicator, null);
    initData();
    initViews();
    return mContentView;
  }

  @Override
  public void initViews() {

    singleLayout = (IIndicatorSingleLayout) mContentView.findViewById(R.id.indicator_single);
    singleLayout.setIndicatorItems(indicatorInfos);
    singleLayout.setNumberPerPage(4);
    singleLayout.switchTo(2);
    singleLayout.showNotification(IIndicatorParam.param_3);
    
    singleLayout.setmIndicatorChangedListener(new IIndicatorChangedListener() {
      
      @Override
      public void onIndicatorChanged(IIndicatorInfo indicatorInfo,
          IIndicatorParam indicatorParam, IIndicatorDirection direction) {
        
      }
      @Override
      public void onIndicatorRepeated(IIndicatorInfo indicatorInfo,
          IIndicatorParam indicatorParam) {
        
      }
      
    });

    normalLayout = (IIndicatorNormalLayout) mContentView.findViewById(R.id.indicator_normal);
    normalLayout.setIndicatorItems(indicatorInfos);

    
  }

  @Override
  public void initData() {
    IIndicatorInfo indicatorInfo;

    int color_pressed = Color.parseColor("#ff52c6");
    int color_normal = Color.parseColor("#919191");

    indicatorInfo = new IIndicatorInfo();
    indicatorInfo.setIndicatorName("微整形");
    indicatorInfo.setIndicatorParam(IIndicatorParam.param_1);
    indicatorInfo.setIndicatorIconNormal(R.drawable.index_indicator_item_weizheng_normal);
    indicatorInfo.setIndicatorIconSelected(R.drawable.index_indicator_item_weizheng_pressed);
    indicatorInfo.setIndicatorNameColorNormal(color_normal);
    indicatorInfo.setIndicatorNameColorSelected(color_pressed);
    indicatorInfos.add(indicatorInfo);

    indicatorInfo = new IIndicatorInfo();
    indicatorInfo.setIndicatorName("微达人");
    indicatorInfo.setIndicatorParam(IIndicatorParam.param_3);
    indicatorInfo.setIndicatorIconNormal(R.drawable.index_indicator_item_daren_normal);
    indicatorInfo.setIndicatorIconSelected(R.drawable.index_indicator_item_daren_pressed);
    indicatorInfo.setIndicatorNameColorNormal(color_normal);
    indicatorInfo.setIndicatorNameColorSelected(color_pressed);
    indicatorInfos.add(indicatorInfo);

    indicatorInfo = new IIndicatorInfo();
    indicatorInfo.setIndicatorName("喵星人");
    indicatorInfo.setIndicatorParam(IIndicatorParam.param_2);
    indicatorInfo.setIndicatorIconNormal(R.drawable.index_indicator_item_miaoxingren_normal);
    indicatorInfo.setIndicatorIconSelected(R.drawable.index_indicator_item_miaoxingren_pressed);
    indicatorInfo.setIndicatorNameColorNormal(color_normal);
    indicatorInfo.setIndicatorNameColorSelected(color_pressed);
    indicatorInfos.add(indicatorInfo);

    indicatorInfo = new IIndicatorInfo();
    indicatorInfo.setIndicatorName("喵小咪");
    indicatorInfo.setIndicatorParam(IIndicatorParam.param_0);
    indicatorInfo.setIndicatorIconNormal(R.drawable.index_indicator_item_xiaomi_normal);
    indicatorInfo.setIndicatorIconSelected(R.drawable.index_indicator_item_xiaomi_pressed);
    indicatorInfo.setIndicatorNameColorNormal(color_normal);
    indicatorInfo.setIndicatorNameColorSelected(color_pressed);
    indicatorInfos.add(indicatorInfo);
  }

  @Override
  public void saveData() {

  }

  @Override
  public void flushPage() {

  }

}
