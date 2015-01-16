package com.icode.library.tools.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
/**
 * 截图辅助类
 * @author chenzheng
 *
 */
public class IScreenShotUtils {


  /**
   * 全屏截图
   * @param activity
   * @return
   */
  public static Bitmap shotAllScreen(Activity activity,boolean isContainsStatusBar) {

    // View是你需要截图的View
    View view = activity.getWindow().getDecorView();
    view.setDrawingCacheEnabled(true);
    view.buildDrawingCache();
    Bitmap b1 = view.getDrawingCache();

    // 获取状态栏高度
    Rect frame = new Rect();
    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
    int statusBarHeight = frame.top;
    System.out.println(statusBarHeight);

    // 获取屏幕长和高
    int width = activity.getWindowManager().getDefaultDisplay().getWidth();
    int height = activity.getWindowManager().getDefaultDisplay()
        .getHeight();

    if(isContainsStatusBar){
      statusBarHeight = 0;
    }
    
    Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
        - statusBarHeight);
    view.destroyDrawingCache();
    return b;
  }

  /**
   * 区域性截图
   * @param activity
   * @param rect
   * @return
   */
  public static Bitmap takeRectShot(Activity activity,Rect rect){
    Bitmap bitmap =null;
    View view = activity.getWindow().getDecorView();
    view.setDrawingCacheEnabled(true);
    view.buildDrawingCache();
    Bitmap b1 = view.getDrawingCache();
    bitmap = Bitmap.createBitmap(b1, rect.left, rect.top, rect.right, rect.bottom);
    view.destroyDrawingCache();
    return bitmap;
  }
  

  
  
  
  

}
