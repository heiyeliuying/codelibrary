package com.demo.icodelibrary.demos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.demo.icodelibrary.R;
import com.edmodo.cropper.CropImageView;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;

/***
 * 图片裁剪
 * @author chenzheng
 *
 */
public class PictureCropFragment extends IFragment implements IPageRuler {
  private Button button_crop ;
  private CropImageView cropImageView;
  private ImageView imageView_result;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_picture_crop, null);
    initData();
    initViews();
    
    return mContentView;
  }
  

  @Override
  public void initViews() {
    cropImageView = (CropImageView) mContentView.findViewById(R.id.picture_crop_cropper);
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.demo_1);
    cropImageView.setImageBitmap(bitmap);
  //  cropImageView.rotateImage(30);//设定图片的旋转角度
    cropImageView.setFixedAspectRatio(true);//设置允许按比例截图，如果不设置就是默认的任意大小截图
    cropImageView.setAspectRatio(1, 1);//设置比例为一比一
    
    
    imageView_result = (ImageView)mContentView.findViewById(R.id.picture_crop_result);
    
    button_crop = (Button) mContentView.findViewById(R.id.picture_crop_btn);
    button_crop.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View arg0) {
        
     Bitmap bitmap =    cropImageView.getCroppedImage();
     imageView_result.setImageBitmap(bitmap);
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
