package com.yuan.androidart.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yuan.androidart.R;
import com.yuan.androidart.ui.adapter.MainActivityRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mRecyclerView;
    private List<ActivityBean>  mItems = new ArrayList<>();
    private MainActivityRecyclerViewAdapter mRecyclerAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefresh = findViewById(R.id.swipeRefresh);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mItems.clear();
                mItems.add(new ActivityBean(FragmentActivity.class,"第一课"));
                mItems.add(new ActivityBean(ViewBindingActivity.class,"视图绑定"));
                mItems.add(new ActivityBean(DataBindingActivity.class,"数据绑定"));
                mItems.add(new ActivityBean(NavigationActivity.class,"导航组件"));
                mItems.add(new ActivityBean(RooMActivity.class,"Room"));
                mItems.add(new ActivityBean(CustomViewActivity.class,"自定义View"));
                mItems.add(new ActivityBean(OkhttpActivity.class,"Okhttp"));
                mRecyclerAdapter.notifyItemChanged(mItems);
                mSwipeRefresh.setRefreshing(false);
            }
        });
        mRecyclerView = findViewById(R.id.recyclerView_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter = new MainActivityRecyclerViewAdapter(mItems);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.setmOnItemClickListener(new MainActivityRecyclerViewAdapter.OnItemClickListener<ActivityBean> (){

            @Override
            public void onItemClick(ActivityBean obj) {
                if (obj !=null){
                    Intent intent = new Intent(MainActivity.this,obj.getmClass());
                    MainActivity.this.startActivity(intent);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this,item.getTitle().toString(),Toast.LENGTH_LONG).show();


        return super.onOptionsItemSelected(item);
    }

    public class ActivityBean{
        private Class<? extends Activity> mClass;
        private String name;

        public ActivityBean(Class<? extends Activity> mClass, String name) {
            this.mClass = mClass;
            this.name = name;
        }

        public Class<? extends Activity> getmClass() {
            return mClass;
        }

        public String getName() {
            return name;
        }
    }




}
