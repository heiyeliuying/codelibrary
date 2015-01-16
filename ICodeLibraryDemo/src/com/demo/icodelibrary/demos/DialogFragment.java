package com.demo.icodelibrary.demos;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.widgets.adapter.ISimpleTextAdapter;
import com.icode.library.widgets.dialog.ISimpleAlertDialog;
import com.icode.library.widgets.dialog.ISimpleListDialog;
import com.icode.library.widgets.dialog.ISimpleListDialog.DialogInfo;
import com.icode.library.widgets.dialog.ISimpleListDialog.DialogListItem;
import com.icode.library.widgets.dialog.ISimpleListDialog.DialogListItemClickListener;
import com.icode.library.widgets.dialog.ISimpleProgressDialog;
import com.icode.library.widgets.toast.ISimpleToast;

public class DialogFragment extends IFragment implements IPageRuler {
  
  private Spinner spinner;
  private String[] spinnerItems;
 @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_dialog, null);
   initData();
   initViews();
  return mContentView;
}
 

  @Override
  public void initViews() {
    spinner = (Spinner) mContentView.findViewById(R.id.dialog_spinner);
    
    ISimpleTextAdapter adapter = new ISimpleTextAdapter(mActivity, spinnerItems);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

      @Override
      public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        switch (arg2) {
        case 1:
          showSimpleDialog();
          break;
        case 2:
          showSimpleProgressDialog();
          break;
        case 3:
          showSimpleListDialog();
          break;

        default:
          break;
        }
        
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg0) {
        
      }
    });
  }

  @Override
  public void initData() {
    spinnerItems = new String[]{
        "请选择一个dialog",
        "simpleDialog 取消 确定弹出框",
        "simpleProgressDialog 加载框",
        "simpleListDialog 列表选择框"
        
    };
  }

  @Override
  public void saveData() {
    
  }

  @Override
  public void flushPage() {
    
  }

  private void showSimpleDialog(){
    Dialog dialog = ISimpleAlertDialog.getInstance(mActivity, new OnClickListener() {
      
      @Override
      public void onClick(View arg0) {
        
      }
    });
    dialog.setCanceledOnTouchOutside(false);
    Window window = dialog.getWindow();
    window.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
    dialog.show();
  }
  
  
  private void showSimpleProgressDialog() {
    Dialog dialog = ISimpleProgressDialog.getInstance(mActivity,"中华人民共和国终于在这一天成立了,打到日本帝国主义!");
    dialog.setCanceledOnTouchOutside(false);
    dialog.show();
    

  }
  
  private void  showSimpleListDialog(){
    String items[] = new String[]{
        "语文",
        "英语",
        "数学",
        "化学",
        "综上所述,并不是所有的内容都会在列表中显示出来",
        "最高长度为2行"
        
    };
    DialogInfo<String> dialogInfo = DialogInfo.getInstance(mActivity, items, new DialogListItemClickListener() {

      @Override
      public <String> void onDialogListItemClicked(DialogListItem<String> listItem) {
        ISimpleToast.showTopToast(mActivity, listItem.getItemName());
      }
    
    });
   /* dialogInfo.setDividerColor(Color.BLUE);
    dialogInfo.setTitleColor(Color.BLUE);
    dialogInfo.setGravity(Gravity.CENTER);*/
    
    Dialog dialog = ISimpleListDialog.getInstance(mActivity, dialogInfo);
    dialog.show();
    
  }
  
}
