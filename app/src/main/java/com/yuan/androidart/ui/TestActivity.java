package com.yuan.androidart.ui;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.zhuozhuo.remotetestlib.DataCenter;
import io.zhuozhuo.remotetestlib.Message;

public class TestActivity  extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        DataCenter.init(this, false);
        DataCenter.register(new DataCenter.OnMessageChangeListener() {
            @Override
            public void onMessageChange(Message message) {



            }
        });

    }
}
