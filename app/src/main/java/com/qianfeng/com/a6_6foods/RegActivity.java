package com.qianfeng.com.a6_6foods;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.qianfeng.com.a6_6foods.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegActivity extends Activity {

    private EditText username;
    private EditText password;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        username.setError("用户名不合法！");
         sp = getSharedPreferences("userInfo",MODE_PRIVATE);
    }

    public void OnReg(View view) {
        User user = new User();
        user.setUsername(username.getText().toString().trim());
        user.setPassword(password.getText().toString().trim());
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                    sp.edit().putString("username",username.getText().toString().trim())
                            .putString("password",password.getText().toString().trim())
                            .putBoolean("isLogin",false)
                            .commit();
                    RegActivity.this.finish();
                    Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
