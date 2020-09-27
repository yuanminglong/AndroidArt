package com.yuan.androidart.ui;

import android.os.Bundle;
import android.view.View;

import com.yuan.androidart.data.User;
import com.yuan.androidart.databinding.ActivityRoomBinding;
import com.yuan.androidart.viewmodel.RoomActivityViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;


public class RooMActivity extends AppCompatActivity {
    RoomActivityViewModel viewModel;
    ActivityRoomBinding activityRoomBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRoomBinding = ActivityRoomBinding.inflate(getLayoutInflater());
        setContentView(activityRoomBinding.getRoot());
        viewModel = new ViewModelProvider(this).get(RoomActivityViewModel.class);
        activityRoomBinding.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User("yuanminglo","12","minglong136@163.com");
                viewModel.insert(user);
            }
        });
        updateView(viewModel.getUsers());
        activityRoomBinding.quaryAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              updateView(viewModel.getUsers());
            }
        });
    }
    public void updateView(List<User> users){
        StringBuilder sb = new StringBuilder();
        for (User user:users) {
            sb.append(user.getUserId());
            sb.append(":");
            sb.append(user.getName());
            sb.append(":");
            sb.append(user.getAge());
            sb.append("\n");
        }
        activityRoomBinding.textContent.setText(sb.toString());
    }
}