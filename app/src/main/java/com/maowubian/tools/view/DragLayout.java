package com.maowubian.tools.view;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by zhuruqiao on 2017/1/9.
 */

public class DragLayout extends FrameLayout {

    private Context mContext;

    private ViewDragHelper viewDragHelper;

    private GestureDetectorCompat gestureDetectorCompat;


    private ViewGroup mLeftContent;

    private ViewGroup mMainContent;

    public DragLayout(Context context) {
        this(context, null);
    }


    public DragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        mContext = context;
        viewDragHelper = ViewDragHelper.create(this, callBack);
        gestureDetectorCompat = new GestureDetectorCompat(context, gestureListener);


    }

    private ViewDragHelper.Callback callBack = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (changedView==mMainContent){
                layoutView


            }

        }
    };

    private SimpleOnGestureListener gestureListener = new SimpleOnGestureListener() {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return true;
        }
    };

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean flag = gestureDetectorCompat.onTouchEvent(ev);

        //拦截事件
        return viewDragHelper.shouldInterceptTouchEvent(ev) & flag;
    }

    /**
     * 填充结束时获得两个子布局的引用
     */
    @Override
    protected void onFinishInflate() {

        int childCount = getChildCount();
        // 必要的检验
        if (childCount < 2) {
            throw new IllegalStateException(
                    "You need two childrens in your content");
        }

        if (!(getChildAt(0) instanceof ViewGroup)
                || !(getChildAt(1) instanceof ViewGroup)) {
            throw new IllegalArgumentException(
                    "Your childrens must be an instance of ViewGroup");
        }

        mLeftContent = (ViewGroup) getChildAt(0);
        mMainContent = (ViewGroup) getChildAt(1);
    }

}
