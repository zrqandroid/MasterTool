package com.maowubian.tools.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by zhuruqiao on 2017/1/9.
 * e-mail:563325724@qq.com
 */

public class MainContentView extends RelativeLayout {

    private Context mContext;

    private DragLayout mDragLayout;

    public MainContentView(Context context) {
        this(context, null);
    }

    public MainContentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mDragLayout.getStatus() == DragLayout.Status.Close) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mDragLayout.getStatus() == DragLayout.Status.Close) {
            return super.onTouchEvent(event);
        } else {
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_UP) {
                mDragLayout.close();
            }
            return true;
        }
    }

    public void setmDragLayout(DragLayout mDragLayout) {
        this.mDragLayout = mDragLayout;
    }
}
