package com.yuan.androidart.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.yuan.androidart.R;

/**
 * @ProjectName: AndroidArt
 * @Desc:
 * @Author: budaye
 * @Date: 2021/6/26
 */
public class MyImageView extends View {
    private static final int HEIGHT = 19;
    private static final int WIDTH = 19;
    final int COUNT = (WIDTH + 1) * (HEIGHT + 1);
    private Bitmap topBitmap;
    private Bitmap lowBitmap;
    private Paint paint;
    private Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private float[] verts;

    public MyImageView(Context context) {
        this(context,null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPait();
    }

    private void cacleVert() {


        // 实例化数组
        verts = new float[COUNT * 2];

        /*
         * 生成各个交点坐标
         */
        int index = 0;
        float multiple = topBitmap.getWidth();
        for (int y = 0; y <= HEIGHT; y++) {
            float fy = topBitmap.getHeight() * y / HEIGHT;
            for (int x = 0; x <= WIDTH; x++) {
                float fx = topBitmap.getWidth() * x / WIDTH + ((HEIGHT - y) * 1.0F / HEIGHT * multiple);
                setXY(fx, fy, index);
                index += 1;
            }

        }
    }


    private void setXY(float fx, float fy, int index) {
        verts[index * 2 + 0] = fx;
        verts[index * 2 + 1] = fy;
    }

    private void initPait() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
/*        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
        paint.setColorFilter(porterDuffColorFilter);
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
        paint.setXfermode(xfermode);*/

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        topBitmap = getBitMap(getContext(),width/2,height,R.drawable.test_custom_view);
        lowBitmap = getBitMap(getContext(),width,height,R.drawable.pohoto);
        cacleVert();
    }

    private Bitmap getBitMap(Context context, int width,int height,int resourceId) {
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(),resourceId,options);
        int bitmapWidth =  options.outWidth;
        float scal = (float) bitmapWidth/width;
        float iheight = options.outHeight / scal;
        options.inJustDecodeBounds= false;
        bitmap = BitmapFactory.decodeResource(context.getResources(),resourceId,options);
        bitmap = Bitmap.createScaledBitmap(bitmap,width,(int) iheight,true);//BitmapFactory.decodeResource(context.getResources(),R.drawable.test_custom_view,options);
        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
       //canvas.drawBitmap(topBitmap,0,0,paint);
       //canvas.drawRect(0,0,getWidth(),getHeight(),paint);
        canvas.drawBitmapMesh(topBitmap,WIDTH,HEIGHT,verts,0,null,0,null);
    }
}
