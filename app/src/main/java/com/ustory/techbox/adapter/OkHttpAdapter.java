package com.ustory.techbox.adapter;

import android.content.Context;

import com.example.administrator.test2.R;
import com.ustory.techbox.bean.OkHttpTech;
import com.ustory.techbox.core.CommonAdapter;
import com.ustory.techbox.core.ViewHolder;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/18
 */
public class OkHttpAdapter extends CommonAdapter<OkHttpTech> {


    public OkHttpAdapter(Context context, List<OkHttpTech> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, OkHttpTech okHttpTech) {
        holder.setText(R.id.content,okHttpTech.getContent());
    }
}
