package com.qianfeng.com.a6_6foods.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class CommentBean extends BmobObject {
    private String comentcontent;
    private String foodname;
    private String username;
    private String foodid;


    public String getComentcontent() {
        return comentcontent;
    }

    public void setComentcontent(String comentcontent) {
        this.comentcontent = comentcontent;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoodid() {
        return foodid;
    }

    public void setFoodid(String foodid) {
        this.foodid = foodid;
    }
}
