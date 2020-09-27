package com.yuan.androidart.ui.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuan.androidart.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivityRecyclerViewAdapter extends RecyclerView.Adapter<MainActivityRecyclerViewAdapter.MyViewholder> {

    private List<MainActivity.ActivityBean> data = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    public MainActivityRecyclerViewAdapter(List<MainActivity.ActivityBean> data) {
       if (data!=null){
           this.data.addAll(data);
       }
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setTextSize(50);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.BLUE);
        return new MyViewholder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, final int position) {
        MainActivity.ActivityBean bean = data.get(position);
        holder.textView.setText(bean.getName());
        if (mOnItemClickListener!=null){
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(data.get(position));
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return data.size();
    }
    public void notifyItemChanged(List<MainActivity.ActivityBean> newData){
        if (newData!=null&&newData.size()>0){
            data.clear();
            data.addAll(newData);
        }
        super.notifyItemChanged(0);
    }

    class MyViewholder extends RecyclerView.ViewHolder{
        public TextView textView;
        public MyViewholder(@NonNull TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }
    public  interface OnItemClickListener<T>{
         void onItemClick(T obj);
    }
}
