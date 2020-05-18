package com.imooc_res.utils;

import com.google.gson.Gson;

public class GsonUtils {

    public static Gson mGson = new Gson();
    public static Gson getGson(){
        return mGson;
    }

}
