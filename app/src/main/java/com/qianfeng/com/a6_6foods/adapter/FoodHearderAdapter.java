package com.qianfeng.com.a6_6foods.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public class FoodHearderAdapter extends PagerAdapter {
    private List<String> list = new ArrayList<>();
    private Context context;

    public FoodHearderAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        Picasso.with(context).load(this.list.get(position)).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView imageView = (ImageView) object;
        container.removeView(imageView);
    }
}
