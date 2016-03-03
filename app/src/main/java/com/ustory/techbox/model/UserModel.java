package com.ustory.techbox.model;

import com.ustory.techbox.bean.User;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/3
 */
public class UserModel implements IUserModel{
    @Override
    public void login(String username, String password, OnLoginListener loginListener) {
        /**
         * 网络请求****
         */
        if ("ustory".equals(username) && "123456".equals(password))
        {
            User user = new User();
            user.setUserName(username);
            user.setPassword(password);
            loginListener.loginSuccess(user);
        } else {
            loginListener.loginFailed();
        }
    }
}
