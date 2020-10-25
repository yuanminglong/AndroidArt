package com.yuan.test.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.yuan.test.R;
import com.yuan.test.ui.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.zhuozhuo.remotetestlib.DataCenter;
import io.zhuozhuo.remotetestlib.Message;
import io.zhuozhuo.remotetestlib.Size;

public class TestActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerAdapter;
    private List<Message> data = new ArrayList<>();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull android.os.Message msg) {
            Message message  = (Message) msg.obj;
            mRecyclerAdapter.notifyItemChanged(message);
        }
    };
    private String TAG = TestActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        DataCenter.init(this,true);
        mRecyclerView = findViewById(R.id.rc_content);
        LinearLayoutManager ll=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(ll);
        mRecyclerAdapter = new RecyclerViewAdapter(data,this);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = Size.message_horizontal_margin;
                outRect.right=Size.message_horizontal_margin;
            }
        });
        mRecyclerView.setAdapter(mRecyclerAdapter);
        DataCenter.register(new DataCenter.OnMessageChangeListener() {
            @Override
            public void onMessageChange(Message message) {
                if (message==null) return;
                Log.d(TAG,message.toString()+";"+data.size());
                android.os.Message msg = android.os.Message.obtain();
                msg.obj = message;
                msg.what=1;
                handler.sendMessage(msg);
            }
        });
        mRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });

    }
}
