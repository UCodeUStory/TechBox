package com.ustory.techbox.iviews;

import com.ustory.techbox.bean.User;
import com.ustory.techbox.core.mvp.MvpView;

/**
 * 这里定义的都是界面应用的功能
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Date:2016/3/2
 */
public interface MainView extends MvpView {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toWelcomexActivity(User user);


}
