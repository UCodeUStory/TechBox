package com.ustory.techbox.iviews;

import com.ustory.techbox.bean.AndroidTech;
import com.ustory.techbox.core.mvp.MvpView;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/8
 */
public interface IAndroidTechView extends MvpView {
    void querySuccess(List<AndroidTech> androidTeches);

    void delete(AndroidTech androidTech);

    boolean add(AndroidTech androidTech);

    void update(AndroidTech androidTech);
}
