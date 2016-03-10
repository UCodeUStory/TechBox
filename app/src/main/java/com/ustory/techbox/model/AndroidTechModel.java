package com.ustory.techbox.model;

import com.ustory.techbox.bean.AndroidTech;
import com.ustory.techbox.test.DataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/8
 */
public class AndroidTechModel implements IAndroidTechModel {
    @Override
    public void add(AndroidTech androidTech, AndroidTechListener listener) {

    }

    @Override
    public void delete(AndroidTech androidTech, AndroidTechListener listener) {

    }

    @Override
    public void modify(Long androidTechId, AndroidTechListener listener) {

    }

    @Override
    public void queryItTechById(Long androidTechId, AndroidTechListener listener) {

    }

    @Override
    public List<AndroidTech> queryAllAndroidTechModel(AndroidTechListener listener) {
        List<AndroidTech> list = new ArrayList<AndroidTech>();
        /**
         * 异步线程网络请求
         */
        for (int i=0;i< DataProvider.AndroidNewTechContent.length;i++){
            AndroidTech androidTech = new AndroidTech();
            androidTech.setId(i);
            androidTech.setContent(DataProvider.AndroidNewTechContent[i]);
            androidTech.setTag(DataProvider.type[i]);
            androidTech.setDate(DataProvider.androidTechDate[i]);
            list.add(androidTech);

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
