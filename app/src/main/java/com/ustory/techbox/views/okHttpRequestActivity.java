package com.ustory.techbox.views;

import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.test2.R;
import com.ustory.techbox.core.BaseAppCompatActivity;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/18
 */
public class okHttpRequestActivity extends BaseAppCompatActivity {
    private TextView mTextView;
    @Override
    protected int getLayoutId() {
        return R.layout.ok_http_request_activity;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        this.mTextView = findView(R.id.content);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
         String result = getIntent().getStringExtra("result");
         this.mTextView.setText(result);
    }
}
