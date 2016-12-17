package com.qianfeng.com.a6_6foods;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.com.a6_6foods.bean.FoodDetail;
import com.qianfeng.com.a6_6foods.bean.colbean;
import com.qianfeng.com.a6_6foods.utils.AppConfig;
import com.qianfeng.com.a6_6foods.utils.HttpUtils;
import com.qianfeng.com.a6_6foods.utils.JsonParse;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2016/8/1.
 */
public class DetailActivity extends BaseActivity {
    private ImageView foodImg;
    private TextView content;
    private boolean isCol;
    private FoodDetail fooddetail;

    //点赞变量
    private ImageView praise_tv;
    private Handler mHandler = new Handler(){


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case AppConfig.DOWNLOADSUCCESS:
                    fooddetail = (FoodDetail) msg.obj;
                    Picasso.with(DetailActivity.this).load(fooddetail.getImg()).into(foodImg);
                    //Html.fromHtml("")表示将一个HTML文本解析为HTML格式显示出来

                    content.setText(Html.fromHtml(fooddetail.getMessage()));
                    coltv.setVisibility(View.VISIBLE);
                    break;

                case AppConfig.DOWNLOADFALSE:
                    Toast.makeText(DetailActivity.this,"加载数据失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private ImageView coltv;
    private SharedPreferences sp;
    private EditText comment_content;
    private Button insert_comment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        initView();
        if (sp.getBoolean("isLogin",false)){
            int id = getIntent().getIntExtra("id",0);
            BmobQuery<colbean> query = new BmobQuery<>();
            query.addWhereEqualTo("url",String.format(AppConfig.FOODDETAILURL,id));
            query.addWhereEqualTo("username",sp.getString("username",""));
            query.findObjects(new FindListener<colbean>() {
                @Override
                public void done(List<colbean> list, BmobException e) {
                    if (e == null){
                        //该item已经收藏
                        if (list != null && list.size() >0){
                            coltv.setImageResource(android.R.drawable.btn_star_big_on);
                            isCol = true;
                        }else {
                            coltv.setImageResource(android.R.drawable.btn_star_big_off);
                            isCol = false;
                        }
                    }
                }
            });
        }

        initData();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        foodImg = (ImageView) findViewById(R.id.food_img);
        content = (TextView) findViewById(R.id.content_tv);
        //收藏
        coltv = (ImageView) findViewById(R.id.col_tv);
        coltv.setVisibility(View.GONE);
      /*  //点赞
        praise_tv = (ImageView) findViewById(R.id.praise_tv);
        //评论
        comment_content = (EditText) findViewById(R.id.comment_content);
        insert_comment = (Button) findViewById(R.id.insert_comment_content);*/
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtils.getJson(String.format(AppConfig.FOODDETAILURL, getIntent().getIntExtra("id", 0)));
                if (json != null &&!"".equals(json)){
                    FoodDetail foodDetail =  JsonParse.parseJson2FoodDetail(json);
                    Message message = mHandler.obtainMessage();
                    message.obj = foodDetail;
                    message.what = AppConfig.DOWNLOADSUCCESS;
                    mHandler.sendMessage(message);
                }else{
                    mHandler.sendEmptyMessage(AppConfig.DOWNLOADFALSE);
                }
            }
        }).start();
    }


    public void colClick(View view) {
        if (!sp.getBoolean("isLogin",false)){
            Toast.makeText(DetailActivity.this,"您还没有登录,请登录后再收藏！",Toast.LENGTH_SHORT).show();
            return;
        }

        //先获取数据
        String username = getSharedPreferences("userInfo",MODE_PRIVATE).getString("username","");
        String url = String.format(AppConfig.FOODDETAILURL, getIntent().getIntExtra("id", 0));
        int id = fooddetail.getId();

        if (isCol){
            BmobQuery<colbean> query = new BmobQuery<>();
            query.addWhereEqualTo("username",username);
            query.addWhereEqualTo("url",url);
            query.findObjects(new FindListener<colbean>() {
                @Override
                public void done(List<colbean> list, BmobException e) {
                    if (e == null){
                        if(list != null && list.size() > 0){
                            colbean colbean = list.get(0);
                            colbean.delete(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null){
                                        Toast.makeText(DetailActivity.this,"取消收藏成功！",Toast.LENGTH_SHORT).show();
                                        isCol = !isCol;
                                        coltv.setImageResource(android.R.drawable.btn_star_big_off);
                                    }else{
                                        Toast.makeText(DetailActivity.this,"取消收藏失败！",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }else {
            colbean colbean = new colbean();
            colbean.setUsername(username);
            colbean.setId(id);
            colbean.setName(fooddetail.getName());
            colbean.setUrl(url);
            colbean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null){
                        isCol = !isCol;
                        coltv.setImageResource(android.R.drawable.btn_star_big_on);
                        Toast.makeText(DetailActivity.this,"收藏成功！",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DetailActivity.this,"收藏失败！",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void praiseClick(View view) {

    }

    public void commentClick(View view) {
        //评论内容
        String content= comment_content.getText().toString().trim();

    }
}
