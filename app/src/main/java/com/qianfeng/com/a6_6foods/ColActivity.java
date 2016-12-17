package com.qianfeng.com.a6_6foods;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.qianfeng.com.a6_6foods.bean.colbean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ColActivity extends BaseActivity {

    private ListView colListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_col);
        colListView = (ListView) findViewById(R.id.col_listview);

    }

    @Override
    protected void onResume() {
        super.onResume();
        BmobQuery<colbean> query = new BmobQuery<>();
        query.addWhereEqualTo("username",getIntent().getStringExtra("username"));
        query.findObjects(new FindListener<colbean>() {
            @Override
            public void done(List<colbean> list, BmobException e) {
                if (e == null){
                    if (list != null && list.size()>0){
                        List<String> data = new ArrayList<>();
                        for (colbean colbean:list){
                            data.add(colbean.getName());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ColActivity.this,android.R.layout.simple_list_item_1,data);
                        colListView.setAdapter(adapter);
                    }
                }
            }
        });
        colListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String foodDetailName = (String) parent.getItemAtPosition(position);
                Toast.makeText(ColActivity.this, "收藏食物:"+foodDetailName, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
