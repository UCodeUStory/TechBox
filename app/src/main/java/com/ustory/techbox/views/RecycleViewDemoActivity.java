package com.ustory.techbox.views;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.administrator.test2.R;
import com.ustory.techbox.adapter.DividerGridItemDecoration;
import com.ustory.techbox.adapter.HomeAdapter;
import com.ustory.techbox.core.BaseAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/9
 */
public class RecycleViewDemoActivity extends BaseAppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String>mDatas;
    private HomeAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.recycler_view_demo_activity;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mRecyclerView = findView(R.id.demo_recycler_view);
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
        mAdapter = new HomeAdapter(this, mDatas);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

        mAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
               showToast("click");
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                showToast("long click");
            }
        });
    }

    @Override
    protected void initData() {
    }
}
