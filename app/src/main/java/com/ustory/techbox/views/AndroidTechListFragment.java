package com.ustory.techbox.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.test2.R;
import com.ustory.techbox.adapter.AndroidNewTechAdapter;
import com.ustory.techbox.bean.AndroidTech;
import com.ustory.techbox.core.BaseFragment;
import com.ustory.techbox.iviews.IAndroidTechView;
import com.ustory.techbox.presenter.AndroidTechPresenter;
import com.ustory.techbox.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/8
 */
public class AndroidTechListFragment extends BaseFragment implements IAndroidTechView {

    private RecyclerView mRecyclerView;
    private AndroidNewTechAdapter adapter;
    private List<AndroidTech>list = new ArrayList<AndroidTech>();
    private AndroidNewTechAdapter.RecycleItemClickListener mItemClickListener;
    private AndroidTechPresenter mAndroidTechPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRootView(R.layout.android_tech_list_fragment);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(View rootView) {
        mRecyclerView = $(R.id.atlf_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mItemClickListener = new AndroidNewTechAdapter.RecycleItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //TODO
                L.i("qiyue","view.getId()="+position);
                AndroidTech androidTech = adapter.getAndroidTechs().get(position);
                Intent intent;
                switch (androidTech.getId()){
                    case 0:
                         intent = new Intent(getActivity(),ToolsBarDemoActivity.class);
                         startActivity(intent);
                         break;
                    case 1:
                         intent = new Intent(getActivity(),CardViewDemoActivity.class);
                         startActivity(intent);
                         break;
                    case 2:
                        intent = new Intent(getActivity(),RecycleViewDemoActivity.class);
                        startActivity(intent);
                         break;
                    case 3:
                       // intent = new Intent(getActivity(),PullRefreshActivity.class);
                        //startActivity(intent);
                    default:
                         break;
                }
            }
        };
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        L.i("qiyue","initData");
        adapter = new AndroidNewTechAdapter(list,mItemClickListener);
        mRecyclerView.setAdapter(adapter);
        this.mAndroidTechPresenter = new AndroidTechPresenter();
        this.mAndroidTechPresenter.attachView(this);
        this.mAndroidTechPresenter.findAllAndoroidTech();
    }

    @Override
    public void querySuccess(List<AndroidTech> androidTeches) {

        //adapter = new AndroidNewTechAdapter(androidTeches,mItemClickListener);
        //mRecyclerView.setAdapter(adapter);
        L.i("qiyue","AndroidTech");
        adapter.setAndroidTechs(androidTeches);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void delete(AndroidTech androidTech) {

    }

    @Override
    public boolean add(AndroidTech androidTech) {
        return false;
    }

    @Override
    public void update(AndroidTech androidTech) {

    }

    @Override
    public void onFailure(String failedMessage) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mAndroidTechPresenter.detachView();
    }
}
