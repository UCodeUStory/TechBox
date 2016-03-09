package com.ustory.techbox.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.test2.R;
import com.ustory.techbox.core.BaseAppCompatActivity;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/9
 */
public class ToolsBarDemoActivity extends BaseAppCompatActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.toolsbar_demo_activity;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.demo_toolbar );
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("Title");
        mToolbar.setSubtitle("subtitle");
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        mToolbar.setLogo(R.drawable.test_logo);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // toolbar.setSubtitle("鍓爣棰�);
        //setSupportActionBar(mToolbar);

        // getSupportActionBar().setTitle("鏍囬");
        // getSupportActionBar().setSubtitle("鍓爣棰�);
        // getSupportActionBar().setLogo(R.drawable.ic_launcher);


        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        showToast("click action_settings");
                        break;
                    case R.id.action_share:
                        showToast("click share");
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
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
