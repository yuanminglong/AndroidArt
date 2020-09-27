package com.yuan.androidart.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.yuan.androidart.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity() {
    private lateinit var activityViewBindingBinding: ActivityViewBindingBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewBindingBinding = ActivityViewBindingBinding.inflate(layoutInflater);
        setContentView(activityViewBindingBinding.root)
        activityViewBindingBinding.btnTest.setOnClickListener(View.OnClickListener { Toast.makeText(this,"成功了",Toast.LENGTH_SHORT).show() })
        activityViewBindingBinding.userName.text = "hello world"
    }
}