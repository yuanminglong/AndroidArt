package com.yuan.androidart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ProjectName: AndroidArt
 * @Desc:
 * @Author: budaye
 * @Date: 2021/6/27
 */
public class CanvasView extends View {
    private static final String TAG = "CanvasView";
    private Paint paint;
    private Path path;
    private int startY = -1;
    private int controlX;
    private boolean controlXFlag = false;
    private static final int XXX = 100;
    public CanvasView(Context context) {
        this(context,null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPath() {
        controlX = 0-XXX;
        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initPath();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1);
    }


    @Override
    protected void onDraw(Canvas canvas) {
/*        if (startY == -1){
            startY = canvas.getHeight() /4;
        }
        if (controlX<0-XXX){
            controlXFlag = true;
        } else if (controlX >getWidth()+XXX) {
            controlXFlag = false;
        }
        controlX = controlXFlag ? controlX + 20 : controlX - 20;
        Log.d(TAG,"controlX" + controlX);
        Log.d(TAG,"startY" + startY);
        path.moveTo(0-XXX,startY);
        path.quadTo(controlX,startY+100,getWidth(),startY);
        path.lineTo(getWidth(),getHeight());
        path.lineTo(0,getHeight());
        path.close();

        Log.d(TAG,"on draw ,path is "+ path);
        canvas.drawPath(path,paint);
        path.reset();
        if (startY< getHeight()){
            startY = startY +2;
            invalidate();
        }*/

        path.reset();
        path.moveTo(100, 100);

// 连接路径到点
        RectF oval = new RectF(100, 100, 200, 200);
        path.arcTo(oval, 0, 90);
        //canvas.drawPath(path,paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(oval,0,90,false,paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(oval,paint);
    }
}
