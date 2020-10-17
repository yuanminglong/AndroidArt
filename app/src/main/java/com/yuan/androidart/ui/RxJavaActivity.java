package com.yuan.androidart.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = ActivityRxJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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

        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Handler handler = new Handler(Looper.myLooper());
                        Looper.loop();
                        Looper looper= Looper.myLooper();



                        MessageQueue messageQueue = looper.getQueue();


                        handler.sendEmptyMessage(Message.obtain().what=0);
                        Message message = Message.obtain();

                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                        Looper.myLooper().quit();
                        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
                            @Override
                            public boolean queueIdle() {
                                return false;
                            }
                        });

                    }
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btnSendEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Observable<String> observable =  Observable.fromArray("12345","123454567");
                //observable.subscribe(observer);
                String time = String.valueOf(SystemClock.uptimeMillis()/(3600*1000));
                binding.tvContents.setText(time);

            }
        });
    }
}