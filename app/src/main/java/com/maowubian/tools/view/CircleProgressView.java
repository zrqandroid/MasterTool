package com.maowubian.tools.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.maowubian.tools.R;


/**
 * Created by zhuruqiao on 2016/11/30.
 */

public class CircleProgressView extends View {

    private Context mContext;

    private float progressTextSize = 0.2f;
    private int progressWidth = 10;
    private int progressTextColor = 0XFFFFFF;
    private int strokeColor = 0XFFFFFF;
    private int backgroundColor = 0XFFFFFF;
    private int progressColor = 0XFFFFFF;
    private int centerColor = 0XFFFFFF;
    private int progress = 15;
    private int strokeWidth = 2;


    private Paint storkePaint = new Paint();
    private Paint progressPaint = new Paint();
    private Paint centerCirclePaint = new Paint();

    private Paint progressTextPaint = new Paint();


    private int width;

    private int height;

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);


    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);

        try {
            progressTextSize = typedArray.getFloat(R.styleable.CircleProgressView_cpv_text_size, progressTextSize);
            progressTextColor = typedArray.getColor(R.styleable.CircleProgressView_cpv_text_color, progressTextColor);
            strokeColor = typedArray.getColor(R.styleable.CircleProgressView_cpv_stroke_color, strokeColor);
            backgroundColor = typedArray.getColor(R.styleable.CircleProgressView_cpv_bg, backgroundColor);
            progressColor = typedArray.getColor(R.styleable.CircleProgressView_cpv_progress_color, progressColor);
            progress = typedArray.getColor(R.styleable.CircleProgressView_cpv_progress, progress);
            centerColor = typedArray.getColor(R.styleable.CircleProgressView_cpv_center_color, centerColor);
            strokeWidth = typedArray.getDimensionPixelOffset(R.styleable.CircleProgressView_cpv_stroke_width, strokeWidth);
            progressWidth = typedArray.getDimensionPixelOffset(R.styleable.CircleProgressView_cpv_progress_width, progressWidth);

        } finally {
            typedArray.recycle();
        }

        storkePaint.setColor(strokeColor);
        storkePaint.setAntiAlias(true);
        storkePaint.setStyle(Paint.Style.STROKE);
        storkePaint.setStrokeWidth(strokeWidth);

        progressPaint.setColor(progressColor);
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.STROKE);
        //以圆环为半径，向外扩张strokeWidth
        progressPaint.setStrokeWidth(progressWidth);

        centerCirclePaint.setColor(centerColor);
        centerCirclePaint.setAntiAlias(true);
        centerCirclePaint.setStyle(Paint.Style.FILL);

        progressTextPaint.setColor(progressTextColor);
        progressTextPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        loadViewSize();
        //画背景
        canvas.drawColor(backgroundColor);

        //画进度的外边框
        canvas.drawCircle(width / 2, height / 2, getRadius() * 2 / 3, storkePaint);

        RectF rect = new RectF(width / 2 - getRadius() * 2 / 3 + progressWidth / 2, height / 2 - getRadius() * 2 / 3 + progressWidth / 2, width / 2 + getRadius() * 2 / 3
                - progressWidth / 2, height / 2 + getRadius() * 2 / 3 - progressWidth / 2);
        canvas.drawArc(rect, 0f, 360f, false, progressPaint);
        canvas.drawCircle(width / 2, height / 2, (getRadius()) * 2 / 3 - progressWidth / 2 - strokeWidth, centerCirclePaint);
        String s = progress + "%";
        progressTextPaint.setTextSize(progressTextSize * getRadius());
        float textLength = progressTextPaint.measureText(s);
        Paint.FontMetrics fontMetrics = progressTextPaint.getFontMetrics();
        float textHeight = Math.abs(fontMetrics.ascent);
        canvas.drawText(s, width / 2 - textLength / 2, height / 2 +textHeight / 2, progressTextPaint);

        super.onDraw(canvas);
    }

    private int getRadius() {
        return width <= height ? width / 2 : height / 2;
    }

    private void loadViewSize() {
        if (width == 0) {
            width = getMeasuredWidth();
        }
        if (height == 0) {
            height = getMeasuredHeight();
        }
    }

    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        } else if (progress > 100) {
            progress = 100;
        }
        this.progress = progress;
        postInvalidate();
    }


}
