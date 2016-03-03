package com.ustory.techbox.model;

import com.ustory.techbox.bean.User;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/3
 */
public interface IUserModel {

    public void login(String username, String password, OnLoginListener loginListener);

    interface OnLoginListener
    {
        void loginSuccess(User user);

        void loginFailed();
    }
}
