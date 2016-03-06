package com.ustory.techbox.presenter;

import android.os.Handler;

import com.ustory.techbox.bean.ITTech;
import com.ustory.techbox.bean.VolleyTech;
import com.ustory.techbox.core.mvp.BasePresenter;
import com.ustory.techbox.iviews.VolleyView;
import com.ustory.techbox.model.IITTechModel;
import com.ustory.techbox.model.ITTechModel;
import com.ustory.techbox.model.IVolleyModel;
import com.ustory.techbox.model.VolleyModel;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/6
 */
public class VolleyTechPresenter extends BasePresenter<VolleyView> {
    private VolleyModel mVolleyModel;
    private Handler mHandler;
    public VolleyTechPresenter() {
        this.mVolleyModel = new VolleyModel();
        mHandler = new Handler();
    }

    public void findAllVolleyTech(){
        mVolleyModel.queryAllVolleyTech(new IVolleyModel.VolleyTechListener() {
            @Override
            public void showData(List<VolleyTech> list) {
                mMvpView.querySuccess(list);
            }

            @Override
            public void success() {

            }

            @Override
            public void failed() {

            }
        });


    }

}
