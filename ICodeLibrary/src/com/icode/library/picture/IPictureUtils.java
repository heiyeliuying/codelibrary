package com.icode.library.picture;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 和图片处理相关的辅助类
 *
 */
public class IPictureUtils {

  /**
   * 将文件路径发给系统,以便系统的图库能够检索到.
   * @param context
   * @param contentUri 图片路径
   */
  public static void addToGallery(Context context ,Uri contentUri){
    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
    mediaScanIntent.setData(contentUri);
    context.sendBroadcast(mediaScanIntent);
    
  }
}
