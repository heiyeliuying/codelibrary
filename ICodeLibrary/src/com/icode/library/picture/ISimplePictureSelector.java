package com.icode.library.picture;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
/**
 * 图片选择相关的选择器
 */
public class ISimplePictureSelector {
  
  /**
   * 打开系统图库，选中一张图片.获取的图片地址存放在intent中,例如Uri uriPic = data.getData();
   * 如果当前无图库程序,获取出现异常,则不做任何处理
   */
  public static void openSysPicture(Activity activity,int requestCode) {
    try {
      Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
      intent.setType("image/*");
      activity.startActivityForResult(intent, requestCode);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 打开系统相机,拍摄一张图片.图片存储在intent或者bundle中.提取代码示例
   * Uri uriPic_camera = data.getData();
      if (uriPic_camera == null) {
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");
        if (bitmap != null) {
          bitmap = IImageUtils.scaleBitmap(bitmap, attchWidth,
              attchHeight);
        }

   * @param activity
   * @param requestCode
   */
  public static void  openCamera(Activity activity,int requestCode) {
    try {
      Intent intent_otherCamera = new Intent(
          MediaStore.ACTION_IMAGE_CAPTURE);
      activity.startActivityForResult(intent_otherCamera, requestCode);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
