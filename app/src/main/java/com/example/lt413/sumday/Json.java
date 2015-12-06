package com.example.lt413.sumday;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lt413 on 2015/12/6.
 */
public class Json {

    public String getHttpImgUrl(String url) {
        URL myFileURL;

        String img_list = null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流

            InputStreamReader is = new InputStreamReader(conn.getInputStream());
            BufferedReader bu = new BufferedReader(is);
            String readData = "";
            String inputLine  = null;

            while ((inputLine = bu.readLine())!=null)
            {
                readData = inputLine + "\n";
            }

//            System.out.println(readData);

            JSONObject object = new JSONObject(readData);
            JSONObject data = object.getJSONObject("data");
            JSONArray try_list = data.getJSONArray("try_list");
            JSONObject try1 = try_list.getJSONObject(2);
            img_list = try1.getString("img_list");

//            String result = readData(is, "GBK");
            //解析得到图片

            //关闭数据流
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return img_list;

    }
    public static Bitmap getHttpBitmap(String url){
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return bitmap;

    }
}
