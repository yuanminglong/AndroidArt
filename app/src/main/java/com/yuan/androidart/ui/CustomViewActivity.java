package com.yuan.androidart.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import com.yuan.androidart.R;
import com.yuan.androidart.view.QQStepView;

public class CustomViewActivity extends AppCompatActivity {
    private QQStepView qqStepView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        initView();
    }

    private void initView() {
        //qqStepView = findViewById(R.id.qq_step_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
/*        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,90000);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(animation -> {
            int intAnimation = (int) animation.getAnimatedValue();
            qqStepView.setmCurrentCount(intAnimation);
        });
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.start();*/
    }
}