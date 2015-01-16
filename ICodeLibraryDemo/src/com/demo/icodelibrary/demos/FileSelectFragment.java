package com.demo.icodelibrary.demos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.picture.ISimplePictureSelector;
import com.icode.library.widgets.toast.ISimpleToast;
/**
 * 文件选择
 * @author chenzheng
 *
 */
public class FileSelectFragment extends IFragment implements IPageRuler {
  
  private Button button_select,button_camera ;
  private TextView textView_info ;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_fileselect, null);
    initData();
    initViews();
    
    return mContentView;
  }

  @Override
  public void initViews() {

    button_select = (Button) mContentView.findViewById(R.id.fileselect_btn);
    button_camera = (Button)mContentView.findViewById(R.id.fileselect_btn_camera);
    textView_info = (TextView)mContentView.findViewById(R.id.fileselect_info);
    
    button_select.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View arg0) {
        selectPic();
      }
    });
    
    button_camera.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View arg0) {
        openCamera();
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

  
  private void selectPic(){
    
    ISimplePictureSelector.openSysPicture(mActivity, 0x11);
  }
  
  private void openCamera(){
    
    ISimplePictureSelector.openCamera(mActivity, 0x22);
  }
  
  
  /**
   * 默认该返回将会返给activity,请重新activity里的onActivityResult,然后调用本类的onActivityResult.
   * 这里由于是示例代码,就不做展示了.只提供如何获取路径的方式
   */
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
   
    if(resultCode==Activity.RESULT_OK){
      if(requestCode==0x11){
        
        Uri uri = data.getData();
        ISimpleToast.showCenterToast(mActivity, uri.toString());
        textView_info.setText(uri.toString());
      }
      
      
    }
    
  }
}
