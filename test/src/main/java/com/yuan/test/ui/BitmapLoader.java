package com.yuan.test.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.zhuozhuo.remotetestlib.DataCenter;
import io.zhuozhuo.remotetestlib.Message;
import io.zhuozhuo.remotetestlib.Size;

public class BitmapLoader {
    public static String TAG = BitmapLoader.class.getSimpleName();
    public static BitmapLoader  instance= null;
    private int maxMemory = (int)(Runtime.getRuntime().totalMemory() / 1024);
    private int cacheSize = maxMemory / 8;
    private static ExecutorService executorService =null;
    private static LruCache<String, Bitmap> mMemoryCache = null;
    private BitmapLoader() {
        this.mMemoryCache =new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
        executorService = Executors.newCachedThreadPool();
    }
    public static BitmapLoader getInstance(){
        if (instance==null){
            synchronized (BitmapLoader.class){
                if (instance==null){
                    instance=new BitmapLoader();
                }
            }
        }
        return instance;

    }

    public void loadBitmap(final Message msg, @NonNull final LoadBitmapListener loadBitmapListener){
        if (loadBitmapListener==null) return  ;
        executorService.execute(new Runnable() {
            @Override
            public void run() {
               Bitmap bitmap =  loadBitmap(msg) ;
               loadBitmapListener.finished(bitmap);
            }
        });
    }

    private Bitmap loadBitmap(Message msg){
        Bitmap bitmap = null;

        bitmap = mMemoryCache.get(msg.getContent());
        if (bitmap==null){
            byte[] bytes= DataCenter.loadImage__NotAllowMainThread(msg.getContent());
            bitmap=decodeSampleBitmapFromBytes(bytes,(int) Size.message_image_max_width,(int)Size.message_image_max_height);
            mMemoryCache.put(msg.getContent(),bitmap);
        }
        return bitmap;
    }
    public Bitmap getBitmapFromCache(Message msg){
        return mMemoryCache.get(msg.getContent());
    }

    public static Bitmap decodeSampleBitmapFromBytes(byte[] data ,int needWidth,int needHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        options.inSampleSize =  calculateInSampleSize(options,needWidth,needHeight);
        options.inJustDecodeBounds = false;
        Bitmap bitmap=  BitmapFactory.decodeByteArray(data, 0, data.length,options);
        return zoomImg(bitmap,needWidth,needHeight);
    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height/2 ;
            final int halfWidth = width/2 ;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight){
        int width = bm.getWidth();
        int height = bm.getHeight();
        if (bm.getWidth()<=newWidth&&bm.getHeight()<=newHeight){
            return bm;
        }
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        float scale = Math.min(scaleHeight,scaleWidth);
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    public  interface LoadBitmapListener{

         void finished(Bitmap bitmap);

    }


}
