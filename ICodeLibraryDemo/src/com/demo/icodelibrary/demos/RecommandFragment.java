package com.demo.icodelibrary.demos;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.tools.utils.IToastUtils;
import com.icode.library.widgets.marquee.ISimpleMarqueeLayout;
import com.icode.library.widgets.marquee.ISimpleMarqueeLayout.IMarqueeItem;
import com.icode.library.widgets.marquee.ISimpleMarqueeLayout.MarqueeItemSelectedListener;

public class RecommandFragment extends IFragment implements IPageRuler {
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_recommand, null);
    initData();
    initViews();
    return mContentView;
  }

  @Override
  public void initViews() {
    ISimpleMarqueeLayout marqueeLayout = (ISimpleMarqueeLayout) mContentView.findViewById(R.id.marquee_id);
    
    List<ISimpleMarqueeLayout.IMarqueeItem> iMarqueeItems = new ArrayList<ISimpleMarqueeLayout.IMarqueeItem>();
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01901.jpg", "", ""));
    
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01902.jpg", "", ""));
    
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01903.jpg", "", ""));
    
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01904.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01905.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01906.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01907.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01908.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01911.jpg", "", ""));
    iMarqueeItems.add(new ISimpleMarqueeLayout.IMarqueeItem
        ("http://mm.haha168.com/photos/201201/01910.jpg", "", ""));
    
    marqueeLayout.flushWithData(iMarqueeItems);
    
    marqueeLayout.setOnItemSelectedListener(new MarqueeItemSelectedListener() {
      
      @Override
      public void onMarqueeItemSelected(IMarqueeItem marqueeItem, int position) {
        IToastUtils.toast(mActivity, marqueeItem.getUri());
      }
    });
    
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
