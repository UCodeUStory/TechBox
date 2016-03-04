package com.ustory.techbox.iviews;

import com.ustory.techbox.bean.ITTech;
import com.ustory.techbox.core.mvp.MvpView;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Date:2016/3/2
 */
public interface ITTechView extends MvpView {

    void querySuccess(List<ITTech> itTech);

    void error();
}
