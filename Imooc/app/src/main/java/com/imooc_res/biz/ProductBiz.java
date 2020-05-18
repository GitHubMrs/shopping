package com.imooc_res.biz;

import com.imooc_res.bean.Product;
import com.imooc_res.config.Config;
import com.imooc_res.net.CommonCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

public class ProductBiz {

    public void listByPage(int currentPage, CommonCallback<List<Product>> callback){

        OkHttpUtils.post()
                .url(Config.BASE_URL+"product_find")
                .addParams("currentPage",currentPage+"")
                .tag(this)
                .build()
                .execute(callback);
    }


    public void onDestroy(){

        OkHttpUtils.getInstance().cancelTag(this);

    }

}
