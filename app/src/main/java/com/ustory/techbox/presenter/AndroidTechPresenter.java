package com.ustory.techbox.presenter;

import android.os.Handler;

import com.ustory.techbox.bean.AndroidTech;
import com.ustory.techbox.core.mvp.BasePresenter;
import com.ustory.techbox.iviews.IAndroidTechView;
import com.ustory.techbox.model.AndroidTechModel;
import com.ustory.techbox.model.IAndroidTechModel;
import com.ustory.techbox.utils.L;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/8
 */
public class AndroidTechPresenter extends BasePresenter<IAndroidTechView> {
    private AndroidTechModel mAndroidTechModel;
    private Handler mHandler;
    public AndroidTechPresenter() {
        this.mAndroidTechModel = new AndroidTechModel();
        mHandler = new Handler();
    }

    public void findAllAndoroidTech(){
        mAndroidTechModel.queryAllAndroidTechModel(new IAndroidTechModel.AndroidTechListener() {
            @Override
            public void showData(List<AndroidTech> list) {
                L.i("qiyue","queryAll");
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
