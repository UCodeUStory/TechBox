package com.ustory.techbox.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.test2.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.ustory.techbox.adapter.OkHttpAdapter;
import com.ustory.techbox.bean.OkHttpTech;
import com.ustory.techbox.core.BaseFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/6
 */
public class OKHttpDemoFragment extends BaseFragment implements AdapterView.OnItemClickListener{

    private ListView mListView;
    private OkHttpAdapter adapter;
    private List<OkHttpTech> lists = new ArrayList<OkHttpTech>();
    private OkHttpClient mOkHttpClient;
    String  result="";
    TextView textView;
    final int  MSG_WHAT=1;
    String url = "https://www.baidu.com/";
    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WHAT:
                    Intent intent = new Intent(getActivity(), okHttpRequestActivity.class);
                    intent.putExtra("result",result);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        };
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRootView(R.layout.ok_http_demo_fragment);
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    protected void initView(View rootView) {
        this.mListView = $(R.id.okhttp_list);
    }

    @Override
    protected void initListener() {
        this.mListView.setOnItemClickListener(this);

    }

    @Override
    protected void initData() {
        for (int i=0;i<5;i++){
            OkHttpTech tech = new OkHttpTech();
            tech.setId(i);
            tech.setContent(""+i);
            lists.add(tech);
        }
        adapter = new OkHttpAdapter(getActivity(),lists,R.layout.demo_item);
        this.mListView.setAdapter(adapter);
        mOkHttpClient = new OkHttpClient();

    }

    private void doGet() throws Exception {
        // TODO Auto-generated method stub
        String url = "http://blog.ikabi.com:7070/spring_mvc/json";
        Request request = new Request.Builder().url(url).build();
        Response response = mOkHttpClient.newCall(request).execute();// execute
        if (response.isSuccessful()) {
            System.out.println(response.code());
            //System.out.println(response.body().string());
            Log.i("qiyue","response.code"+response.code());
            String body = response.body().string();
            Log.i("qiyue","body" + body);
            System.out.println(body);
            result = "doGet|"+response.code()+"|"+body;
            mHandler.sendEmptyMessage(MSG_WHAT);
        }
    }

    private void doGetAsync() {
        // TODO Auto-generated method stub
        String url = "http://blog.ikabi.com:7070/spring_mvc/json";
        Request request = new Request.Builder().url(url).build();
        // enqueue
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                // NOT UI Thread
                if (response.isSuccessful()) {
                    System.out.println(response.code());
                    //System.out.println(response.body().string());
                    String body=response.body().string();
                    System.out.println(body);
                    result = "doGetAsync|"+response.code()+"|"+body;
                    mHandler.sendEmptyMessage(MSG_WHAT);
                }

            }
        });
    }

    private void doPost() throws Exception {
        // TODO Auto-generated method stub
        RequestBody formBody = new FormEncodingBuilder().add("platform", "android").add("name", "bug")
                .add("subject", "XXXXXXXXXXXXXXX").build();

        //当写请求头的时候，使用 header(name, value) 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value) 可以添加多值（添加，不移除已有的）。
        Request request = new Request.Builder().url(url).header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5").addHeader("Accept", "application/vnd.github.v3+json")
                .post(formBody).build();

        Response response = mOkHttpClient.newCall(request).execute();// execute
        if (response.isSuccessful()) {
            System.out.println(response.code());
            //System.out.println(response.body().string());
            String body=response.body().string();
            System.out.println(body);
            result="doPost|"+response.code()+"|"+body;
            mHandler.sendEmptyMessage(MSG_WHAT);

        }
    }

    private void doPostAsync() {
        // TODO Auto-generated method stub
        RequestBody formBody = new FormEncodingBuilder().add("platform", "android").add("name", "bug")
                .add("subject", "XXXXXXXXXXXXXXX").build();

        Request request = new Request.Builder().url(url).header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5").addHeader("Accept", "application/vnd.github.v3+json")
                .post(formBody).build();

        // enqueue
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                // NOT UI Thread
                if (response.isSuccessful()) {
                    System.out.println(response.code());

                    //System.out.println(response.body().string());
                    String body=response.body().string();
                    System.out.println(body);
                    result="doPostAsync|"+response.code()+"|"+body;
                    mHandler.sendEmptyMessage(MSG_WHAT);
                }

            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OkHttpTech okHttpTech = (OkHttpTech)parent.getItemAtPosition(position);
        int okHttpTechId = okHttpTech.getId();
        switch (okHttpTechId){
            case 0:
                new Thread(new  Runnable() {
                    public void run() {
                        try {
                            doGet();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case 1:
                doGetAsync();
                break;
            case 2:
                new Thread(new  Runnable() {
                    public void run() {
                        try {
                            doPost();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case 3:
                doPostAsync();
                break;
            default:
                break;

        }

    }
}
