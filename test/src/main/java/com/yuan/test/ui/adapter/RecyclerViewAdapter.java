package com.yuan.test.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.yuan.test.R;
import com.yuan.test.ui.BitmapLoader;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import io.zhuozhuo.remotetestlib.Message;
import io.zhuozhuo.remotetestlib.Size;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "RecyclerViewAdapter";
    private  List<Message> data ;
    private Context context;
    private Comparator<Message> comparator = new Comparator<Message>() {
        @Override
        public int compare(Message o1, Message o2) {
            return o1.getMsgId()-o2.getMsgId();
        }
    };

    public RecyclerViewAdapter(List<Message> data , Context context) {
        this.data = data;
        this.context =context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==Message.MessageTypeImage){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.message_image,null);
         return new ImageViewholder(view);
        }else{
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.text_message_layout,null);
            return new MyViewholder(view);
        }
/*        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.message_image,null);
        return  new CustomViewHolder(rootView);*/
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message= data.get(position);
        if (message.getMsgType()==Message.MessageTypeImage){
            ImageViewholder imageViewholder = (ImageViewholder) holder;
            imageViewholder.imageView.setImageBitmap(null);
            imageViewholder.loadImage(message);
        }else {
            MyViewholder myViewholder = (MyViewholder) holder;
            myViewholder.textView.setText(message.getContent().trim());
        }
/*        CustomViewHolder h = (CustomViewHolder) holder;
        ((CustomViewHolder) holder).recycleBitmap();
        h.setMessage(message);*/

    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getMsgType();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public synchronized void  notifyItemChanged(Message msg ){
        data.add(msg);
        Collections.sort(data,comparator);
        super.notifyDataSetChanged();
    }

    class MyViewholder extends RecyclerView.ViewHolder{
        public TextView textView;
        public MyViewholder(@NonNull View view) {
            super(view);
            TextView textView = view.findViewById(R.id.tv_content);
            //textView.setTextSize(Size.message_text_size);
            textView.setTextSize(20);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.drawable.message_bac);
            textView.setPadding(Size.message_horizontal_margin,0,Size.message_horizontal_margin,0);
            textView.setMaxWidth(Size.message_text_max_width);
            this.textView = textView;
        }
    }
    class ImageViewholder extends RecyclerView.ViewHolder{
        public  final ImageView imageView;
        public ImageViewholder( View rootView) {
            super(rootView);
            this.imageView = rootView.findViewById(R.id.iv_content);
            imageView.setMaxWidth((int)Size.message_image_max_width);
            imageView.setMaxWidth((int)Size.message_image_max_height);
        }
        public void loadImage(final Message msg){
            Bitmap bitmap = BitmapLoader.getInstance().getBitmapFromCache(msg);
            Log.d(TAG,"loadBitmap from cache"+bitmap);
            if (bitmap!=null){
                imageView.setImageBitmap(bitmap);
                return;
            }
            BitmapLoader.getInstance().loadBitmap(msg, new BitmapLoader.LoadBitmapListener() {
                @Override
                public void finished(final Bitmap bitmap) {
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }
            });

        }
    }

    /*class CustomViewHolder extends RecyclerView.ViewHolder {
        CustomView customView;

        public CustomViewHolder(View rootView) {
            super(rootView);
            this.customView = rootView.findViewById(R.id.iv_content);
            customView.setBackgroundResource(R.drawable.message_bac);
            customView.setMaxWidth((int)Size.message_image_max_width);
            customView.setMaxWidth((int)Size.message_image_max_height);
        }


        public void recycleBitmap() {
            if (customView != null && customView.getDrawable() != null) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) customView.getDrawable();
                    customView.setImageDrawable(null);
                    if (bitmapDrawable != null) {
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        if (bitmap != null) {
                            bitmap.recycle();
                        }

                    }
                }
        }

        public void setMessage(Message msg){
            customView.clean();
            customView.setMessage(msg);
            if (msg.getMsgType()==Message.MessageTypeImage){
                BitmapLoader.getInstance().loadBitmap(msg, new BitmapLoader.LoadBitmapListener() {
                    @Override
                    public void finished(final Bitmap bitmap) {
                        customView.post(new Runnable() {
                            @Override
                            public void run() {
                                customView.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
            }
        }
    }*/
}
