package com.imooc_res;

import android.text.TextUtils;

import com.imooc_res.bean.UserBean;
import com.imooc_res.utils.SPUtils;

public class UserInfoHolder {

    private static UserInfoHolder mInstance = new UserInfoHolder();
    private UserBean mUser;

    private static final String KEY_USERNAME = "key_username";
    private static final String KEY_PASSWORD = "key_password";

    public static UserInfoHolder getInstance() {

        return mInstance;

    }

    public void setUser(UserBean user) {
        mUser = user;
        if (user != null) {
            SPUtils.getInstance().put(KEY_USERNAME, mUser.getUserName());
            SPUtils.getInstance().put(KEY_PASSWORD, mUser.getPassword());
        }
    }

    public UserBean getUser() {

        UserBean userBean = mUser;
        if (userBean == null) {

            String username = (String) SPUtils.getInstance().get("KEY_USERNAME","");
            String password = (String) SPUtils.getInstance().get("KEY_PASSWORD","");
            if (!TextUtils.isEmpty(username)||!TextUtils.isEmpty(password)) {
                userBean = new UserBean();
                userBean.setUserName(username);
                userBean.setPassword(password);
            }

        }
        mUser = userBean;
        return userBean;
    }


}
