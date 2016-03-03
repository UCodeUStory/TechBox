package com.ustory.techbox.model;

import com.ustory.techbox.bean.ITTech;

import java.util.LinkedList;
import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/2
 */
public class ITTechModelImpl implements ITTechModel {
    private ITTechModelCallBack callBack;

    /**
     * 添加Topic
     *
     * @param itTech
     */
    @Override
    public void add(ITTech itTech) {
        // TODO ...网络请求后...

        // TODO 返回数据 这里默认为返回原数据
        this.callBack.addSuccess(itTech);
    }

    /**
     * 删除主题
     *
     * @param itTech
     */
    @Override
    public void del(ITTech itTech) {
        // TODO ...网络请求后...

        // TODO 返回数据 这里默认为返回原数据
        this.callBack.delSuccess( itTech);
    }

    /**
     * 修改主题
     *
     * @param topicId
     * @param itTech
     */
    @Override
    public void mod(Long topicId, ITTech itTech) {
        // TODO ...网络请求后...

        // TODO 返回数据 这里默认为返回原数据
        this.callBack.modSuccess(itTech);
    }

    /**
     * 查询主题
     *
     * @return
     */
    @Override
    public void query() {
        // TODO ...网络请求后...

        // TODO 返回数据
        ITTech itTech = new ITTech();
        List<ITTech> list = new LinkedList<ITTech>();
        list.add(itTech);
        this.callBack.querySuccess(list);
    }

    public abstract class ITTechModelCallBack {
        /**
         * 添加成功
         *
         * @param topic
         */
        public void addSuccess(ITTech topic) {
        }

        /**
         * 删除成功
         *
         * @param topic
         */
        public void delSuccess(ITTech topic) {
        }

        /**
         * 修改成功
         *
         * @param newTopic
         */
        public void modSuccess(ITTech newTopic) {
        }

        /**
         * 查询成功
         *
         * @return
         */
        public void querySuccess(List<ITTech> topics) {
        }

        /**
         * 发生错误
         */
        public abstract void error();
    }
}
