package com.imooc_res.biz;

import com.imooc_res.bean.Order;
import com.imooc_res.bean.Product;
import com.imooc_res.config.Config;
import com.imooc_res.net.CommonCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by zhy on 16/10/23.
 */
public class OrderBiz {

    public void listByPage(int currentPage, CommonCallback<List<Order>> callback) {
        OkHttpUtils
                .post()
                .url(Config.BASE_URL + "order_find")
                .tag(this)
                .addParams("currentPage", currentPage + "")
                .build()
                .execute(callback);
    }


    public void add(Order order, CommonCallback callback) {

        Map<Product, Integer> productsMap = order.productsMap;
        StringBuilder sb = new StringBuilder();
        for (Product p : productsMap.keySet()) {
            sb.append(p.getId() + "_" + productsMap.get(p));
            sb.append("|");
        }
        sb = sb.deleteCharAt(sb.length() - 1);

        OkHttpUtils
                .post()
                .url(Config.BASE_URL + "order_add")
                .addParams("res_id", order.getRestaurant().getId() + "")
                .addParams("product_str", sb.toString())
                .addParams("count", order.getCount() + "")
                .addParams("price", order.getPrice() + "")
                .tag(this)
                .build()
                .execute(callback);
    }

}
