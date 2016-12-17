package com.qianfeng.com.a6_6foods;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

public class WelcomeActivity extends Activity {

    private boolean flag = true;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("qianfengmo", "onCreate: ");
        setContentView(R.layout.activity_welcome);

        //更新配置文件，检查用户是否登录。。
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                if (flag){
                    SharedPreferences sp = getSharedPreferences("xiaomo",MODE_PRIVATE);
                    boolean isFirstLogin = sp.getBoolean("isFirstLogin",true);
                    if (isFirstLogin){
                        startActivity(new Intent(WelcomeActivity.this,GuideActivity.class));
                    }else{
                        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    }
                    WelcomeActivity.this.finish();
                }
            }
        }).start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        flag = false;
    }
}
