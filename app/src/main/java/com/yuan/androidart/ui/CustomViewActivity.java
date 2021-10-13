package com.yuan.androidart.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuan.androidart.R;
import com.yuan.androidart.view.QQStepView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.TypeReference;

public class CustomViewActivity extends AppCompatActivity {
    private QQStepView qqStepView;
    RecyclerView mRecyclerView;
    private List<String> mDatas = new ArrayList<>();
    MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        initView();
        initData();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mDatas.add("Text " + i);
        }
        myRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void initView() {
        //qqStepView = findViewById(R.id.qq_step_view);
        mRecyclerView = findViewById(R.id.rv_list);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
/*        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,90000);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(animation -> {
            int intAnimation = (int) animation.getAnimatedValue();
            qqStepView.setmCurrentCount(intAnimation);
        });
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.start();*/
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
        List<String> mData;
        public MyRecyclerViewAdapter(List<String> mData) {
            this.mData = mData;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());
            textView.setHeight((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,getResources().getDisplayMetrics()));
            return new MyViewHolder(textView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bindData(mData.get(position));
        }

        @Override
        public int getItemCount() {
            if (mData != null) {
                return mData.size();
            }
            return 0;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView mTextVew;
            public MyViewHolder(@NonNull TextView itemView) {
                super(itemView);
                mTextVew = itemView;
            }
            public void bindData(String info){
                mTextVew.setText(info);
            }
        }
    }
}