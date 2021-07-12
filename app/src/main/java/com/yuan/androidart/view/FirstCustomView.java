package com.yuan.androidart.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.icu.util.MeasureUnit;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.yuan.androidart.R;


public class FirstCustomView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private static String TAG = FirstCustomView.class.getSimpleName();
    private Paint paint ;
    private int radius= 0;
    float oldX,oldY,newX,newY;
    private Paint.FontMetrics mFontMetrics;
    Rect mRect;
    Path mPath;
    VelocityTracker velocityTracker ;

    private static final String TEXT = "ap爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓p爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓p爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓p爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓p爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓p爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓p爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓p爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓p爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓p爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓p爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓p爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓";
    public FirstCustomView(Context context) {
        super(context);
        inintPaint();
    }

    public FirstCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inintPaint();
    }

    public FirstCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inintPaint();
        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.MyTextView);
    }

    private void inintPaint(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        mRect = new Rect(0,0,500,500);
        mRect.intersect(250,250,500,500);
        mPath = new Path();
        mPath.moveTo(50,50);
        mPath.lineTo(50,100);
        mPath.lineTo(100,100);
        mPath.lineTo(100,50);
        mPath.close();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawCircle(500,500,radius<500?radius:500,paint);
        //canvas.drawRect(mRect,paint);
        canvas.drawPath(mPath,paint);
        canvas.drawRect(new Rect(300,200,400,500),paint);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x  = event.getRawX();
        float y  = event.getRawY();

        if (event.getAction()== MotionEvent.ACTION_MOVE){
            float deltaX  = oldX-x;
            float deltaY  = oldY - y ;
            //setTranslationX(deltaX);
            //setTranslationY(deltaY);
            scrollBy((int)deltaX,(int)deltaY);



            newX = (int) event.getX();
            newY = (int) event.getY();
        }
/*        Log.d(TAG,"scrollX:"+getScrollX());
        Log.d(TAG,"ScrollY:"+getScaleY());
        Log.d(TAG,"oldX:"+oldX);
        Log.d(TAG,"oldY:"+oldY);*/
        oldX = x;
        oldY = y;

        GestureDetector detector = new GestureDetector(getContext(),this);
        detector.setOnDoubleTapListener(this);
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG,"onDown:");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG,"onShowPress:");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG,"onSingleTapUp:");

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG,"onScroll:");
        scrollTo((int )distanceX,(int) distanceY);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG,"onLongPress:");

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG,"onFling:");

        return false;
    }


    @Override
    public void scrollBy(int x, int y) {
        Log.d(TAG,"scrollBy:");

        super.scrollBy(x, y);
    }



    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(TAG,"onSingleTapConfirmed:");

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG,"onDoubleTap:");

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d(TAG,"onDoubleTapEvent:");

        return false;
    }
}
