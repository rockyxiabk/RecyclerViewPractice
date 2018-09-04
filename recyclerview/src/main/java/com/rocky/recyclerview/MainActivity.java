package com.rocky.recyclerview;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rocky.recyclerview.adapter.CustomFragmentAdapter;
import com.rocky.recyclerview.type.GirdListFragment;
import com.rocky.recyclerview.type.ListViewFragment;
import com.rocky.recyclerview.type.StageFragment;
import com.rocky.recyclerview.type.SwipeDeleteFragment;
import com.rocky.recyclerview.type.SwipeFragment;

import java.util.ArrayList;
import java.util.List;

//https://guides.codepath.com/android
public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private TabLayout tableLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tableLayout = ((TabLayout) findViewById(R.id.tab));
        viewPager = ((ViewPager) findViewById(R.id.viewpager));
        fragmentList = new ArrayList<>();

        tableLayout.addOnTabSelectedListener(this);
        tableLayout.addTab(tableLayout.newTab().setText("ListView"));
        fragmentList.add(new ListViewFragment());
        tableLayout.addTab(tableLayout.newTab().setText("GirdView"));
        fragmentList.add(new GirdListFragment());
        tableLayout.addTab(tableLayout.newTab().setText("StageView"));
        fragmentList.add(new StageFragment());
        tableLayout.addTab(tableLayout.newTab().setText("SwipeView"));
        fragmentList.add(new SwipeFragment());
        tableLayout.addTab(tableLayout.newTab().setText("SwipeDeleteView"));
        fragmentList.add(new SwipeDeleteFragment());

//        tableLayout.setupWithViewPager(viewPager);//需要重写pagerAdapter.getTitle();
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new CustomFragmentAdapter(fragmentList, getSupportFragmentManager()));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
