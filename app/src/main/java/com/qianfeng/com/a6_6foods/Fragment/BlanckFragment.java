package com.qianfeng.com.a6_6foods.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qianfeng.com.a6_6foods.DetailActivity;
import com.qianfeng.com.a6_6foods.R;
import com.qianfeng.com.a6_6foods.adapter.FoodHearderAdapter;
import com.qianfeng.com.a6_6foods.adapter.MyFoodAdapter;
import com.qianfeng.com.a6_6foods.bean.FoodBean;
import com.qianfeng.com.a6_6foods.utils.AppConfig;
import com.qianfeng.com.a6_6foods.utils.HttpUtils;
import com.qianfeng.com.a6_6foods.utils.JsonParse;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/8/1 0001.
 */
public class BlanckFragment extends Fragment {
    private ListView listview;
    private ProgressBar progressBar;
    private MyFoodAdapter adapter;
    private List<FoodBean> foods = new ArrayList<>();
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AppConfig.DOWNLOADSUCCESS:
                    Log.i("BlanckFragment", "handleMessage: hello");
                    foods = (List<FoodBean>) msg.obj;
                    Log.i("BlanckFragment", "handleMessage: foods size"+foods.size());
                    adapter = new MyFoodAdapter(foods,getActivity());
                    listview.setAdapter(adapter);
                    break;

                case AppConfig.DOWNLOADFALSE:

                    break;
            }
            progressBar.setVisibility(View.GONE);
        }
    };
    public static  BlanckFragment getInstance(int id,int index){
        BlanckFragment fragment = new BlanckFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putInt("index",index);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = initView(inflater,container);
        //如果是第一页，就显示广告图片滚动
        Log.i("superingxz", "onCreateView: index:"+getArguments().getInt("index"));
        if (getArguments().getInt("index") == 0) {
            initAbsBar(inflater);
        }

        initData();
        return view;
    }
    //初始化广告滚动图片
    private void initAbsBar(LayoutInflater inflater) {
        View AbsView = inflater.inflate(R.layout.header_foot_listview_layout,null);
        ViewPager viewPager = (ViewPager) AbsView.findViewById(R.id.food_viewpager);
        List<String> urls = new ArrayList<>();
        urls.add("http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg");
        urls.add("http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg");
        urls.add("http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg");
        urls.add("http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg");
        urls.add("http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg");
        FoodHearderAdapter adapter = new FoodHearderAdapter(urls,getActivity());
        Log.i("superingxz", "initAbsBar: foodheader viewpager size"+adapter.getCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //禁止父容器拦截事件
                v.getParent().requestDisallowInterceptTouchEvent(true);
                //这里返回值为false,如果返回true则屏蔽滑动
                return false;
            }
        });
        //给ListView添加头布局，必须在给ListView设置Adapter之前添加头布局，否则没有效果
        listview.addHeaderView(AbsView);
    }

    //初始化控件
    private View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.blanck_fg_layout,container,false);
        listview = (ListView) view.findViewById(R.id.listview);

        //没有数据时，空白提示
         TextView textView = (TextView) view.findViewById(R.id.nodata);
        //当ListView中没有数据时显示TextView
        listview.setEmptyView(textView);
        //进度条
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //当给ListView添加了头布局之后，ListView的item position会从1开始计数
                int newPosition = position;
                if (getArguments().getInt("index") == 0)
                    newPosition = position -1;
                //到DetailActivity页面显示详情页
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("id",foods.get(newPosition).getId());
                startActivity(intent);
            }
        });

        return view;
    }

    private void initData() {
        final int id = getArguments().getInt("id");
        new Thread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                String json = HttpUtils.getJson(String.format(AppConfig.FOOD,id));
                Log.i("BlanckFragment", "run: food json"+json);
                if (json != null && !"".equals(json)) {
                    List<FoodBean> foods = JsonParse.Json2FoodBeanParse(json);
                    Log.i("BlanckFragment", "run: foods size"+foods.size());
                    Message msg = mHandler.obtainMessage();
                    msg.obj = foods;
                    msg.what = AppConfig.DOWNLOADSUCCESS;
                    mHandler.sendMessage(msg);
                }else{
                   mHandler.sendEmptyMessage(AppConfig.DOWNLOADFALSE);
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
