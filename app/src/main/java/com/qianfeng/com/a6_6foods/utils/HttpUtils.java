package com.qianfeng.com.a6_6foods.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public class HttpUtils {
    public static String getJson(String urlString){
        HttpURLConnection con = null ;
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            Log.i("qianfengmo", "getJson: urlString"+urlString);
            con.connect();
            Log.i("qianfengmo", "getJson: getResponseCode"+con.getResponseCode());
            if (con.getResponseCode() == 200){
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String str ;
                while((str = br.readLine()) != null){
                    sb.append(str);

                }
                Log.i("qianfengmo", "getJson: json"+sb.toString());
                return  sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (con != null){
                con.disconnect();
            }
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] getData(String urlStr) {
        HttpURLConnection con = null;
        InputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5 * 1000);
            con.setReadTimeout(5 * 1000);
            con.connect();
            if (con.getResponseCode() == 200) {
                is = con.getInputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                return baos.toByteArray();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (con != null) {
                con.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
