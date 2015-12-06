package com.example.lt413.sumday;

import android.graphics.Bitmap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by lt413 on 2015/12/6.
 */
public class Wait_tro extends Fragment {

    private ImageView imageView;
    private String url1 = "http://appserver.shikee.com/trys/try_list?version=2.1.2";
    private String img_url;
    private Bitmap bitmap1;
    private Json json = new Json();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wait_tro,container,false);
        //图片资源


        //得到可用的图片
        Thread t = new Thread()
        {
            @Override
            public void run() {
                super.run();
                img_url = json.getHttpImgUrl(url1);
                bitmap1 = json.getHttpBitmap(img_url);
            }
        };

        t.start();

        try
        {
            t.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        imageView = (ImageView)rootView.findViewById(R.id.img1);
        //显示
        imageView.setImageBitmap(bitmap1);
        return rootView;
    }


}
