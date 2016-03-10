package com.ustory.techbox.views;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.test2.R;
import com.ustory.techbox.adapter.MultipleItemAdapter;
import com.ustory.techbox.bean.ITTech;
import com.ustory.techbox.core.BaseAppCompatActivity;

import java.util.ArrayList;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/9
 */
public class NewMessageDetailActivity extends BaseAppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.new_message_detail_activity;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initViews(Bundle savedInstanceState) {
       // ArrayList<String> mTitles=new ArrayList<String>();
        ArrayList<ITTech> itTeches = new ArrayList<ITTech>();
        for(int i=0;i<10;i++){
            itTeches.add(new ITTech());
        }

        mRecyclerView=(RecyclerView) findViewById(R.id.new_message_detail_activity);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MultipleItemAdapter adapter = new MultipleItemAdapter(this, itTeches);
        mRecyclerView.setAdapter(adapter);


    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        AppBarLayout appBarLayout = findView(R.id.include_toolbar);
        Toolbar mToolbar = (Toolbar) appBarLayout.findViewById(R.id.global_toolbar);
        mToolbar.setTitle("详细内容页");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }
}
