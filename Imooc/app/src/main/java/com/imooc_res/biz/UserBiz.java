package com.imooc_res.biz;

import com.imooc_res.bean.UserBean;
import com.imooc_res.config.Config;
import com.imooc_res.net.CommonCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.OkHttpClient;

public class UserBiz {

    public void login(String username, String password, CommonCallback<UserBean> commonCallback){


        OkHttpUtils
                .post()
                .url(Config.BASE_URL+"user_login")
                .tag(this)
                .addParams("username",username)
                .addParams("password",password)
                .build()
                .execute(commonCallback);

    }

    public void register(String username, String password, CommonCallback<UserBean> commonCallback){

        OkHttpUtils
                .post()
                .url(Config.BASE_URL+"user_register")
                .tag(this)
                .addParams("username",username)
                .addParams("password",password)
                .build()
                .execute(commonCallback);


    }

    public void onDestory() {
        OkHttpUtils.getInstance().cancelTag(this);
    }

}
