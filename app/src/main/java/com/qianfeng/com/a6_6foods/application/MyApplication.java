package com.qianfeng.com.a6_6foods.application;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,"c9e02dece42851575361cdb5e2f156ce");
    }
}
