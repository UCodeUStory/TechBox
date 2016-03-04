package com.ustory.techbox.custom;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/3
 */
public class URecyclerView extends RecyclerView {
    public URecyclerView(Context context) {
        super(context);
        this.initRecyclerView(context);
    }

    public URecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initRecyclerView(context);
    }

    public URecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.initRecyclerView(context);
    }

    /**
     * Init the recycler view
     *
     * @param context context
     */
    private void initRecyclerView(Context context) {
        // init LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        // set the VERTICAL layout
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // set layout manager
        this.setLayoutManager(linearLayoutManager);
        // set item animator
        this.setItemAnimator(new DefaultItemAnimator());
        // keep recyclerview fixed size
        this.setHasFixedSize(true);
    }
}