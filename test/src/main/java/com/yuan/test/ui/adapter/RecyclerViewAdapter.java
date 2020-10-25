package com.yuan.test.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.yuan.test.R;
import com.yuan.test.ui.BitmapLoader;
import com.yuan.test.ui.CustomView;

import java.util.List;
import io.zhuozhuo.remotetestlib.Message;
import io.zhuozhuo.remotetestlib.Size;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "RecyclerViewAdapter";
    private  List<Message> data ;
    private Context context;

    public RecyclerViewAdapter(List<Message> data , Context context) {
        this.data = data;
        this.context =context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
/*        if (viewType==Message.MessageTypeImage){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.message_image,null);
         return new ImageViewholder(view);
        }else{
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.text_message_layout,null);
            return new MyViewholder(view);
        }*/
        CustomView customView = new CustomView(parent.getContext());
        return  new CustomViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message= data.get(position);
/*        if (message.getMsgType()==Message.MessageTypeImage){
            ImageViewholder imageViewholder = (ImageViewholder) holder;
            imageViewholder.imageView.setImageBitmap(null);
            imageViewholder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_background));
            imageViewholder.loadImage(message);
        }else {
            MyViewholder myViewholder = (MyViewholder) holder;
            myViewholder.textView.setText(message.getContent());
        }*/
        CustomViewHolder h = (CustomViewHolder) holder;
        ((CustomViewHolder) holder).setMessage(message);

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
        super.notifyItemChanged(data.size()-1);
    }

    class MyViewholder extends RecyclerView.ViewHolder{
        public TextView textView;
        public MyViewholder(@NonNull View view) {
            super(view);
            TextView textView = view.findViewById(R.id.tv_content);
            textView.setTextSize(Size.message_text_size);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.drawable.message_bac);
            textView.setMaxWidth(Size.message_text_max_width);
            textView.setPadding(Size.message_horizontal_margin,0,Size.message_horizontal_margin,0);
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

    class CustomViewHolder extends RecyclerView.ViewHolder {
        CustomView customView;

        public CustomViewHolder(CustomView customView) {
            super(customView);
            customView.setBackgroundColor(Color.RED);
            this.customView = customView;
        }

        public void setMessage(Message msg){
            customView.clean();
            customView.setMessage(msg);
            if (msg.getMsgType()==Message.MessageTypeImage){
                BitmapLoader.getInstance().loadBitmap(msg, new BitmapLoader.LoadBitmapListener() {
                    @Override
                    public void finished(Bitmap bitmap) {
                        customView.setBitmap(bitmap);
                    }
                });
            }
        }
    }
}
