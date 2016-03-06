package com.ustory.techbox.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/6
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;
    protected int layoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(layoutId,container,false);
        initView(rootView);
        initListener();
        initData();
        return rootView;
    }

    protected void setRootView(int layoutId){
        this.layoutId = layoutId;
    }

    protected abstract void initView(View rootView);

    protected abstract void initListener();

    protected abstract void initData();

    protected <V extends View> V $(int id) {
        return (V) rootView.findViewById(id);
    }
}
