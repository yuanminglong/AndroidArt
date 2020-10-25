package com.yuan.androidart.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.yuan.androidart.R;
import com.yuan.androidart.databinding.ActivityRxJavaBinding;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class RxJavaActivity extends AppCompatActivity {
    ActivityRxJavaBinding binding;
    Observer observer;
    Handler handler;
    Looper looper;
    private static String TAG = RxJavaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = ActivityRxJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActivityManager a = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(String o) {
                StringBuilder stringBuilder =new StringBuilder( binding.tvContents.getText());
                stringBuilder.append("\n");
                stringBuilder.append(o);
                binding.tvContents.setText(stringBuilder.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btnSendEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPackageManager();
            }
        });

    }

    public class  MyTask extends  AsyncTask<String, String ,String >{

        @Override
        protected String doInBackground(String... strings) {
            publishProgress();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
}