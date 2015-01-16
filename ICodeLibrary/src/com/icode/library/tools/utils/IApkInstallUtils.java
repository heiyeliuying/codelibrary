package com.icode.library.tools.utils;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
/***
 * 主要涵盖了APK文件的安装和卸载功能
 * @version 1.0
 *
 */
public class IApkInstallUtils {

	 /***
     * 安装apk文件,例如  installApk(this, "test.apk");
     * @param context 上下文
     * @param fileName 文件名称，该文件默认放在根目录下
     */
    public static void installApk(Context context ,String fileName){
    	String filePath = Environment.getExternalStorageDirectory()+"/"+fileName;
    	installApkWithPath(context, filePath);
    }
    
    /***
     * 安装apk文件,例如  installApk(this, "/sdcard/installs/test.apk");
     * @param context 上下文
     * @param filePath 文件路径
     */
    public static void installApkWithPath(Context context ,String filePath){
    	Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setDataAndType(Uri.fromFile(new File(filePath)),"application/vnd.android.package-archive");
    	context.startActivity(intent);
    	
    }
    
    
    /***
     * 卸载软件
     * @param context
     * @param packageName package的Name属性
     */
    public static void unInstallApk(Context context,String packageName){
    	 Uri packageURI = Uri.parse("package:" + packageName);
    	Intent intent = new Intent(Intent.ACTION_DELETE,packageURI);
    	context.startActivity(intent);
    }
    
    
}
