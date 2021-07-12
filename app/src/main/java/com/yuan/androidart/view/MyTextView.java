package com.yuan.androidart.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.yuan.androidart.R;

public class MyTextView extends View {
    private static final String TAG = MyTextView.class.getSimpleName();
    String mText="";
    Paint paint;
    float mTestSzie;
    private int progress = 0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTextView(Context context) {
        this(context,null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyTextView);
        mText = typedArray.getString(R.styleable.MyTextView_myText);
        Log.d(TAG,"mytext is :"+ mText);
        mTestSzie = typedArray.getDimension(R.styleable.MyTextView_myTextSize,30);
        Log.d(TAG,"text size  is :"+ mTestSzie);
        initPaint();
        typedArray.recycle();
    }
    private void initPaint(){
        paint= new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(mTestSzie);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height =  MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG,"text is :"+ mText);
        if (widthMode == MeasureSpec.AT_MOST){
            width = (int) paint.measureText(mText,0,mText.length());
        }
        if (heightMode == MeasureSpec.AT_MOST){
            height = (int) (paint.descent()-paint.ascent());
        }
        Log.d(TAG,"view width is :"+ width+"|view height is :"+height);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG,"canvas height is :"+canvas.getHeight());
        Log.d(TAG,"canvas width is :"+canvas.getWidth());
        canvas.save();
        //progress = getWidth()/2;
        paint.setColor(Color.RED);
        float baseLine = (Math.abs(paint.ascent())- Math.abs(paint.descent()))/2;
        canvas.clipRect(0,0,progress,getHeight());
        canvas.drawText(mText,0,canvas.getHeight()/2+baseLine,paint);
        canvas.restore();
        canvas.save();
        paint.setColor(Color.BLUE);
        canvas.clipRect(progress,0,getWidth(),getHeight());
        canvas.drawText(mText,0,canvas.getHeight()/2+baseLine,paint);
        canvas.restore();
        if (progress<getWidth()){
            progress++;
            invalidate();
        }
    }

}
