package com.demo.icodelibrary.demos;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.demo.icodelibrary.DemoConstant;
import com.demo.icodelibrary.R;
import com.icode.library.common.IActivity;
import com.icode.library.common.IPageRuler;

public class DemoDetailActivity extends IActivity implements IPageRuler{
  private int fragmentId = R.id.fragment_id; 
  private DemoConstant demoConstant ;
  private FragmentManager fragmentManager ;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo_detail);
    initData();
    initViews();
  }
  

  @Override
  public void initViews() {
    
    switch (demoConstant) {
    case demo_photoview:
     skipToPhotoView(); 
      
      break;
    case demo_dialogs:
      skipToDialogs();
      break;
    case demo_slidingTabStrip:
      skipToSlidingTabStrip();
      break;
      case demo_toast:
        skipToToast();
        break;
      case demo_indicator:
        skipToIndicator();
        break;
      case demo_switchButton:
        skipToSwitchButton();
        break;
      case demo_settingItem:
        skipToSettingItem();
        break;
      case demo_fileselect:
        skipToFileSelect();
        break;
      case demo_pictureCrop:
        skipToPicCropper();
        break;
      case demo_httpQuery:
        skipToHttpQuery();
        break;
      case demo_imageLoader:
        skipToImageLoader();
        break ;
      case demo_introduce:
        skipToIntroduce();
        break;
      case demo_roundCorner:
        skipToRoundCorner();
        break;
    default:
      break;
    }
    
  }

  @Override
  public void initData() {
    demoConstant =   (DemoConstant) getIntent().getSerializableExtra("type");
    fragmentManager = getFragmentManager();
    
  }

  @Override
  public void saveData() {
    
    
  }

  @Override
  public void flushPage() {
    
    
  }
  
  private void skipToPhotoView(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    PhotoViewFragment fragment = new PhotoViewFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
  
  private void skipToDialogs(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    DialogFragment fragment = new DialogFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
  private void skipToSlidingTabStrip(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    SlidingTabTripFragment fragment = new SlidingTabTripFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
  
  private void skipToToast(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    ToastFragment fragment = new ToastFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
  
  private void skipToIndicator(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    IndicatorFragment fragment = new IndicatorFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
  
  public void skipToSwitchButton(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    SwitchButtonFragment fragment = new SwitchButtonFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
  
  public void skipToSettingItem(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    SettingItemFragment fragment = new SettingItemFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
  
  public void skipToFileSelect(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    FileSelectFragment fragment = new FileSelectFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
  
  public void skipToPicCropper(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    PictureCropFragment fragment = new PictureCropFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
  
  

  public void skipToHttpQuery(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    HttpQueryFragment fragment = new HttpQueryFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
  
  public void skipToImageLoader(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    ImageLoadFragment fragment = new ImageLoadFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
  
  public void skipToIntroduce(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    IntroduceFragment fragment = new IntroduceFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
  public void skipToRoundCorner(){
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    RoundConerFragment fragment = new RoundConerFragment();
    transaction.replace(fragmentId, fragment);
    transaction.commitAllowingStateLoss();
    
  }
}
