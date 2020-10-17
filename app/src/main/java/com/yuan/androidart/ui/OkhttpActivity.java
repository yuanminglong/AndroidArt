package com.yuan.androidart.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.yuan.androidart.databinding.ActivityOkhttpBinding;
import com.yuan.androidart.ui.net.HttpMethod;

import okhttp3.internal.Util;

public class OkhttpActivity extends AppCompatActivity {
    ActivityOkhttpBinding binding;
    private String TAG = OkhttpActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOkhttpBinding.inflate(getLayoutInflater());
        binding.btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        Log.d(TAG,"get net info");
                        final String msg = HttpMethod.getHttpMethod().getTop();
                        if (!TextUtils.isEmpty(msg)){
                            binding.tvContent.post(new Runnable() {
                                @Override
                                public void run() {
                                    binding.tvContent.setText(msg);
                                }
                            });

                        }
                    }
                }.start();

            }
        });
        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        final String msg = HttpMethod.getHttpMethod().post();
                        if (!TextUtils.isEmpty(msg)){
                            binding.tvContent.post(new Runnable() {
                                @Override
                                public void run() {
                                    binding.tvContent.setText(msg);
                                }
                            });

                        }
                    }
                }.start();
            }
        });
        binding.btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvContent.setText("");
            }
        });
        setContentView(binding.getRoot());
    }
}