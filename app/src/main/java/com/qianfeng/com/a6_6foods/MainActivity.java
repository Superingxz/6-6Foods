package com.qianfeng.com.a6_6foods;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.com.a6_6foods.Fragment.BlanckFragment;
import com.qianfeng.com.a6_6foods.adapter.MyFgAdapter;
import com.qianfeng.com.a6_6foods.bean.ClassfyBean;
import com.qianfeng.com.a6_6foods.utils.AppConfig;
import com.qianfeng.com.a6_6foods.utils.HttpUtils;
import com.qianfeng.com.a6_6foods.utils.JsonParse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{
    private List<Fragment> fragments = new ArrayList<>();
    private MyFgAdapter adapter;
    private List<ClassfyBean> beanList = new ArrayList<>();
    private TabLayout tablayout;
    private  ViewPager viewpager;
    
    private TextView loginTv;
    private Handler mhandler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case AppConfig.DOWNLOADSUCCESS:
                    beanList = (List<ClassfyBean>) msg.obj;
                    Log.i("qianfengmo", "handleMessage: beanlist size"+beanList.size());
                    for (int i = 0; i < beanList.size(); i++) {
                        ClassfyBean classfyBean = beanList.get(i);
                        Log.i("qianfengmo", "handleMessage: classfyBean id"+classfyBean.getId());
                        //传i索引值为了  为第一页设置头广告滚动图片
                        fragments.add(BlanckFragment.getInstance(classfyBean.getId(),i));
                        adapter = new MyFgAdapter(getSupportFragmentManager(),fragments,beanList);
                        viewpager.setAdapter(adapter);
                        tablayout.setupWithViewPager(viewpager);

                    }

                    break;
                case AppConfig.DOWNLOADFALSE:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //数据加载
        loadData();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_header_container);
        //获取NavigationView的头布局
        View headerView = navigationView.getHeaderView(0);
        //获取登录控件
        loginTv = (TextView) headerView.findViewById(R.id.login_iv);
        updateLoginTv();

        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        //菜单点击
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.my_col:
                        SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
                        Intent intent = new Intent(MainActivity.this,ColActivity.class);
                        if (!sp.getBoolean("isLogin",false)) {
                            Toast.makeText(MainActivity.this,"您还没登录！",Toast.LENGTH_SHORT).show();
                            break;
                        }
                        intent.putExtra("username", sp.getString("username",""));
                        startActivity(intent);
                        break;
                    case R.id.my_setting:
                        Toast.makeText(MainActivity.this,"设置",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about_me:
                        Toast.makeText(MainActivity.this,"关于我",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.outlogin:
                        boolean isLogin = getSharedPreferences("userInfo",MODE_PRIVATE).getBoolean("isLogin",false);
                        if (isLogin){
                            getSharedPreferences("userInfo",MODE_PRIVATE).edit().putBoolean("isLogin",false).commit();
                        }
                        updateLoginTv();

                        break;
                }
                return false;


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("qianfengmo", "onResume: ");
        //侧拉布局
        updateLoginTv();
        Log.i("qianfengmo", "onResume: after updateLoginTv");
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    //当登录Activity销毁后回调该方法，在该方法中更新loginTv的值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateLoginTv();
    }
    private void updateLoginTv() {
        Log.i("qianfengmo", "updateLoginTv method");
        //如果登录了则SharePreferences loginInfo中的isLogin为true
        SharedPreferences loginInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        boolean isLogin = loginInfo.getBoolean("isLogin",false);
        Log.i("qianfengmo", "updateLoginTv: isLogin"+isLogin);
        if (isLogin){
            Log.i("qianfengmo", "updateLoginTv: username"+loginInfo.getString("username","请登录"));
            loginTv.setText(loginInfo.getString("username","请登录"));
        }else{
            loginTv.setText("请登录");
        }
    }


    private void loadData() {
        Log.i("qianfengmo", "loadData: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtils.getJson(AppConfig.CLASSFY);
                Log.i("qianfengmo", "run: json"+json);
                if (json !=null &&!"".equals(json)) {
                    //解析json数据
                    List<ClassfyBean> ClassfyBeans = JsonParse.JSonParse(json);
                    Log.i("qianf" +
                            "engmo", "run: size"+ClassfyBeans.size());
                    Message message = mhandler.obtainMessage();
                    message.obj = ClassfyBeans;
                    message.what = AppConfig.DOWNLOADSUCCESS;
                    mhandler.sendMessage(message);
                }else{
                    Message message = mhandler.obtainMessage();
                    message.what = AppConfig.DOWNLOADFALSE;
                    mhandler.sendMessage(message);
                }
            }
        }).start();
    }

    public void loginTv(View view) {
        if (!getSharedPreferences("userInfo", MODE_PRIVATE).getBoolean("isLogin", false)){
            startActivityForResult(new Intent(MainActivity.this,LoginActivity.class),1);
        }else{
            //跳转到用户详情页
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mhandler.removeCallbacksAndMessages(null);
    }


}
