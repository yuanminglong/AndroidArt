package com.yuan.androidart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/**
 * @ProjectName: AndroidArt
 * @Desc:
 * @Author: budaye
 * @Date: 2021/9/4
 */
public class SlideMenu extends HorizontalScrollView {
    private static final String TAG = SlideMenu.class.getSimpleName();
    private float mMemuRatio;
    private View mMenuLayout;
    private View mMainContentLayout;
    float mActionDownX = 0;
    float mActionDownY = 0;
    boolean mMenuIsOpen =  true;
    public SlideMenu(Context context) {
        this(context,null);
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMemuRatio = 0.6f;
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        ViewGroup container = (ViewGroup) this.getChildAt(0);
        int childCount = container.getChildCount();
        if (childCount != 2) {
            Log.d(TAG,"there");
            return;
        }
        mMenuLayout = container.getChildAt(0);
        mMainContentLayout = container.getChildAt(1);
        ViewGroup.LayoutParams menuLayoutLayoutParams = mMenuLayout.getLayoutParams();
        menuLayoutLayoutParams.width = (int) (mMemuRatio * getScreenWidth());
        mMainContentLayout.getLayoutParams().width = (int) getScreenWidth();
    }

    private float getScreenWidth() {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:
                float ActionUpX = ev.getX() - mActionDownX;
                Log.d(TAG, "ActionUpX :" + ActionUpX + "| mMenuIsOpen :"+ mMenuIsOpen);

                float ActionUpY = ev.getY();
                int width = mMenuLayout.getWidth();
                if ( !mMenuIsOpen){
                    if (ActionUpX> width/2){
                        openMenu();
                    }else {
                        closeMenu();
                    }
                }else {
                    if (ActionUpX<0 && Math.abs(ActionUpX) > width /2){
                        closeMenu();
                    }else {
                       openMenu();
                    }
                }
                 mActionDownX = 0;
                 mActionDownY = 0;
                 return true;
            case MotionEvent.ACTION_DOWN:
                mActionDownX = ev.getX();
                mActionDownY = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float i = ((float) getScrollX()/(float) mMenuLayout.getWidth()) * 0.3f;
                Log.d(TAG,"scale:" + i + "|"+ "scrollX:"+getScrollX() + "| menulayou with :"+mMenuLayout.getWidth());
                mMainContentLayout.setScaleX(i + 0.7f);
                mMainContentLayout.setScaleY(i + 0.7f);
                break;

        }
        return super.onTouchEvent(ev);
    }

    private void  openMenu(){
        mMenuIsOpen = true;
        smoothScrollTo(0,0);
        mMainContentLayout.setScaleX(0.7f);
        mMainContentLayout.setScaleY(0.7f);

    }

    private void closeMenu(){
        mMenuIsOpen = false;
        smoothScrollTo(mMenuLayout.getWidth(),0);
        mMainContentLayout.setScaleX(1f);
        mMainContentLayout.setScaleY(1f);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
}
