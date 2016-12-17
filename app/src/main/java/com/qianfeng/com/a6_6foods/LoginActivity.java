package com.qianfeng.com.a6_6foods;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.qianfeng.com.a6_6foods.bean.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/8/1.
 */
public class LoginActivity extends  BaseActivity{
    private EditText username_edit;
    private EditText password_edit;
    private SharedPreferences sp;
    private String username;
    private String password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        username_edit = (EditText) findViewById(R.id.username);
        password_edit = (EditText) findViewById(R.id.password);
        sp = getSharedPreferences("userInfo",MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        username_edit.setText(sp.getString("username",""));
        password_edit.setText(sp.getString("password",""));

    }

    public void login(View view) {
        username = username_edit.getText().toString().trim();
        password = password_edit.getText().toString().trim();

        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("username",username);
        query.addWhereEqualTo("password",password);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null){
                    if(list != null && list.size() > 0){
                        sp.edit().putString("username",username)
                                .putString("password",password)
                                .putBoolean("isLogin",true).commit();
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        LoginActivity.this.finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void reg(View view) {
        Intent intent = new Intent(this,RegActivity.class);
        startActivity(intent);

    }
}
