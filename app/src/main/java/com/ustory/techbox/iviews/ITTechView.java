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
    /**
     * 添加成功
     * @param itTech
     */
    void addSuccess(ITTech itTech);

    /**
     * 删除成功
     * @param itTech
     */
    void delSuccess(ITTech itTech);

    /**
     * 修改成功
     * @param itTech
     */
    void modSuccess(ITTech itTech);

    /**
     * 查询成功
     * @return
     */
    void querySuccess(List<ITTech> itTech);

    /**
     * 发生错误
     */
    void error();
}
