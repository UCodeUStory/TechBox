package com.ustory.techbox.utils;

import android.util.Log;

import com.squareup.okhttp.Request;
import com.ustory.techbox.bean.User;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/19
 */
public class testOKHttpUtils {

    public void getJson (String url){
        OKHttpUtils.getAsyn("http://192.168.56.1:8080/okHttpServer/user!getUsers",
                new OKHttpUtils.ResultCallback<List<User>>()
                {
                    @Override
                    public void onError(Request request, Exception e)
                    {
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(List<User> us)
                    {
                        Log.e("TAG", us.size() + "");
                        //   mTv.setText(us.get(1).toString());
                    }
                });
    }

}
