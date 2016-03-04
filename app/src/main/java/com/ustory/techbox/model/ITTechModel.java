package com.ustory.techbox.model;

import com.ustory.techbox.bean.ITTech;
import com.ustory.techbox.test.DataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/2
 */
public class ITTechModel implements IITTechModel {

    @Override
    public void add(ITTech itTech, ITTechListener listener) {

    }

    @Override
    public void delete(ITTech itTech, ITTechListener listener) {

    }

    @Override
    public void modify(Long itTechid, ITTechListener listener) {

    }

    @Override
    public void queryItTechById(Long itTechId, ITTechListener listener) {

    }

    @Override
    public List<ITTech> queryAllItTech(ITTechListener listener) {
        List<ITTech> list = new ArrayList<ITTech>();
        /**
         * 异步线程网络请求
         */
        for (int i = 0; i< DataProvider.iTTechCount; i++){
            ITTech itTech = new ITTech();
            itTech.setId(i);
            itTech.setContent(DataProvider.content[i]);
            //itTech.setImage();
            itTech.setSimilar_key_0(DataProvider.similar_key_0[i]);
            itTech.setSimilar_key_1(DataProvider.similar_key_1[i]);
            itTech.setDate(DataProvider.dates[i]);
            list.add(itTech);
        }
        if (list!=null)
        {
            listener.showData(list);
        } else {
            listener.failed();
        }
        return null;
    }


}
