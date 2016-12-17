package com.qianfeng.com.a6_6foods.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class colbean extends BmobObject {
    private int id;
    private String name;
    private String url;
    private String username;

    public colbean(int id, String name, String url, String username) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.username = username;
    }

    public colbean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
