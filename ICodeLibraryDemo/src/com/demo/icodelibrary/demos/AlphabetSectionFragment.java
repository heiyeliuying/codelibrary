package com.demo.icodelibrary.demos;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.tools.utils.ILogUtils;
import com.icode.library.widgets.sectionIndexer.ISimpleSectionIndicator;
/**
 * 首字符排序
 */
public class AlphabetSectionFragment extends IFragment implements IPageRuler {
  
  private ISimpleSectionIndicator sectionIndicator;
  private TextView textView_info ;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_alphabet_section, null);
    initData();
    initViews();
    return mContentView;
  }

  @Override
  public void initViews() {

    textView_info = (TextView) mContentView.findViewById(R.id.alphabet_info);
    
    sectionIndicator = (ISimpleSectionIndicator) mContentView.findViewById(R.id.alphabet_indexer);
    sectionIndicator.setSectionIndexerChangedListener(new ISimpleSectionIndicator.SectionIndexerChangedListener() {
      
      @Override
      public void onSectionIndexerChanged(int position, String section) {
        
        textView_info.setText(section);
        
      }
    });
    
   // sectionIndicator.setSections(tempList());
    
    
  }

  
  private List<String> tempList(){
    List<String> strings = new ArrayList<String>();
    strings.add("赵");
    strings.add("钱");
    strings.add("孙");
    strings.add("李");
    strings.add("周");
    strings.add("伍");
    strings.add("郑");
    strings.add("王");
    strings.add("冯");
    strings.add("陈");
    strings.add("马");
    strings.add("姓");
    strings.add("刘");
    
    return strings;
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
