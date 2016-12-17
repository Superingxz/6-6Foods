package com.qianfeng.com.a6_6foods.utils;


import com.qianfeng.com.a6_6foods.bean.ClassfyBean;
import com.qianfeng.com.a6_6foods.bean.FoodBean;
import com.qianfeng.com.a6_6foods.bean.FoodDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public class JsonParse {

    public  static List<ClassfyBean> JSonParse(String json) {
        List<ClassfyBean> list = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(json);
            JSONArray tngou = jo.getJSONArray("tngou");
            for (int i = 0; i < tngou.length(); i++) {
                JSONObject Classfy = tngou.getJSONObject(i);
                String description = Classfy.getString("description");
                String keywords = Classfy.getString("keywords");
                String name = Classfy.getString("name");
                String title = Classfy.getString("title");
                int foodclass = Classfy.getInt("foodclass");
                int id = Classfy.getInt("id");
                int seq = Classfy.getInt("seq");
                list.add(new ClassfyBean(description,foodclass,id,keywords,name,seq,title));
            }
            return  list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public static  List<FoodBean> Json2FoodBeanParse(String json){
        List<FoodBean> list = new ArrayList<>();
        JSONObject jo = null;
        try {
            jo = new JSONObject(json);
            JSONArray foods = jo.getJSONArray("tngou");
            for (int i = 0; i < foods.length(); i++) {
                JSONObject data = foods.getJSONObject(i);
                int count = data.getInt("count");
                String description = data.getString("description");
                String disease = data.getString("disease");
                int fcount = data.getInt("fcount");
                String food = data.getString("food");
                int id = data.getInt("id");
                String img = "http://tnfs.tngou.net/image" + data.getString("img");
                String keywords = data.getString("keywords");
                String name = data.getString("name");
                int rcount = data.getInt("rcount");
                String summary = data.getString("summary");
                String symptom = data.getString("symptom");
                list.add(new FoodBean(count, description, disease, fcount, food, id, img, keywords, name, rcount, summary, symptom));
            }
            return  list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FoodDetail parseJson2FoodDetail(String json) {
        List<FoodDetail> list = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(json);
            int count = data.getInt("count");
            int fcount = data.getInt("fcount");
            int id = data.getInt("id");
            int rcount = data.getInt("rcount");
            boolean status = data.getBoolean("status");
            String description = data.getString("description");
            String disease = data.getString("disease");
            String food = data.getString("food");
            String img = "http://tnfs.tngou.net/image"+data.getString("img");
            String keywords = data.getString("keywords");
            String message = data.getString("message");
            String name = data.getString("name");
            String summary = data.getString("summary");
            String symptom = data.getString("symptom");
            String url = data.getString("url");
            return new FoodDetail(count, description, disease, fcount, food, id, img, keywords, message, name, rcount, status, summary, symptom, url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
