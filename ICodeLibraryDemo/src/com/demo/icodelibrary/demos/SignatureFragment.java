package com.demo.icodelibrary.demos;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.demo.icodelibrary.MainActivity;
import com.demo.icodelibrary.R;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.github.gcacace.signaturepad.views.SignaturePad.OnSignedListener;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.picture.IPictureUtils;
import com.icode.library.tools.utils.IFileUtils;
import com.icode.library.tools.utils.ILogUtils;

public class SignatureFragment extends IFragment implements IPageRuler {
  
  private SignaturePad signaturePad ;
  private Button button_clear,button_save ;
   
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_signature, null);
    initData();
    initViews();
    return mContentView;
  }

  @Override
  public void initViews() {
    
    signaturePad = (SignaturePad) mContentView.findViewById(R.id.signature_pad);
    signaturePad.setOnSignedListener(new OnSignedListener() {
      
      @Override
      public void onSigned() {
        ILogUtils.logError("SignatureFragment", "签名完成");
      }
      
      @Override
      public void onClear() {
        ILogUtils.logError("SignatureFragment", "签名被清除");
      }
    });
    signaturePad.setBackgroundColor(Color.WHITE);
    
    
    button_clear = (Button) mContentView.findViewById(R.id.signature_clear);
    button_save = (Button) mContentView.findViewById(R.id.signature_save);

    
    button_clear.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View arg0) {
        signaturePad.clear();
      }
    });
    
    button_save.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View arg0) {
        String path =  "/sdcard/test_signature.jpg";
        //signaturePad.getTransparentSignatureBitmap();获取背景色为透明的签名
        Bitmap bitmap = signaturePad.getSignatureBitmap();
        IFileUtils.saveBitmap(bitmap,path);
        IPictureUtils.addToGallery(mActivity, Uri.fromFile(new File(path)));
        
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

}
