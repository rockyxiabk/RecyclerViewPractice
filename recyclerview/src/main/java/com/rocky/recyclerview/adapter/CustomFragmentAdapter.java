package com.rocky.recyclerview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Description : com.rocky.recyclerview
 *
 * @author : rocky
 * @Create Time : 2018/8/29 下午6:02
 * @Modified Time : 2018/8/29 下午6:02
 */
public class CustomFragmentAdapter extends FragmentStatePagerAdapter {
    List<? extends Fragment> mFragments;


    public CustomFragmentAdapter(List<? extends Fragment> fragments, FragmentManager fm) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }
}