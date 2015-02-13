package com.demo.icodelibrary.demos;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.widgets.adapter.ISimpleTextAdapter;
import com.icode.library.widgets.toast.ISimpleToast;
import com.icode.library.widgets.view.FloatingActionButton;

public class FloatingButtonFragment extends IFragment implements IPageRuler {
  String [] itemNames  ;
  FloatingActionButton mFab;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(com.demo.icodelibrary.R.layout.demo_floating_button, null);
    initData();
    initViews();
    
    return mContentView;
  }
  
  @Override
  public void initViews() {
    mFab = (FloatingActionButton) mContentView.findViewById(R.id.floatingBtn_btn);
    ListView listView = (ListView) mContentView.findViewById(R.id.floatingBtn_listview);
    ISimpleTextAdapter iSimpleTextAdapter = new ISimpleTextAdapter(mActivity, itemNames);
    listView.setAdapter(iSimpleTextAdapter);
    mFab.listenTo(listView);
    
    
    
    ISimpleFloatingListener clickListener = new ISimpleFloatingListener();
    findViewById(R.id.floatingBtn_blue, clickListener);
    findViewById(R.id.floatingBtn_green, clickListener);
    findViewById(R.id.floatingBtn_orange, clickListener);
    findViewById(R.id.floatingBtn_purple, clickListener);
    findViewById(R.id.floatingBtn_red, clickListener);
    
    findButtonById(R.id.floatingBtn_hide, clickListener);
    findButtonById(R.id.floatingBtn_show, clickListener);
    
    mFab.setOnClickListener(clickListener);

  }

  @Override
  public void initData() {
    itemNames = new String[]{
        "董小姐","张小姐","冯小姐",
        "董小姐","张小姐","冯小姐",
        "董小姐","张小姐","冯小姐",
        "董小姐","张小姐","冯小姐",
        "董小姐","张小姐","冯小姐",
        "董小姐","张小姐","冯小姐"
      };

  }

  @Override
  public void saveData() {
    

  }

  @Override
  public void flushPage() {
    

  }

  
  public void showFab(View view) {
    mFab.hide(false);
}

public void fabClicked(View view) {
  ISimpleToast.showCenterToast(mActivity, "点击了悬浮按钮");
}
private class ISimpleFloatingListener implements OnClickListener{

  @Override
  public void onClick(View view) {

    switch (view.getId()) {
    case R.id.floatingBtn_hide:
      mFab.hide(true);
      break;
    case R.id.floatingBtn_show:
      mFab.hide(false);
      break;
    case R.id.floatingBtn_btn:
      fabClicked(view);
      break;
        case R.id.floatingBtn_blue: {
            int holoBlue = Color.parseColor("#ff33b5e5");
            mFab.setColor(holoBlue);
            mFab.setDrawable(getResources().getDrawable(R.drawable.ic_content_new));
            break;
        }
        case R.id.floatingBtn_purple: {
            int holoPurple =Color.parseColor("#ffaa66cc");
            mFab.setColor(holoPurple);
            mFab.setDrawable(getResources().getDrawable(R.drawable.ic_av_play));
            break;
        }
        case R.id.floatingBtn_green: {
            int holoGreen = Color.parseColor("#ff99cc00");
            mFab.setColor(holoGreen);
            mFab.setDrawable(getResources().getDrawable(R.drawable.ic_content_discard));
            break;
        }
        case R.id.floatingBtn_orange: {
            int holoOrange = Color.parseColor("#ffffbb33");
            mFab.setColor(holoOrange);
            mFab.setDrawable(getResources().getDrawable(R.drawable.ic_social_add_person));
            break;
        }
        case R.id.floatingBtn_red: {
            int holoRed = Color.parseColor("#ffff4444");
            mFab.setColor(holoRed);
            mFab.setDrawable(getResources().getDrawable(R.drawable.ic_navigation_accept));
            break;
        }
    }

  }
  
  
}



  
}
