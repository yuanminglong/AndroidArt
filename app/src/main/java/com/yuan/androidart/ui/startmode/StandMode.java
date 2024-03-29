package com.yuan.androidart.ui.startmode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yuan.androidart.R;

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
       // Intent intent = new Intent(this,SingleTopMode.class);
        Intent intent = new Intent();
        intent.setAction("com.yuan.test.SingleTaskMode");
        intent.setComponent(new ComponentName("com.yuan.test","com.yuan.test.ui.SingleTaskMode"));
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}