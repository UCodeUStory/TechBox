package com.ustory.techbox.model;

import com.ustory.techbox.bean.VolleyTech;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/6
 */
public interface IVolleyModel {
    void add (VolleyTech volleyTech , VolleyTechListener listener);

    void delete (VolleyTech itTech,VolleyTechListener listener);

    void modify (Long itTechId, VolleyTechListener listener);

    void queryItTechById (Long itTechId, VolleyTechListener listener);

    List<VolleyTech> queryAllVolleyTech (VolleyTechListener listener);

    interface VolleyTechListener
    {
        void showData(List<VolleyTech> list);

        void success();

        void failed();
    }
}
