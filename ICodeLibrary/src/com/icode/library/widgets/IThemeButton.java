package com.icode.library.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.code.icodelibrary.R;
import com.icode.library.widgets.config.IThemeApplication;
import com.icode.library.widgets.config.IThemeStyle;

/**
 * Button
 */
public class IThemeButton extends Button implements IThemeRule {

  IThemeStyle themeStyle;

  public IThemeButton(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initTheme();
  }

  public IThemeButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    initTheme();
  }

  public IThemeButton(Context context) {
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
    case NONE:

      break;
    case BLUE:
      setBackgroundResource(R.drawable.button_blue_bg_selector);
      break;

    default:
      break;
    }

  }

  @Override
  public void initTextTheme() {

    switch (themeStyle) {
    case NONE:

      break;
    case BLUE:

      setTextColor(getResources().getColorStateList(R.drawable.button_blue_text_selector));

      break;

    default:
      break;
    }

  }

}
