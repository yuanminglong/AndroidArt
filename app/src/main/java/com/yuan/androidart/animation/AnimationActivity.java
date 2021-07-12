package com.yuan.androidart.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuan.androidart.R;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonStartAnimal,buttonAddView,removeView;
    ImageView imageViewAnination;
    LinearLayout linearLayoutMainContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        buttonStartAnimal = findViewById(R.id.btn_test_animation);
        buttonStartAnimal.setOnClickListener(this);
        //imageViewAnination = findViewById(R.id.iv_animation);
        //imageViewAnination.setOnClickListener(this);
        linearLayoutMainContainer = findViewById(R.id.ll_container);
        buttonAddView = findViewById(R.id.btn_add_view);
        removeView = findViewById(R.id.btn_remove_view);
        removeView.setOnClickListener(this);
        buttonAddView.setOnClickListener(this);

        initViewGroupAnimation();

    }

    private void initViewGroupAnimation() {
        ObjectAnimator appearing = ObjectAnimator.ofFloat(null,"rotationY",0,360);
        appearing.setDuration(10000);
        ObjectAnimator transtion = ObjectAnimator.ofFloat(null,"translationY",1000,0);
        transtion.setDuration(10000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(10000);
        animatorSet.play(appearing).with(transtion);
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setDuration(10000);
        layoutTransition.setAnimator(LayoutTransition.APPEARING,appearing);



        linearLayoutMainContainer.setLayoutTransition(layoutTransition);
    }


    private void testAnimation(){
        ObjectAnimator objectAnimatorx = ObjectAnimator.ofFloat(imageViewAnination,"scaleX",1f,2f);
        objectAnimatorx.setDuration(1000);
        objectAnimatorx.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimatorx.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimatorx.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator objectAnimatory = ObjectAnimator.ofFloat(imageViewAnination,"scaleY",1f,2f);
        objectAnimatory.setDuration(1000);
        objectAnimatory.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimatory.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet animatorSet = new AnimatorSet();
        objectAnimatory.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.play(objectAnimatorx).with(objectAnimatory);
        animatorSet.start();

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                return null;
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_test_animation:
                //testAnimation();
                //rotationAnimation(imageViewAnination);
                break;
            case R.id.btn_add_view:
                addView();
                break;
            case R.id.btn_remove_view:
                removeView();
                break;
        }
    }

    private void rotationAnimation(View view){
        view.setPivotX(0);
        view.setPivotY(0);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"rotation",0,90);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setRepeatCount(10);
        objectAnimator.start();
    }

    private void addView(){
        TextView textView = new TextView(this);
        textView.setText("1234512345678765432345fdsdf1234512345678765432345fdsdf");
        linearLayoutMainContainer.addView(textView);
    }

    private void removeView(){
        int childCount = linearLayoutMainContainer.getChildCount();
        if (childCount>0){
            linearLayoutMainContainer.removeView(linearLayoutMainContainer.getChildAt(0));
        }

    }
}