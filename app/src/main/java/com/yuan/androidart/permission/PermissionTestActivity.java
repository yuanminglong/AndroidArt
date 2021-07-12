package com.yuan.androidart.permission;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.yuan.androidart.R;


public class PermissionTestActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "PermissionTestActivity";
    private Button checkPermissionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_test);
       // checkPermissionButton = findViewById(R.id.tv_check_permission);
        //checkPermissionButton.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_check_permission:
                //checkPermission();
                //startCameraIntent();
                setAlarm();
                break;

        }
    }

    private void checkPermission(){



            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_CONTACTS},200);


        Log.d(TAG,"camera permission request is "+checkSelfPermission(Manifest.permission.CAMERA));
        Log.d(TAG,"Read contacts permission request is "+checkSelfPermission(Manifest.permission.READ_CONTACTS));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG,"result is "+grantResults+"  invoke time is ");
        switch (requestCode){
            case 200:
                Log.d(TAG,permissions.length+": "+grantResults.length +"-----invoke time is ");
                break;
        }

    }

    private void startCameraIntent(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_EDIT);
        startActivity(intent);
    }
    private void setAlarm(){
        Intent intent = new Intent();
        intent.setAction(AlarmClock.ACTION_SET_ALARM);
        startActivity(intent);
    }
}