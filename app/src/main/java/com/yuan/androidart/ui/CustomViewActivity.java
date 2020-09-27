package com.yuan.androidart.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yuan.androidart.R;
import com.yuan.androidart.view.FirstCustomView;

public class CustomViewActivity extends AppCompatActivity {
    FirstCustomView customView;
    int radius =500;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    radius = radius>1 ?--radius:1;
                    Log.d("CustomViewActivity","radius:"+radius);
                    customView.setRadius(radius);
                    if (radius==1){
                        sendEmptyMessageDelayed(1,4);
                    }else{
                        sendEmptyMessageDelayed(0,4);
                    }
                    break;
                case 1:
                    radius = radius<500 ?++radius:500;
                    Log.d("CustomViewActivity","radius:"+radius);
                    customView.setRadius(radius);
                    if (radius==500){
                        sendEmptyMessageDelayed(0,4);
                    }else{
                        sendEmptyMessageDelayed(1,4);
                    }
                    break;
            }


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        customView = findViewById(R.id.firstCustomView);
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        //handler.sendEmptyMessage(0);
    }
}