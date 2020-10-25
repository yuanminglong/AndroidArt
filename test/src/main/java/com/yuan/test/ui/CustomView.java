package com.yuan.test.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import io.zhuozhuo.remotetestlib.Message;

public class CustomView extends AppCompatTextView {
    private static String TAG = "CustomView";
    private Message message;
    private Bitmap bitmap;
    public CustomView(@NonNull Context context) {
        super(context);
    }

    public CustomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (message.getMsgType()==Message.MessageTypeImage){
            getLayoutParams();
           int widthSpec = bitmap!=null ? bitmap.getWidth():756;//MeasureSpec.makeMeasureSpec(message.getImageWidth(),MeasureSpec.EXACTLY);
           int heightSpec = bitmap!=null ? bitmap.getHeight():756;// MeasureSpec.makeMeasureSpec(message.getImageHeight(),MeasureSpec.EXACTLY);
            Log.d(TAG,"onMeasure   width:"+MeasureSpec.getSize(widthSpec)+"-----height:"+MeasureSpec.getSize(heightSpec));
            super.setMeasuredDimension(widthSpec,heightSpec);
           //super.onMeasure(widthSpec,heightSpec);
           return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (message==null||message.getMsgType()==Message.MessageTypeText){
            super.setText(text, type);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG,"width:"+getWidth()+"-----height:"+getHeight());
        if (message.getMsgType()==Message.MessageTypeImage&&bitmap!=null){
            canvas.drawBitmap(bitmap,0,0,getPaint());
            return;
        }
        super.onDraw(canvas);
    }
    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
        postInvalidate();
    }

    public void setMessage(Message message) {
        if (message==null)return;
        this.message = message;
        if (message.getMsgType()==Message.MessageTypeImage){
            postInvalidate();
            return;
        }
        setText(message.getContent());
    }
    public void clean(){
        message=null;
        bitmap=null;
    }
}
