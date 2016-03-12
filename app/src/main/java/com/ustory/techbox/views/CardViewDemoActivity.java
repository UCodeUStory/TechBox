package com.ustory.techbox.views;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.test2.R;
import com.ustory.techbox.core.BaseAppCompatActivity;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/9
 */
public class CardViewDemoActivity extends BaseAppCompatActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.card_view_demo_activity;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        AppBarLayout appBarLayout = findView(R.id.include_toolbar);
        Toolbar mToolbar = (Toolbar) appBarLayout.findViewById(R.id.global_toolbar);
        mToolbar.setTitle("CardViewDemo");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
