package com.demo.icodelibrary.demos;

import java.util.Calendar;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.OnWheelChangedListener;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;

public class WheelFragment extends IFragment implements IPageRuler {

  private AbstractWheel wheel_years, wheel_months, wheel_days;
  private NumericWheelAdapter wheelAdapter_years, wheelAdapter_months,
      wheelAdapter_days;
  private int yearMin = 1964;
  private int textSize = 33;
  private DisplayMetrics displayMetrics;
  private Float floatSize;
  private int finalYear, finalMonth, finalDay;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView =LayoutInflater.from(mActivity).inflate(R.layout.demo_wheel, null);
    initData();
    initViews();
    return mContentView;
  }
  
  
  @Override
  public void initViews() {
    
    /***
     * 如果想要设置分割线等属性,请到布局文件中设置.
     * 
     * 
     */

    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    
    
    displayMetrics  = getResources().getDisplayMetrics();
    wheel_years = (AbstractWheel) mContentView
        .findViewById(R.id.user_detail_modify_birthday_years);
    
    wheelAdapter_years = new NumericWheelAdapter(mActivity, yearMin,
        calendar.get(Calendar.YEAR));
    floatSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
        textSize, displayMetrics);
    wheelAdapter_years.setTextSize(floatSize.intValue());
    wheel_years.setViewAdapter(wheelAdapter_years);
    wheel_years.setCyclic(true);
    
    wheel_years.addChangingListener(new OnWheelChangedListener() {

      @Override
      public void onChanged(AbstractWheel wheel, int oldValue,
          int newValue) {
        yearsChanged(oldValue, newValue);
      }
    });

    wheel_months = (AbstractWheel) mContentView
        .findViewById(R.id.user_detail_modify_birthday_months);
    wheelAdapter_months = new NumericWheelAdapter(mActivity, 1, 12);
    wheelAdapter_months.setTextSize(floatSize.intValue());
    wheel_months.setViewAdapter(wheelAdapter_months);
    wheel_months.setCyclic(true);
    wheel_months.addChangingListener(new OnWheelChangedListener() {

      @Override
      public void onChanged(AbstractWheel wheel, int oldValue,
          int newValue) {
        monthsChanged(oldValue, newValue);
      }
    });

    wheel_days = (AbstractWheel) mContentView
        .findViewById(R.id.user_detail_modify_birthday_days);
    wheelAdapter_days = new NumericWheelAdapter(mActivity, 1, 31);
    wheelAdapter_days.setTextSize(floatSize.intValue());
    wheel_days.setViewAdapter(wheelAdapter_days);
    wheel_days.setCyclic(true);
    wheel_days.addChangingListener(new OnWheelChangedListener() {

      @Override
      public void onChanged(AbstractWheel wheel, int oldValue,
          int newValue) {
        daysChanged(oldValue, newValue);
      }
    });

      wheel_years.setCurrentItem(calendar.get(Calendar.YEAR) - yearMin);
      wheel_months.setCurrentItem(calendar.get(Calendar.MONTH));
      wheel_days.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
      
      
      
  
  }

  
  /**
   * 当年分改变时触发
   * 
   * @param oldYear
   * @param newYear
   */
  private void yearsChanged(int oldYear, int newYear) {
    finalYear = newYear;
    int oldDayNumbers = daysOfMonth(oldYear, finalMonth);
    int newDayNumbers = daysOfMonth(newYear, finalMonth);
    if (oldDayNumbers == newDayNumbers) {

    } else {
      wheelAdapter_days = new NumericWheelAdapter(mActivity, 1,
          newDayNumbers);
      wheelAdapter_days.setTextSize(floatSize.intValue());
      wheel_days.setViewAdapter(wheelAdapter_days);
    }

  }

  /**
   * 当月份变化时触发
   * 
   * @param oldMonth
   * @param newMonth
   */
  private void monthsChanged(int oldMonth, int newMonth) {
    finalMonth = newMonth + 1;
    int oldDayNumbers = daysOfMonth(finalYear, oldMonth + 1);
    int newDayNumbers = daysOfMonth(finalYear, newMonth + 1);
    if (oldDayNumbers == newDayNumbers) {

    } else {
      wheelAdapter_days = new NumericWheelAdapter(mActivity, 1,
          newDayNumbers);
      wheelAdapter_days.setTextSize(floatSize.intValue());
      wheel_days.setViewAdapter(wheelAdapter_days);
    }
  }

  /**
   * 当日期变化时触发
   * 
   * @param oldDay
   * @param newDay
   */
  private void daysChanged(int oldDay, int newDay) {
    finalDay = newDay + 1;
  }

  /**
   * 获取某一个月的天数
   * 
   * @param month
   * @return
   */
  private int daysOfMonth(int year, int month) {
    int daysNumber = 30;
    switch (month) {
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
      daysNumber = 31;
      break;
    case 4:
    case 6:
    case 9:
    case 11:
      daysNumber = 30;
      break;
    case 2:
      if (isLeapYear(year))
        daysNumber = 29;
      else
        daysNumber = 28;
    default:
      break;
    }
    return daysNumber;

  }

  /**
   * 是否为闰年
   * 
   * @param year
   * @return 闰年则返回true
   */
  private boolean isLeapYear(int year) {
    year+=yearMin;
  //  ILogUtils.logError("年份", "year="+(year));
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
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
