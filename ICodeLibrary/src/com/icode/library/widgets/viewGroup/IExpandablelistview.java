package com.icode.library.widgets.viewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;
/**
 * 解决ScrollView与ListView滑动冲突的问题
 * @author thank
 *
 */
public class IExpandablelistview extends ExpandableListView {  
  
    public IExpandablelistview(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
        MeasureSpec.AT_MOST);  
        super.onMeasure(widthMeasureSpec, expandSpec);  
    }  
}  