package com.yuan.test.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.yuan.test.R;

import java.util.List;

public class StandMode extends AppCompatActivity implements View.OnClickListener {
    String TAG = "StandMode";
    private Button startButton,getInfoButton;
    private TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_mode);
        startButton =findViewById(R.id.btn_start);
        getInfoButton = findViewById(R.id.btn_getinfo);
        infoTextView = findViewById(R.id.tv_info);
        getInfoButton.setOnClickListener(this);
        startButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                startActivity();
                break;
            case R.id.btn_getinfo:
                getTaskInfo();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getTaskInfo(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> appTasks = activityManager.getAppTasks();
        ActivityManager.RecentTaskInfo taskInfo = appTasks.get(0).getTaskInfo();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TaskId：");
        stringBuilder.append(taskInfo.taskId);
        stringBuilder.append("\r\n");
        stringBuilder.append("Number Activities：");
        stringBuilder.append(taskInfo.numActivities);
        stringBuilder.append("\r\n");
        stringBuilder.append("baseActivity：");
        stringBuilder.append(taskInfo.baseActivity);
        stringBuilder.append("\r\n");
        stringBuilder.append("TopActivity：");
        stringBuilder.append(taskInfo.topActivity);
        stringBuilder.append("\r\n");
        infoTextView.setText(stringBuilder.toString());
        Log.d(TAG,stringBuilder.toString());
    }
    private void startActivity(){
        Intent intent = new Intent(this,SingleTopMode.class);
        startActivity(intent);
    }

}