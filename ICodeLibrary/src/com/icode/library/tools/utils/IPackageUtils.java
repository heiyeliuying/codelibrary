package com.icode.library.tools.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;

/**
 * 获取包信息相关的内容
 * 
 * 
 */
public class IPackageUtils {

  /***
   * 获取当前应用程序的packageVersionCode属性
   * 
   * @param context
   * @return
   */
  public static int getVersionCode(Context context) {
    int code = 0;
    try {
      PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
          context.getPackageName(), PackageManager.GET_ACTIVITIES);
      code = packageInfo.versionCode;
    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }
    return code;
  }

  public static String getVersionName(Context context) {
    String code = "";
    try {
      PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
          context.getPackageName(), PackageManager.GET_ACTIVITIES);
      code = packageInfo.versionName;
    } catch (NameNotFoundException e) {
      e.printStackTrace();
    }
    return code;
  }

  public static void restart(Context context) {

  }

  /**
   * 是否存放某个action的接收者
   * 
   * @param context
   * @param action
   * @return
   */
  public static boolean isIntentAvaliable(Context context, String action) {
    PackageManager packageManager = context.getPackageManager();
    Intent intent = new Intent(action);
    List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent,
        PackageManager.MATCH_DEFAULT_ONLY);
    return resolveInfos.size() > 0;
  }

  /**
   * 在本程序中打开其他的程序
   * 
   * @param context
   * @param packageName
   *          包名称
   * @param className
   *          类名称
   */
  public static void openApp(Context context, String packageName, String className) {
    Intent intent = new Intent(Intent.ACTION_MAIN);
    ComponentName componentName = new ComponentName(packageName, className);
    intent.setComponent(componentName);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }

  /**
   * 根据packageName启动应用
   * 
   * @param activity
   * @param packageName
   */
  public static void openApp(Activity activity, String packageName) {
    Intent mintent = activity.getPackageManager().getLaunchIntentForPackage(packageName);
    activity.startActivity(mintent);
  }

  /**
   * 某个程序是否已安装
   * 
   * @param context
   * @param packageName
   * @return
   */
  public static boolean checkApkExist(Context context, String packageName) {
    if (packageName == null || "".equals(packageName)) {
      return false;
    }
    try {
      PackageManager info = context.getPackageManager();
      info.getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
      return true;
    } catch (NameNotFoundException e) {
      return false;
    }
  }

  /**
   * 获取所有的应用信息,包含系统自带和用户安装的全部应用信息.获取的packageInfo中包含很多不能独立运行的应用,想要获取全部可运行的应用信息,
   * 请使用getDisplayApks
   * 
   * @param activity
   * @return
   */
  public static List<PackageInfo> getAllPackages(Activity activity) {
    PackageManager packageManager = activity.getPackageManager();
    List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
    return packageInfoList;
  }

  /**
   * 根据类型获取package信息,获取的packageInfo中包含很多不能独立运行的应用,想要获取全部可运行的应用信息,
   * 请使用getDisplayApks
   * 
   * @param activity
   * @param isSystem
   *          是否为系统预装应用
   * @return
   */
  public static List<PackageInfo> getPackagesByType(Activity activity, boolean isSystem) {
    PackageManager packageManager = activity.getPackageManager();
    List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
    List<PackageInfo> results = new ArrayList<PackageInfo>();

    if (isSystem) {
      for (PackageInfo packageInfo : packageInfoList) {
        if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
          results.add(packageInfo);
        }

      }
    } else {
      for (PackageInfo packageInfo : packageInfoList) {
        if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
          results.add(packageInfo);
        }
      }
    }

    return results;

  }

  /**
   * 判断是否为系统预装软件
   * 
   * @param packageInfo
   * @return
   */
  public static boolean isSystemApplication(PackageInfo packageInfo) {
    return (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0;
  }

  /**
   * 获取全部可运行的应用信息,主要用于launcher展示
   * 
   * @param context
   * @return
   */
  public List<ResolveInfo> getDisplayApks(Context context) {
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_LAUNCHER);
    PackageManager packageManager = context.getPackageManager();
    List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent,
        PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
    Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(packageManager));
    return resolveInfos;

  }

}
