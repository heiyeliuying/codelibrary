package com.icode.library.widgets;

import com.code.icodelibrary.R;
import com.icode.library.widgets.config.IThemeApplication;
import com.icode.library.widgets.config.IThemeStyle;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
/**
 * EditText
 *
 */
public class IThemeEditText extends EditText implements IThemeRule {
  IThemeStyle themeStyle;
  

  public IThemeEditText(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initTheme();
  }

  public IThemeEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    initTheme();
  }

  public IThemeEditText(Context context) {
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
      setBackgroundResource(R.drawable.edittext_blue_bg_selector);
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

      setTextColor(getResources().getColorStateList(R.drawable.edittext_blue_text_selector));
      setHintTextColor(R.drawable.edittext_blue_texthint_selector);
      break;

    default:
      break;
    }

  }

}
