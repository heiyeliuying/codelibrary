package com.icode.library.widgets.viewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/**
 * 滚动ListView.解决与ScrollView的冲突问题
 * @author thank
 *
 */
public class IScrollListView  extends ListView{
	 
    public IScrollListView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
        MeasureSpec.AT_MOST);  
        super.onMeasure(widthMeasureSpec, expandSpec);  
    }  
}
