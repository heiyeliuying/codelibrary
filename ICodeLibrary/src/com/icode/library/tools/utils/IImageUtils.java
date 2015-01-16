package com.icode.library.tools.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;

/**
 * 图片处理
 * 
 * 
 */
public class IImageUtils {

	

	/**
	 * 等比例缩放图片到指定的高宽.此方法不进行图片的角度处理
	 * @param uri 图片地址
	 * @param destinationWidth 目标宽度
	 * @param destinationHeight 目标高度
	 * @param context
	 * @return
	 */
	public static Bitmap scaleImage(Uri uri, int destinationWidth,
			int destinationHeight, Context context) {
		Bitmap bitmap = null;
		Bitmap bitmap1 = null;
		try {
			BitmapFactory.Options options = new Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(uri), null, options);
			int outHeight = options.outHeight;
			int outWidth = options.outWidth;
			int ration_height = outHeight / destinationHeight;
			int ration_width = outWidth / destinationWidth;

			int ration_finally = ration_height >= ration_width ? ration_height
					: ration_width;
			BitmapFactory.Options options_finally = new Options();
			options_finally.inJustDecodeBounds = false;
			options_finally.inPurgeable = true;
			options_finally.inSampleSize = ration_finally + 1;

			bitmap = BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(uri), null, options_finally);

			double width_ = bitmap.getWidth();
			double height_ = bitmap.getHeight();
			double width_step = (double) destinationWidth / width_;
			double height_step = (double) destinationHeight / height_;
			double step = Math.min(width_step, height_step);
			
			bitmap1 = Bitmap.createScaledBitmap(bitmap,
					Double.valueOf(step * width_).intValue(),
					Double.valueOf(step * height_).intValue(), true);

		} catch (Exception e) {
		}
		return bitmap1;
	}

	/**
	 * 检测图片的旋转角度并矫正,并将图片等比例到指定高宽
	 * @param uri 图片地址
	 * @param destinationWidth 目标宽度
	 * @param destinationHeight 目标高度
	 * @param context
	 * @return
	 */
	public static Bitmap scaleImageWithRotate(Uri uri, int destinationWidth,
			int destinationHeight, Activity context) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(ICommonUtils.getAbsoluteImagePath(uri, context));
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,  
                    ExifInterface.ORIENTATION_UNDEFINED);  
			switch (orientation) {
			 case ExifInterface.ORIENTATION_ROTATE_90:  
				 degree = 90;  
                 break;  
             case ExifInterface.ORIENTATION_ROTATE_180:  
            	 degree = 180;  
                 break;  
             case ExifInterface.ORIENTATION_ROTATE_270:  
            	 degree = 270;  
                 break;  
             default:  
            	 degree = 0;  
                 break;  
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
		}
		
		Bitmap bitmap = null;
		Bitmap bitmap1 = null;
		try {
			BitmapFactory.Options options = new Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(uri), null, options);
			int outHeight = options.outHeight;
			int outWidth = options.outWidth;
			int ration_height = outHeight / destinationHeight;
			int ration_width = outWidth / destinationWidth;

			int ration_finally = ration_height >= ration_width ? ration_height
					: ration_width;
			BitmapFactory.Options options_finally = new Options();
			options_finally.inJustDecodeBounds = false;
			options_finally.inPurgeable = true;
			options_finally.inSampleSize = ration_finally + 1;

			bitmap = BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(uri), null, options_finally);
			Matrix matrix = new Matrix();
			if(degree!=0){
				matrix.postRotate(degree);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			}

			double width_ = bitmap.getWidth();
			double height_ = bitmap.getHeight();
			double width_step = (double) destinationWidth / width_;
			double height_step = (double) destinationHeight / height_;
			double step = Math.min(width_step, height_step);
			
			bitmap1 = Bitmap.createScaledBitmap(bitmap,
					Double.valueOf(step * width_).intValue(),
					Double.valueOf(step * height_).intValue(), true);

		} catch (Exception e) {
		}
		return bitmap1;
		
	}
	
	/**
	 * 保存图片
	 * 
	 * @param bitmap
	 * @param savepath
	 * @return
	 */
	public static boolean save(Bitmap bitmap, String savepath,int...qualityParams) {
		if(!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
			return false;
		boolean b = false;
		CompressFormat format = Bitmap.CompressFormat.JPEG;
		int quality = 95;
		if(qualityParams.length>0)
		 quality =qualityParams[0];
		OutputStream stream = null;
		try {
			File file = new File(savepath);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			file.createNewFile();
			stream = new FileOutputStream(file);
			b = bitmap.compress(format, quality, stream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(stream!=null)
					stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	/**
	 * 从指定路径加载图.不会对图片进行任何处理,谨防内存溢出
	 * 
	 * @param path
	 * @return
	 */
	public static Bitmap getBitmpFromFile(String path) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(
					path)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bitmap;
	}

	/**
	 * 将图片放入byte数组
	 * @param bmp
	 * @param needRecycle
	 * @return
	 */
	public static byte[] bmpToByteArray(final Bitmap bmp,
			final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.JPEG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}

		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	/**
	 * 缩放图片
	 * 
	 * @param bitmap
	 * @param newWidth
	 * @param newHeight
	 * @param rotate
	 * @return
	 */
	public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight) {
		double width_ = bitmap.getWidth();
		double height_ = bitmap.getHeight();
		double width_step = (double) newWidth / width_;
		double height_step = (double) newHeight / height_;
		double step = Math.min(width_step, height_step);
		Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap,
				Double.valueOf(step * width_).intValue(),
				Double.valueOf(step * height_).intValue(), true);
		bitmap.recycle();
		return bitmap1;
	}

	/**
	 * 缩放图片
	 * @param uri
	 * @param destinationWidth
	 * @param destinationHeight
	 * @param context
	 * @return
	 */
	public static Bitmap scaleBitmap(Uri uri, int destinationWidth,
			int destinationHeight, Context context) {
		Bitmap bitmap = scaleImage(uri, destinationWidth, destinationHeight,
				context);
		return bitmap;
	}
	
	/**
	 * 告诉系统图库有一张新图片创建
	 * @param activity
	 * @param uri
	 */
	public static void notifySystemPicChanged(Activity activity,Uri uri){
		 activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
	}

}
