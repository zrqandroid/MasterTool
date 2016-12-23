package com.maowubian.tools.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Dimension;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Adapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.maowubian.tools.R;
import com.maowubian.tools.UnitUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhuruqiao on 2016/11/23.
 */

public class SlidingScaleTitleView extends HorizontalScrollView {


    private List<String> titles = new ArrayList<>();

    private String placeHolder = "";

    private int padding;

    private int childViewWith;

    private Context mContext;

    private int color = 0x000000;

    private int textSize = 10;

    private float textAlpha = 0.5f;

    private float textScale = 1.4f;

    private boolean isFirstChild = true;

    private static final int VISIBLE_TITLE_COUNT = 3;

    private int oldPosiOffsetPixels = 0;

    public interface OnScrollChangeListener {

        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }

    private OnScrollChangeListener onScrollChangeListener;

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {

        this.onScrollChangeListener = onScrollChangeListener;

    }

    public SlidingScaleTitleView(Context context) {
        super(context);
        init(null, null, 0);
    }


    public SlidingScaleTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr);
    }

    public SlidingScaleTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs, 0);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlidingScaleTitleView);
            try {
                color = typedArray.getColor(R.styleable.SlidingScaleTitleView_text_color, color);
                textSize = typedArray.getDimensionPixelOffset(R.styleable.SlidingScaleTitleView_text_size, textSize);
                textScale = typedArray.getFloat(R.styleable.SlidingScaleTitleView_select_text_scale, textScale);
                textAlpha = typedArray.getFloat(R.styleable.SlidingScaleTitleView_un_select_text_alpha, textAlpha);
            } finally {
                typedArray.recycle();
            }
        }
        mContext = getContext();
        padding = getPaddingLeft() + getPaddingRight();
        setHorizontalScrollBarEnabled(false);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setTitles(final List<String> titles) {

        post(new Runnable() {
            @Override
            public void run() {
                SlidingScaleTitleView.this.titles.add(placeHolder);
                SlidingScaleTitleView.this.titles.add(placeHolder);
                SlidingScaleTitleView.this.titles.addAll(titles);
                SlidingScaleTitleView.this.titles.add(placeHolder);
                SlidingScaleTitleView.this.titles.add(placeHolder);
                addChildren();
            }
        });

    }

    private LinearLayout contentLayout;

    private void addChildren() {
        childViewWith = getWidth() / (VISIBLE_TITLE_COUNT * 2 - 1);
        contentLayout = new LinearLayout(mContext);
        contentLayout.setOrientation(LinearLayout.HORIZONTAL);
        contentLayout.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        for (String title : titles) {
            TextView childView = getTextView(title);
            contentLayout.addView(childView);
        }
        addView(contentLayout);


    }

    @NonNull
    private TextView getTextView(String title) {
        TextView childView = new TextView(mContext);
        if (!TextUtils.isEmpty(title) && isFirstChild) {
            childView.setScaleX(textScale);
            childView.setScaleY(textScale);
            isFirstChild = false;
        } else {
            childView.setAlpha(textAlpha);
        }
        childView.setLayoutParams(new LinearLayout.LayoutParams(childViewWith, ViewGroup.LayoutParams.MATCH_PARENT));
        childView.setText(title);
        childView.setTextColor(color);
        childView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        childView.setGravity(Gravity.CENTER);
        return childView;
    }

    public void setViewPagerAdapter(ViewPager adapter) {

        adapter.setOnPageChangeListener(pageChangeListener);

    }


    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (onScrollChangeListener != null) {
                onScrollChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            if (positionOffsetPixels != 0) {
                float alphaX = 1 - (1 - textAlpha) * positionOffset;
                float alphaY = textAlpha + (1 - textAlpha) * positionOffset;
                if (positionOffsetPixels - oldPosiOffsetPixels > 5) {
                    float i = textScale - (textScale - 1) * positionOffset;
                    float j = 1 + (textScale - 1) * positionOffset;
                    View behind = contentLayout.getChildAt(position + VISIBLE_TITLE_COUNT);
                    View self = contentLayout.getChildAt(position + VISIBLE_TITLE_COUNT - 1);
                    self.setScaleX(i);
                    self.setScaleY(i);
                    self.setAlpha(alphaX);
                    behind.setScaleX(j);
                    behind.setScaleY(j);
                    behind.setAlpha(alphaY);

                } else if (positionOffsetPixels - oldPosiOffsetPixels < -5) {
                    float i = 1 + (textScale - 1) * (1 - positionOffset);
                    float j = textScale - (textScale - 1) * (1 - positionOffset);
                    View front = contentLayout.getChildAt(position + VISIBLE_TITLE_COUNT - 1);
                    front.setScaleX(i);
                    front.setScaleY(i);
                    front.setAlpha(alphaX);
                    View self = contentLayout.getChildAt(position + VISIBLE_TITLE_COUNT);
                    self.setScaleX(j);
                    self.setScaleY(j);
                    self.setAlpha(alphaY);

                }
            }
            SlidingScaleTitleView.this.scrollTo((int) Math.floor(childViewWith * positionOffset) + position * childViewWith, 0);
            oldPosiOffsetPixels = positionOffsetPixels;
        }

        @Override
        public void onPageSelected(int position) {
            if (onScrollChangeListener != null) {
                onScrollChangeListener.onPageSelected(position);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (onScrollChangeListener != null) {
                onScrollChangeListener.onPageScrollStateChanged(state);
            }

        }
    };
}
