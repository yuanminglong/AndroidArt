package com.yuan.androidart.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.yuan.androidart.R;

/**
 * @ProjectName: AndroidArt
 * @Desc:
 * @Author: budaye
 * @Date: 2021/6/18
 */
public class QQStepView extends View {
    private static final String TAG = "QQStepView";
    private int mMaxSteps ;
    private int mCurrentCount;
    private float mStepNumberTextSize;
    private float mBarWidth;
    private int mBarColor;
    private int mStepsColor;
    private int mStepTextColor;
    private Paint mTextPaint;
    private Paint mBaseBarPaint;
    private Paint mStepsBarPaint;


    public QQStepView(Context context) {
        this(context,null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        mMaxSteps = typedArray.getInteger(R.styleable.QQStepView_maxSteps,100000);
        mCurrentCount = typedArray.getInteger(R.styleable.QQStepView_currentSteps, 0);
        mBarColor = typedArray.getColor(R.styleable.QQStepView_barColor,Color.BLUE);
        mStepsColor = typedArray.getColor(R.styleable.QQStepView_stepsColor,Color.RED);
        mStepTextColor = typedArray.getColor(R.styleable.QQStepView_stepsNumberColor,Color.BLACK);
        mStepNumberTextSize = typedArray.getDimension(R.styleable.QQStepView_stepsNumberSize,dp2px(12));
        mBarWidth = typedArray.getDimension(R.styleable.QQStepView_barWidth,dp2px(10));
        typedArray.recycle();
        initBaseBarPaint();
        initStepsPaint();
        iniStepNumberTextPaint();
    }

    private void iniStepNumberTextPaint() {
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(mStepTextColor);
        mTextPaint.setTextSize(mStepNumberTextSize);
    }

    private void initStepsPaint() {
        mStepsBarPaint = new Paint();
        mStepsBarPaint.setAntiAlias(true);
        mStepsBarPaint.setStyle(Paint.Style.STROKE);
        mStepsBarPaint.setColor(mStepsColor);
        mStepsBarPaint.setStrokeWidth(mBarWidth);
        mStepsBarPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void initBaseBarPaint() {
        mBaseBarPaint = new Paint();
        mBaseBarPaint.setAntiAlias(true);
        mBaseBarPaint.setStyle(Paint.Style.STROKE);
        mBaseBarPaint.setColor(mBarColor);
        mBaseBarPaint.setStrokeWidth(mBarWidth);
        mBaseBarPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private float dp2px (float dpValue){
        return getContext().getResources().getDisplayMetrics().density * dpValue + 0.5f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (MeasureSpec.AT_MOST == widthMode && MeasureSpec.AT_MOST == heightMode){
            width = (int) dp2px(20);
            height = (int) dp2px(20);
        }else {
            int temp = width > height? height :width;
            width = temp;
            height = temp;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBaseBar(canvas);
        drawStepsBar(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        Rect rect = new Rect();
        String text = String.valueOf(mCurrentCount);
        mTextPaint.getTextBounds(text,0,text.length(),rect);
        canvas.drawText(text,getWidth()/2-rect.width()/2,getHeight()/2-rect.height()/2,mTextPaint);
    }

    private void drawStepsBar(Canvas canvas) {
        float percent =(float)mCurrentCount/mMaxSteps;
        Log.d(TAG,"current percent is :"+ percent);
        float sweepAngle =percent  * 270;
        Log.d(TAG,"sweepAngle percent is :"+ sweepAngle);
        float x = mBarWidth/2;
        RectF rectF = new RectF(x,x,canvas.getWidth()-x,canvas.getHeight());
        canvas.drawArc(rectF,135,sweepAngle,false,mStepsBarPaint);
    }

    private void drawBaseBar(Canvas canvas) {
        float x = mBarWidth/2;
        RectF rectF = new RectF(x,x,canvas.getWidth()-x,canvas.getHeight());
        canvas.drawArc(rectF,135,270,false,mBaseBarPaint);
    }

    public void setmCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        postInvalidate();
    }
}
