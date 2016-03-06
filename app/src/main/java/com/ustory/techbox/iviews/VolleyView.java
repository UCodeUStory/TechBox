package com.ustory.techbox.iviews;

import com.ustory.techbox.bean.VolleyTech;
import com.ustory.techbox.core.mvp.MvpView;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/6
 */
public interface VolleyView extends MvpView {

    void querySuccess(List<VolleyTech> volleyTeches);

    void delete(VolleyTech volleyTech);

    boolean add(VolleyTech volleyTech);

    void update(VolleyTech volleyTech);
}
