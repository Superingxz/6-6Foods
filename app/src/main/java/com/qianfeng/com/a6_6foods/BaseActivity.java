package com.qianfeng.com.a6_6foods;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Administrator on 2016/8/1 0001.
 */

public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //创建一个Dialog,一旦受到系统广播，就弹出
    }
    @Override
    protected void onResume() {
        super.onResume();
      toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


}
