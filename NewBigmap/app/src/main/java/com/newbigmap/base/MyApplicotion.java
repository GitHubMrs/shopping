package com.newbigmap.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class MyApplicotion extends Application {

    //ARouter调试开关
    private boolean isDebugARouter = true;


    @Override
    public void onCreate() {
        super.onCreate();

        if (isDebugARouter){
//下面两行必须写在init之前否则无效
            ARouter.openLog();
            //开启调试模式(如果在InstantRun模式下运营,必须开启调试模式
            //线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }

        ARouter.init(MyApplicotion.this);

    }
}
