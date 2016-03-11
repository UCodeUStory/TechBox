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

        ArrayList<String> strings = new ArrayList<String>();
        for (int i=0;i<4;i++){
            strings.add("item"+i);
        }
        ITTech itTech = new ITTech();
        itTech.setType(ITTech.HADER);
        itTech.setDetailHeaderTitle("这是一项新技术");
        itTech.setDetailHeaderContent("PPPPPPPPPPP");
        itTeches.add(itTech);

        ITTech itTech2 = new ITTech();
        itTech2.setType(ITTech.ITEM);
        itTech2.setDetailContentHeader("Android");
        itTech2.setDetailContentItems(strings);

        ITTech itTech3 = new ITTech();
        itTech3.setType(ITTech.ITEM);
        itTech3.setDetailContentHeader("IOS");
        itTech3.setDetailContentItems(strings);

        ITTech itTech4 = new ITTech();
        itTech4.setType(ITTech.ITEM);
        itTech4.setDetailContentHeader("WEB");
        itTech4.setDetailContentItems(strings);

        itTeches.add(itTech2);
        itTeches.add(itTech3);
        itTeches.add(itTech4);

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
