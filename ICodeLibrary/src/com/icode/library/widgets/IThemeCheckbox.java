package com.icode.library.widgets;

import com.code.icodelibrary.R;
import com.icode.library.tools.utils.IColorStateUtils;
import com.icode.library.widgets.config.IThemeApplication;
import com.icode.library.widgets.config.IThemeStyle;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Checkbox
 */
public class IThemeCheckbox extends CheckBox implements IThemeRule {
  IThemeStyle themeStyle;

  public IThemeCheckbox(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initTheme();
  }

  public IThemeCheckbox(Context context, AttributeSet attrs) {
    super(context, attrs);
    initTheme();
  }

  public IThemeCheckbox(Context context) {
    super(context);
    initTheme();
  }

  @Override
  public void initTheme() {
    IThemeApplication application = (IThemeApplication) getContext().getApplicationContext();
    themeStyle = application.getThemeConfiguration().getThemeStyle();
    initBackgroudTheme();
    initTextTheme();

  }

  @Override
  public void initBackgroudTheme() {

    switch (themeStyle) {
    case BLUE:
      setButtonDrawable(R.drawable.chebox_blue_bg_selector);
      setPadding(10, 0, 0, 0);
      break;
    case NONE:
      
      break;

    default:
      break;
    }
   
  }

  @Override
  public void initTextTheme() {
    switch (themeStyle) {
    case BLUE:
      setTextColor(IColorStateUtils.newSelector(getContext(), Color.parseColor("#000000"),
          Color.parseColor("#44000000")));
      break;
    case NONE:

      break;
    default:
      break;
    }

  }

}
