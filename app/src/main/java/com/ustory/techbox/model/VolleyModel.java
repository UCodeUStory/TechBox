package com.ustory.techbox.model;

import com.ustory.techbox.bean.VolleyTech;
import com.ustory.techbox.test.DataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/6
 */
public class VolleyModel implements IVolleyModel {
    @Override
    public void add(VolleyTech volleyTech, VolleyTechListener listener) {

    }

    @Override
    public void delete(VolleyTech itTech, VolleyTechListener listener) {

    }

    @Override
    public void modify(Long itTechId, VolleyTechListener listener) {

    }

    @Override
    public void queryItTechById(Long itTechId, VolleyTechListener listener) {

    }

    @Override
    public List<VolleyTech> queryAllVolleyTech(VolleyTechListener listener) {
        List<VolleyTech> list = new ArrayList<VolleyTech>();
        /**
         * 异步线程网络请求
         */
        for (int i = 0; i< DataProvider.volleyContent.length; i++){
            VolleyTech volleyTech = new VolleyTech();
            volleyTech.setId(DataProvider.ids[i]);
            volleyTech.setContent(DataProvider.volleyContent[i]);
            list.add(volleyTech);
        }
        if (list!=null)
        {
            listener.showData(list);
        } else {
            listener.failed();
        }
        return list;
    }
}
