package com.ustory.techbox.presenter;

import android.os.Handler;

import com.ustory.techbox.bean.User;
import com.ustory.techbox.core.mvp.BasePresenter;
import com.ustory.techbox.iviews.MainView;
import com.ustory.techbox.model.IUserModel;
import com.ustory.techbox.model.UserModel;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/2
 */
public class MainPresenter extends BasePresenter<MainView> {
    private UserModel userModel;
    private Handler mHandler;
    public MainPresenter() {
        this.userModel = new UserModel();
        mHandler = new Handler();
    }

    /**
     * This is controller invoke function to handle service,
     * activity is handler view;
     * model is handler data;
     * presenter handler view and data interaction
     */
    public void login()
    {
       // userLoginView.showLoading();
        userModel.login(mMvpView.getUserName(), mMvpView.getPassword(), new IUserModel.OnLoginListener()
        {
            @Override
            public void loginSuccess(final User user)
            {
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mMvpView.toWelcomexActivity(user);
                        mMvpView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed()
            {
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mMvpView.onFailure("用户名密码错误");
                        mMvpView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear()
    {
        mMvpView.clearUserName();
        mMvpView.clearPassword();
    }



}
