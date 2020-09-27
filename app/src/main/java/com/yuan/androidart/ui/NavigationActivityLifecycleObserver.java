package com.yuan.androidart.ui;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class NavigationActivityLifecycleObserver implements LifecycleObserver {
    private static final String TAG =NavigationActivityLifecycleObserver.class.getSimpleName();
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResum(){
        Log.d(TAG,"onResum()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreateActivity (){
        Log.d(TAG,"onCreateActivity()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        Log.d(TAG,"onStart()");


    }


}
