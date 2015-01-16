package com.imageloader;

import java.io.File;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
/**
 * 在Application中初始化ImageLoader
 * 想要使用图片加载功能,则必须继承该application
 *
 */
public class IImageLoaderApplication extends Application {

  @TargetApi(Build.VERSION_CODES.GINGERBREAD)
  @SuppressWarnings("unused")
  @Override
  public void onCreate() {
   /* if (Constants.Config.DEVELOPER_MODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
    }*/

    super.onCreate();

    initImageLoader(getApplicationContext());
  }

  public static void initImageLoader(Context context) {
    
    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
        .threadPriority(Thread.NORM_PRIORITY - 2)
        .denyCacheImageMultipleSizesInMemory()
        .diskCacheFileNameGenerator(new Md5FileNameGenerator())
        .diskCacheSize(50 * 1024 * 1024) // 50 Mb
        .tasksProcessingOrder(QueueProcessingType.LIFO)
        .writeDebugLogs() // Remove for release app
        .imageDownloader(new BaseImageDownloader(context,5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间 
        .build();
    // Initialize ImageLoader with configuration.
    ImageLoader.getInstance().init(config);
  }
}
