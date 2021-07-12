package com.yuan.androidart.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yuan.androidart.R;

public class DialogStudyActivity extends AppCompatActivity implements View.OnClickListener {
    private Button dialog;
    private static final String TAG = "DialogStudyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_study);
        dialog = findViewById(R.id.btn_dialog);
        dialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dialog:
                createDialog();
                break;
        }
    }

    private void createDialog() {
        String[] strings = {"1234","fdfsdf","dsfdf","khkkj","dfdsf","fsdfdfjl","flkjdlfl","1234","fdfsdf","dsfdf","khkkj","dfdsf","fsdfdfjl","flkjdlfl"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("标题")
        .setPositiveButton("确定", (dialog, which) -> {
            Log.d(TAG,"确定按钮按下了");
        }).setNegativeButton("取消",(dialog,which)->{
            Log.d(TAG,"取消按钮按下了");
        });
        builder.create().show();
    }
}