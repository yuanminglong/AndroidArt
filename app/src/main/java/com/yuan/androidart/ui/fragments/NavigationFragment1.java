package com.yuan.androidart.ui.fragments;

import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.yuan.androidart.R;
import com.yuan.androidart.databinding.NavigationFragment1FragmentBinding;



public class NavigationFragment1 extends Fragment {

    private NavigationFragment1ViewModel mViewModel;
    NavigationFragment1FragmentBinding binding;
    private ViewPager2 viewPager2;

    public static NavigationFragment1 newInstance() {
        return new NavigationFragment1();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.navigation_fragment1_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.viewPager2.setAdapter(new ViewPager2Adapter(getActivity()));
        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
               tab.setText("第"+position+"Fragment") ;
               tab.setIcon(R.drawable.ic_baseline_account_balance_24);
            }
        }
        ).attach();
    }

    class ViewPager2Adapter extends FragmentStateAdapter {


        public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Bundle bundle = new Bundle();
            bundle.putString("title", "第"+position+"个Fragment");
            Fragment fragment = new DemoFragment();
            fragment.setArguments(bundle);
            return fragment ;
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

   public static  class DemoFragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            String content = getArguments().getString("title");
            TextView textView = new TextView(getContext());
            textView.setTextColor(Color.RED);
            textView.setText(content);
            return textView;
        }
    }

}