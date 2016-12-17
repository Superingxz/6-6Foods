package com.qianfeng.com.a6_6foods.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qianfeng.com.a6_6foods.bean.ClassfyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public class MyFgAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    private List<ClassfyBean> bean = new ArrayList<>();
    public MyFgAdapter(FragmentManager fm,List<Fragment> fragments,List<ClassfyBean> bean) {
        super(fm);
        this.fragments = fragments;
        this.bean = bean;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.bean.get(position).getTitle();
    }
}
