package com.qianfeng.com.a6_6foods.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.com.a6_6foods.R;
import com.qianfeng.com.a6_6foods.bean.FoodBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public class MyFoodAdapter extends BaseAdapter {
    private List<FoodBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public MyFoodAdapter(List<FoodBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.food_listview_item,parent,false);
            holder.iv= (ImageView) view.findViewById(R.id.food_iv);
            holder.description = (TextView) view.findViewById(R.id.description);
            holder.keywords = (TextView) view.findViewById(R.id.keywords);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Picasso.with(context).load(this.list.get(i).getImg())
                            .into(holder.iv);
        holder.description.setText(this.list.get(i).getDescription());
        holder.keywords.setText("【关键字】"+this.list.get(i).getKeywords());
        return view;
    }

    class ViewHolder{
        ImageView iv ;
        TextView description,keywords;
    }
}
