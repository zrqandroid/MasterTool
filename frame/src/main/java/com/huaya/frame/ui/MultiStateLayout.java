package com.huaya.frame.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.ViewUtils;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by zhuruqiao on 2017/3/10.
 * e-mail:563325724@qq.com
 */

public class MultiStateLayout extends ViewGroup {

    public static final int STATE_LOADING = 0;

    public static final int STATE_EMPTY = 1;

    public static final int STATE_ERROR = 2;

    public static final int STATE_CONTENT = 4;

    public static final int DEFAULT_CHILD_GRAVITY = Gravity.TOP | Gravity.START;

    private int mCurrentState = STATE_CONTENT;

    private int mWillState;

    private final SparseArray<View> mStateCache = new SparseArray<>();

    private boolean hasDefined = false;


    public MultiStateLayout(Context context) {
        super(context);
    }

    /**
     * 将布局替换到activity
     *
     * @param activity
     * @return
     */
    public static MultiStateLayout attachToActivity(Activity activity) {
        //获取到根布局
        ViewGroup content = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        //获取到根布局的父布局
        ViewGroup parent = (ViewGroup) content.getParent();
        //创建混合状态的布局
        MultiStateLayout multiStateLayout = new MultiStateLayout(activity);
        //获取到根布局的index
        int index = parent.indexOfChild(content);
        //移除根布局
        parent.removeView(content);
        //获取到根布局的布局参数
        LayoutParams params = content.getLayoutParams();
        multiStateLayout.attachLayout(STATE_CONTENT, content);
        //替换布局
        parent.addView(multiStateLayout, index, params);
        return multiStateLayout;

    }

    /**
     * 用MulitiStatelayout去替换view
     *
     * @param target
     * @return
     */
    public static MultiStateLayout attachToTarget(View target) {
        final Context context = target.getContext();
        ViewGroup parent = (ViewGroup) target.getParent();

        MultiStateLayout multiStateLayout = new MultiStateLayout(context);

        int index = parent.indexOfChild(target);

        parent.removeView(target);
        LayoutParams params = target.getLayoutParams();
        multiStateLayout.attachLayout(STATE_CONTENT, target);
        parent.addView(multiStateLayout, index, params);
        return multiStateLayout;

    }

    /**
     * 创建MultiStateLayout 去替换原有布局
     *
     * @param content
     * @return
     */
    public static MultiStateLayout newInstance(View content) {
        Context context = content.getContext();
        MultiStateLayout multiStateLayout = new MultiStateLayout(context);
        multiStateLayout.attachLayout(STATE_CONTENT, content);
        multiStateLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        return multiStateLayout;
    }


    public synchronized MultiStateLayout attachLayout(int state, View childView) {
        if (hasDefined) {
            throw new IllegalArgumentException("attachLayout must before compile");
        }
        View child = mStateCache.get(state);
        if (child != null) {
            throw new IllegalArgumentException("had attach layout of state =" + state);
        }
        if (childView == null) {
            return this;
        }
        addView(childView, childView.getLayoutParams());
        mStateCache.put(state, childView);
        return this;
    }

    private synchronized MultiStateLayout attachLayout(int state, ViewGroup childView) {
        if (hasDefined) {
            throw new IllegalArgumentException("attachLayout must before compile");
        }
        View child = mStateCache.get(state);
        if (child != null) {
            throw new IllegalArgumentException("had attach layout of state =" + state);
        }
        if (childView == null) {
            return this;
        }
        addView(childView, childView.getLayoutParams());
        mStateCache.put(state, childView);
        return this;

    }

    /**
     * 根据状态从集合中删除对应状态的view
     *
     * @param state
     */
    public synchronized void detachLayout(int state) {
        View child = mStateCache.get(state);
        if (child == null) {
            return;
        }
        mStateCache.delete(state);
    }

    /**
     * 编译
     *
     * @return
     */
    public synchronized StateController compile() {
        hasDefined = true;
        mCurrentState = STATE_CONTENT;
        int size = mStateCache.size();
        for (int i = 0; i < size; i++) {
            int state = mStateCache.keyAt(i);
            View view = mStateCache.valueAt(i);
            if (state == mCurrentState) {
                ViewUtils.showView(view);
                continue;
            }
            ViewUtils.hideView(view);
        }
        return mStateController;


    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    /**
     * 控制器 控制不同view的显示
     */
    public interface StateController {

        void showLoading(boolean animate);

        void showContent(boolean animate);

        void showEmpty(boolean animate);

        void showError(boolean animate);

        void showState(int state, boolean animate);

        int getCurrentState();

        View getStateView(int state);

    }

    final StateController mStateController = new StateController() {
        @Override
        public void showLoading(boolean animate) {
            checkViewState(STATE_LOADING, animate);
        }

        @Override
        public void showContent(boolean animate) {
            checkViewState(STATE_CONTENT, animate);

        }

        @Override
        public void showEmpty(boolean animate) {
            checkViewState(STATE_EMPTY, animate);

        }

        @Override
        public void showError(boolean animate) {
            checkViewState(STATE_ERROR, animate);
        }

        @Override
        public void showState(int state, boolean animate) {
            checkViewState(state, animate);
        }

        @Override
        public int getCurrentState() {
            return mCurrentState;
        }

        @Override
        public View getStateView(int state) {
            return mStateCache.get(state);
        }
    };


    void checkViewState(int willState, boolean animate) {
        mWillState = willState;
        int size = mStateCache.size();

        for (int i = 0; i < size; i++) {
            int state = mStateCache.keyAt(i);
            if (state == willState || state == mCurrentState) {
                continue;
            }
            View view = mStateCache.valueAt(i);
            ViewUtils.hideView(view);
        }
        if (willState == mCurrentState) {
            return;
        }

        if (animate) {
            View view = mStateCache.get(mCurrentState);
            if (view instanceof AnimateView) {
                AnimateView v = (AnimateView) view;
                v.setCallback(mAnimateViewDismissCallback);
                ViewUtils.hideViewAnimated(mStateCache.get(mCurrentState));
            } else {
                ViewUtils.hideViewAnimated(mStateCache.get(mCurrentState));
                ViewUtils.showViewAnimated(mStateCache.get(willState));
            }

        } else {
            ViewUtils.hideView(mStateCache.get(mCurrentState));
            ViewUtils.showView(mStateCache.get(willState));
        }
        mCurrentState == willState;

    }
    private final Callback mAnimateViewDismissCallback = new Callback() {
        @Override
        public void call() {
            ViewUtils.showView(mStateCache.get(mWillState));
        }
    };

}
