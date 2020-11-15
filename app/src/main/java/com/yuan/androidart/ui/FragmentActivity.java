package com.yuan.androidart.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yuan.androidart.R;
import com.yuan.androidart.ui.fragments.FirstFragment;
import com.yuan.androidart.ui.fragments.SecondFragment;
import com.yuan.androidart.ui.fragments.TopFragment;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button add,replace;
    private ViewGroup container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fregment);
        add = findViewById(R.id.btn_add);
        replace = findViewById(R.id.btn_replace);
        add.setOnClickListener(this);
        replace.setOnClickListener(this);
        container = findViewById(R.id.fragment_container);
        shouldShowRequestPermissionRationale(Manifest.permission.LOCATION_HARDWARE);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                addFragment();
                break;
            case R.id.btn_replace:
                replaceFragment();
                break;

        }
    }
    public void addFragment(){
        FragmentManager fragmentManager=  getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container,new FirstFragment(),null);
        transaction.commit();
    }
    public void replaceFragment(){
        FragmentManager fragmentManager=  getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,new SecondFragment(),null);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void checkPermissions(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.LOCATION_HARDWARE)==PackageManager.PERMISSION_DENIED){



        }else if (shouldShowRequestPermissionRationale(Manifest.permission.LOCATION_HARDWARE)){

        }else {

        }



    }
}
