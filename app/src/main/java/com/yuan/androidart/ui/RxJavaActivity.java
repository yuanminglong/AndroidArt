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

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    ActivityRxJavaBinding binding;
    Observer observer;
    private static String TAG = RxJavaActivity.class.getSimpleName();
    Observable <String> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = ActivityRxJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Flowable flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> emitter) throws Throwable {

            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
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

        observable = Observable.create(emitter -> {
            for (int i = 0; i<10;i++){
                emitter.onNext("this is a default message");
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btnSendEvent.setOnClickListener(v -> {
                observable.subscribe(observer);
         }
        );

    }

}