package com.ustory.techbox.model;

import com.ustory.techbox.bean.ITTech;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/2
 */
public interface IITTechModel {

    void add (ITTech itTech ,ITTechListener listener);

    void delete (ITTech itTech,ITTechListener listener);

    void modify (Long itTechId, ITTechListener listener);


    void queryItTechById (Long itTechId, ITTechListener listener);

    List<ITTech> queryAllItTech (ITTechListener listener);

    interface ITTechListener
    {
        void showData(List<ITTech> list);

        void success();

        void failed();
    }
}
