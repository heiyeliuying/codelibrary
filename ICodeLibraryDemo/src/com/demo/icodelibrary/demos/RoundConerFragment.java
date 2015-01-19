package com.demo.icodelibrary.demos;

import java.util.Random;

import android.graphics.Color;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.makeramen.roundedimageview.RoundedImageView;

public class RoundConerFragment extends IFragment implements IPageRuler {
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView  =LayoutInflater.from(mActivity).inflate(R.layout.demo_roundcorner, null);
    initData();
    initViews();
    return mContentView;
  }

  @Override
  public void initViews() {
    
    
    RoundedImageView riv = (RoundedImageView) mContentView.findViewById(R.id.roundCorner_imageview);
    riv.setScaleType(ScaleType.CENTER_CROP);
    //图片转圆角的弧度
    riv.setCornerRadius((float) (10*Math.random()));
    riv.setBorderWidth((float) 2);
    riv.setBorderColor(Color.GREEN);
    riv.mutateBackground(true);
  //  riv.setImageDrawable(drawable);
   // riv.setBackground(backgroundDrawable);
    //是否强制为椭圆
    riv.setOval(true);
    riv.setTileModeX(Shader.TileMode.REPEAT);
    riv.setTileModeY(Shader.TileMode.REPEAT);
    

    
  
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
