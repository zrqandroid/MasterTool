package com.maowubian.tools.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by zhuruqiao on 2016/12/15.
 */

public class PullRefreshScrollView extends ScrollView {

    /**
     * 简单的构造方法 用于代码创建View
     * @param context
     */
    public PullRefreshScrollView(Context context) {
        super(context);
    }

    /**
     * 当用加载器加载布局时，此方法将被调用。被加载时 style =0；
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public PullRefreshScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PullRefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
