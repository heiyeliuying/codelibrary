package com.demo.icodelibrary.demos;

import org.apache.http.Header;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.widgets.dialog.ISimpleAlertDialog;
import com.icode.library.widgets.dialog.ISimpleAlertDialog.DialogInfo;
import com.icode.library.widgets.toast.ISimpleToast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.IAsyncHttpUtils;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
/**
 * 获取网络上的数据
 * @author chenzheng
 *
 */
public class HttpQueryFragment extends IFragment implements IPageRuler {
  private Button btn_query;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_httpquery, null);
    initData();
    initViews();
    return mContentView;
  }
  
  

  @Override
  public void initViews() {
    btn_query = (Button) mContentView.findViewById(R.id.httpquery_btn);
    btn_query.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View arg0) {
        load();
      }
    });

  }
  
  private void load(){
     String urlStr = "http://doido.sinaapp.com/xingzuo/";
  AsyncHttpClient client =  IAsyncHttpUtils.getAsyncHttpClient();
  RequestParams requestParams = new RequestParams();
  requestParams.put("type", "d");
  requestParams.put("xingzuo", 5);
  client.post(urlStr,requestParams,new TextHttpResponseHandler() {
    
    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
      ISimpleToast.showCenterToast(mActivity, responseString);
    }
    
    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
      showMsg("加载失败");
    }
  });
  

  }
  
  
  private void showMsg(String msg){
    DialogInfo dialogInfo = DialogInfo.getInstance();
    dialogInfo.setMessage(msg);
    ISimpleAlertDialog.getInstance(mActivity, dialogInfo, null).show();
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
