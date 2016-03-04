package com.ustory.techbox.presenter;

import android.os.Handler;

import com.ustory.techbox.bean.ITTech;
import com.ustory.techbox.core.mvp.BasePresenter;
import com.ustory.techbox.iviews.ITTechView;
import com.ustory.techbox.model.IITTechModel;
import com.ustory.techbox.model.ITTechModel;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/4
 */
public class NewTechPresenter extends BasePresenter<ITTechView> {
    private ITTechModel iTtechModel;
    private Handler mHandler;
    public NewTechPresenter() {
        this.iTtechModel = new ITTechModel();
        mHandler = new Handler();
    }

    public void findAllItTech(){
        iTtechModel.queryAllItTech(new IITTechModel.ITTechListener(){

            @Override
            public void showData(List<ITTech> list) {
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
