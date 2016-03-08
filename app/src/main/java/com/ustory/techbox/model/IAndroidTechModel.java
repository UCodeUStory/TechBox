package com.ustory.techbox.model;

import com.ustory.techbox.bean.AndroidTech;
import com.ustory.techbox.bean.VolleyTech;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/6
 */
public interface IAndroidTechModel {
    void add(AndroidTech androidTech, AndroidTechListener listener);

    void delete(AndroidTech androidTech, AndroidTechListener listener);

    void modify(Long androidTechId, AndroidTechListener listener);

    void queryItTechById(Long androidTechId, AndroidTechListener listener);

    List<AndroidTech> queryAllAndroidTechModel(AndroidTechListener listener);

    interface AndroidTechListener
    {
        void showData(List<AndroidTech> list);

        void success();

        void failed();
    }
}
