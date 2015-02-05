package com.demo.icodelibrary.demos;

import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.icodelibrary.R;
import com.icode.library.common.IFragment;
import com.icode.library.common.IPageRuler;
import com.icode.library.widgets.lock.ISimpleLockPatternView;
import com.icode.library.widgets.lock.ISimpleLockPatternView.Cell;
import com.icode.library.widgets.lock.ISimpleLockPatternView.DisplayMode;
import com.icode.library.widgets.lock.ISimpleLockPatternView.LockPatternItem;
import com.icode.library.widgets.lock.ISimpleLockPatternView.OnPatternListener;

public class LockPatternFragment extends IFragment implements IPageRuler {
  private ISimpleLockPatternView lockPatternView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContentView = LayoutInflater.from(mActivity).inflate(R.layout.demo_lockpattern, null);
    initViews();
    return mContentView;
  }

  @Override
  public void initViews() {

    lockPatternView = (ISimpleLockPatternView) mContentView.findViewById(R.id.lock_pattern);

    ISimpleLockPatternView.LockPatternItem mPatternItem = LockPatternItem.getInstance(mActivity);
    mPatternItem.mRegularColor = Color.parseColor("#009933");
    lockPatternView.setPatternItem(mPatternItem);
    lockPatternView.setOnPatternListener(new OnPatternListener() {

      @Override
      public void onPatternStart() {

      }

      @Override
      public void onPatternDetected(List<Cell> pattern) {

        if (pattern.size() == 4) {
          lockPatternView.setDisplayMode(DisplayMode.Correct);
          return;
        }

        if (pattern.size() == 3) {
          lockPatternView.setDisplayMode(DisplayMode.Animate);
          return;
        }

        lockPatternView.setDisplayMode(DisplayMode.Wrong);
        return;

      }

      @Override
      public void onPatternCleared() {

      }

      @Override
      public void onPatternCellAdded(List<Cell> pattern) {

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
