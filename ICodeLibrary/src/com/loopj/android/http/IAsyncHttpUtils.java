package com.loopj.android.http;

/**
 * 提供AsyncHttpClient的单例对象
 */
public class IAsyncHttpUtils {


  private static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

  public static AsyncHttpClient getAsyncHttpClient() {
    return asyncHttpClient;
  }
  
  
  


  
}
