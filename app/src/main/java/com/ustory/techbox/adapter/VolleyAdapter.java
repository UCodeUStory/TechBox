package com.ustory.techbox.adapter;

import android.content.Context;

import com.example.administrator.test2.R;
import com.ustory.techbox.bean.VolleyTech;
import com.ustory.techbox.core.CommonAdapter;
import com.ustory.techbox.core.ViewHolder;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/6
 */
public class VolleyAdapter extends CommonAdapter<VolleyTech>
{


    public VolleyAdapter(Context context, List<VolleyTech> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);

    }

    @Override
    public void convert(ViewHolder viewHolder, VolleyTech item) {
        viewHolder.setText(R.id.content,item.getContent());
    }

}