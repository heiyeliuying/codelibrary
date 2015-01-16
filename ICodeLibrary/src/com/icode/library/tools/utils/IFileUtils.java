package com.icode.library.tools.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

/***
 * 和文件相关的操作
 * 
 * @version 2.0
 */
public class IFileUtils {

	/***
	 * 删除指定文件
	 * 
	 * @param path
	 *            文件路径
	 * @return 文件不存在或者删除成功则返回true
	 */
	public static boolean deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
			return true;
		} else {
			return true;
		}

	}

	/***
	 * 删除指定目录以及该目录下的所有文件
	 * 
	 * @param directoryPath
	 *            目录
	 * @throws IOException
	 *             删除失败时抛出异常
	 */
	public static void deleteDirectory(String directoryPath) throws IOException {
		File f = new File(directoryPath);// 定义文件路径
		if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
			if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
				f.delete();
			} else {// 若有则把文件放进数组，并判断是否有下级目录
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;
				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						deleteDirectory(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
					} else {
						delFile[j].delete();// 删除文件
					}

				}
			}
			deleteDirectory(directoryPath);// 递归调用
		}

	}

	/**
	 * 将输入流中的内容读取到字符串中
	 * 
	 * @param inputStream
	 *            输入流
	 * @return 包含了输入流中内容的字符串
	 * @throws IOException
	 *             流操作失败时抛出异常
	 */
	public static String readInputStreamToString(InputStream inputStream)
			throws IOException {
		int len = -1;
		StringBuilder sb = new StringBuilder();
		InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		while ((len = br.read()) != -1) {
			sb.append((char) len);
		}
		if (br != null) {
			br.close();
			br = null;
		}
		if (inputStream != null) {
			inputStream.close();
			inputStream.close();
		}
		return sb.toString();

	}

	/**
	 * 将输入流中的内容放入到byte数组中
	 * 
	 * @param inputStream
	 *            输入流
	 * @return 包含了输入流中所有内容的byte数组
	 * @throws IOException
	 */
	public static byte[] readInputStreamToBytes(InputStream inputStream)
			throws IOException {
		ByteArrayOutputStream outputSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inputStream.read(buffer)) != -1) {
			outputSteam.write(buffer, 0, len);
		}
		outputSteam.close();
		inputStream.close();
		return outputSteam.toByteArray();
	}

	/**
	 * 将byte数组中的内容下如到指定的文件中。
	 * 
	 * @param bytes
	 *            数组
	 * @param filePath
	 *            文件路径
	 * @return 返回新创建的文件对象
	 */
	public static File createFileWithBytes(byte[] bytes, String filePath) {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	/***
	 * 解压文件到指定的文件夹
	 * 
	 * @param sZipPathFile
	 *            压缩文件路径
	 * @param sDestPath
	 *            解压后的路径
	 * @throws Exception
	 *             解压过程中出现异常
	 */
	public static void unzip(String sZipPathFile, String sDestPath)
			throws Exception {
		FileInputStream fins = new FileInputStream(sZipPathFile);
		ZipInputStream zins = new ZipInputStream(fins);
		ZipEntry ze = null;
		byte ch[] = new byte[512];
		while ((ze = zins.getNextEntry()) != null) {
			File zfile = null;
			zfile = new File(sDestPath
					+ ze.getName());
			File fpath = new File(zfile.getParentFile().getPath());
			if (ze.isDirectory()) {
				if (!zfile.exists())
					zfile.mkdirs();
				zins.closeEntry();
			} else {
				if (!zfile.exists())
					zfile.getParentFile().mkdirs();
				if (!fpath.exists())
					fpath.mkdirs();
				FileOutputStream fouts = new FileOutputStream(zfile);
				int i;
				while ((i = zins.read(ch)) != -1)
					fouts.write(ch, 0, i);
				zins.closeEntry();
				fouts.close();
			}
		}
		fins.close();
		zins.close();
	}

	/***
	 * 关闭流
	 */
	public static void closeInputStream(InputStream inputStream) {
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/***
	 * 关闭输出流
	 * 
	 * @param outputStream
	 */
	public static void closeOutputStream(OutputStream outputStream) {
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/***
	 * 将输入流中的数据存储到私有文件夹中，用户无法通过其他软件获取到该文件
	 * @param imageName
	 *            文件名称
	 * @param fileInputStream
	 *            输入流
	 * @param context
	 *            上下文
	 */
	public static void saveFileInPrivate(String imageName,
			FileInputStream fileInputStream, Context context) {
		FileOutputStream fileOutputStream;
		byte[] buffer = new byte[1024];
		try {
			fileOutputStream = context.openFileOutput(imageName,
					Context.MODE_PRIVATE);
			while (fileInputStream.read(buffer) != -1) {
				fileOutputStream.write(buffer);
			}
			fileOutputStream.flush();
			fileInputStream.close();
			fileOutputStream.close();
			buffer = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * @author 陈正
	 * @since 2012/1/5 将指定路径的图片存放到私有文件夹中
	 * @param imageName
	 *            文件名称
	 * @param imagePath
	 *            文件路径，如/sdcard/sample.jpg
	 * @param context
	 */
	public static void saveFileInPrivate(String imageName, String imagePath,
			Context context) {
		try {
			FileInputStream fileInputStream = new FileInputStream(imagePath);
			saveFileInPrivate(imageName, fileInputStream, context);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 保存图片
	 * @param bitmap 要保存的图片
	 * @param path 保存的路径
	 * @return
	 */
	public static boolean saveBitmap(Bitmap bitmap, String path) {
		boolean isSuccess = false;
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			FileOutputStream fstream = new FileOutputStream(file);
			isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
					fstream);
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {
			return isSuccess;
		}

	}


	/**
	 * SDCARD当前是否可用
	 * @return
	 */
	public static boolean isSdcardUserable(){
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
	
}
