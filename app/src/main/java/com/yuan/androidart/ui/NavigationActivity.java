package com.yuan.androidart.ui;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yuan.androidart.R;

public class NavigationActivity extends AppCompatActivity {
    AppBarConfiguration appBarConfiguration;
    private NavigationView navigationView;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                CharSequence title  =  menuItem.getTitle();
                String stitle  =  TextUtils.isEmpty(title)? "empty":title.toString();
                Toast.makeText(NavigationActivity.this,stitle,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigationFragment1,R.id.navigationFragment2,R.id.navigationFragment3).setDrawerLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this,Navigation.findNavController(this,R.id.fragment),appBarConfiguration );
        NavigationUI.setupWithNavController(bottomNavigationView,Navigation.findNavController(this,R.id.fragment));
        NavigationUI.setupWithNavController(navigationView,Navigation.findNavController(this,R.id.fragment));
        getLifecycle().addObserver(new NavigationActivityLifecycleObserver());
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this,R.id.fragment);
       return NavigationUI.navigateUp(navController,drawerLayout)||super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}