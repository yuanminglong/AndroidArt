package com.yuan.androidart.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.yuan.androidart.R;
import com.yuan.androidart.data.User;
import com.yuan.androidart.databinding.ActivityDataBindingBinding;
import com.yuan.androidart.viewmodel.DataBindingActivityViewModel;

public class DataBindingActivity extends AppCompatActivity {
    private ActivityDataBindingBinding activityDataBindingBinding;
    DataBindingActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        viewModel =  new ViewModelProvider(this).get(DataBindingActivityViewModel.class);
        activityDataBindingBinding.setUser(viewModel.getLiveDataUser().getValue());
        viewModel.getLiveDataUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                activityDataBindingBinding.tvUserName.setText(user.getName());
            }
        });
        activityDataBindingBinding.btnSetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.changeUserName("minglongyuan");
            }
        });
    }
}