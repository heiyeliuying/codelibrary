package com.demo.icodelibrary.demos;

import uk.co.senab.photoview.PhotoViewAttacher;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;

public class PhotoViewFragment extends IFragment implements IPageRuler{
  
  private ImageView imageView;
  private PhotoViewAttacher viewAttacher ;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    mContentView  = LayoutInflater.from(mActivity).inflate(R.layout.demo_photoview, null);
    initData();
    initViews();
    return mContentView;
  }
  
  
  

  @Override
  public void initViews() {
    imageView  =(ImageView) mContentView.findViewById(R.id.photoview_img);
    imageView.setImageResource(R.drawable.demo_1);
    viewAttacher = new PhotoViewAttacher(imageView);
    
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
