package com.yuan.androidart.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.yuan.androidart.R;

/**
 * @ProjectName: AndroidArt
 * @Desc:
 * @Author: budaye
 * @Date: 2021/7/12
 */
public class RatingView extends View {
    private static final String TAG = "RatingView";
    private int maxPoint = 100;
    int rating = 50;
    private Paint mPaint;
    int bitmapWidth,bitmapHeight;
    private Shader normalShader,ratingShader;
    public RatingView(Context context) {
        this(context,null);
    }

    public RatingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParam(attrs);
        initPaint();
    }

    private void initParam(AttributeSet attrs) {
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap bitmap = drawableToBitmap(getResources().getDrawable(R.drawable.star_normal));
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
        normalShader  =  new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        bitmap = drawableToBitmap(getResources().getDrawable(R.drawable.star_point));;
        ratingShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mPaint.setShader(normalShader);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = bitmapWidth * 5;
        setMeasuredDimension(width,bitmapHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw width is :"+ getWidth() +" | height is :" +getHeight());
        float splitPoint =  (float) rating/maxPoint * getWidth();
        mPaint.setShader(ratingShader);
        canvas.drawRect(0,0,splitPoint,getHeight(),mPaint);
        mPaint.setShader(normalShader);
        canvas.drawRect(splitPoint,0,getWidth(),getHeight(),mPaint);
    }


    public static final Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap( drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                cacleRating(event);
            case MotionEvent.ACTION_DOWN:
        }
        return true;
    }

    private void cacleRating(MotionEvent event) {
        rating = (int) (event.getX()/getWidth() * maxPoint);
        invalidate();
    }
}
