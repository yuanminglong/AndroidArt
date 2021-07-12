package com.yuan.kotlinproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yuan.kotlinproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       activityMainBinding =  ActivityMainBinding.inflate(layoutInflater);
        activityMainBinding.name.setTextColor(Color.RED)
        activityMainBinding.button.setOnClickListener(View.OnClickListener { Log.d("Main","shou")})
        setContentView(activityMainBinding.root)

    }
    override fun onStop() {
        super.onStop()
    }
}
