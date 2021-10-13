package com.yuan.androidart.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

import com.yuan.androidart.R;

/**
 * @ProjectName: AndroidArt
 * @Desc:
 * @Author: budaye
 * @Date: 2021/9/13
 */
public class DragableView extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    private String TAG = DragableView.class.getSimpleName();

    public DragableView(@NonNull Context context) {
        this(context ,null);
    }

    public DragableView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    View mMenuView;
    public DragableView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewDragHelper = ViewDragHelper.create(this, 1f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                Log.d(TAG,"pointerId:" + pointerId);
                return true;
            }

            @Override
            public int getViewHorizontalDragRange(@NonNull View child) {
                return super.getViewHorizontalDragRange(child);
            }

            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                if (child.getId() != R.id.rv_list){
                    return 0;
                }
                if (top<0){
                    return 0;
                }else if (top > mMenuView.getHeight()){
                    return mMenuView.getHeight();
                }else {
                   return top;
                }
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                return 0;
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, 0, 0);
                int top = releasedChild.getTop();
                if (top > mMenuView.getHeight()/2){
                    mViewDragHelper.smoothSlideViewTo(releasedChild,0,mMenuView.getHeight());
                }else {
                    mViewDragHelper.smoothSlideViewTo(releasedChild,0,0);
                }
                invalidate();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,event.toString() + "，porinterIndex:"+ event.findPointerIndex(event.getPointerId(event.getActionIndex())));
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)){
            invalidate();
        }
        super.computeScroll();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
    }
}
