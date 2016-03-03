package com.ustory.techbox.model;

import com.ustory.techbox.bean.ITTech;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/2
 */
public interface ITTechModel {
    /**
     * 添加Topic
     *
     * @param topic
     */
    void add( ITTech itTech);

    /**
     * 删除主题
     *
     * @param itTech
     */
    void del( ITTech itTech);

    /**
     * 修改主题
     *
     * @param topicId
     * @param itTech
     */
    void mod(Long topicId, ITTech itTech);

    /**
     * 查询主题
     *
     * @return
     */
    void query();
}
