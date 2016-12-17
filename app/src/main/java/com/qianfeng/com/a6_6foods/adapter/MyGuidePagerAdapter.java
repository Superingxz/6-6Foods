package com.qianfeng.com.a6_6foods.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class MyGuidePagerAdapter extends PagerAdapter {
    private List<ImageView> imageViews;

    public MyGuidePagerAdapter(List<ImageView> imageViews) {
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return this.imageViews.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(this.imageViews.get(position));
        return  this.imageViews.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(this.imageViews.get(position));
    }
}
