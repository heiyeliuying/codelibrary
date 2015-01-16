package com.demo.icodelibrary.demos;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class ImageLoadFragment extends IFragment implements IPageRuler {
  
  private ImageView imageView ;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_imageloader, null);
    initData();
    initViews();
    return mContentView;
  }
  

  @Override
  public void initViews() {
    imageView = (ImageView) mContentView.findViewById(R.id.imageloader_img);
    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()  
    .showImageOnLoading(R.drawable.ic_launcher)            //加载图片时的图片  
    .showImageForEmptyUri(R.drawable.ic_launcher)         //没有图片资源时的默认图片  
    .showImageOnFail(R.drawable.ic_launcher)              //加载失败时的图片  
    .cacheInMemory(true)                               //启用内存缓存  
    .cacheOnDisk(true)                                 //启用外存缓存  
    .considerExifParams(true)                          //启用EXIF和JPEG图像格式  
    .displayer(new RoundedBitmapDisplayer(20))         //设置显示风格这里是圆角矩形  
    .build();  
    
     String url = "http://t1.27270.com/uploads/tu/201501/563/1.jpg";
    imageLoader.displayImage(url, imageView, options, new SimpleImageLoadingListener(){
      @Override
      public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        super.onLoadingComplete(imageUri, view, loadedImage);
        if (loadedImage != null) {  
          ImageView imageView = (ImageView) view;  
               FadeInBitmapDisplayer.animate(imageView, 500);  
               imageView.setImageBitmap(loadedImage);
      } 
        
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
