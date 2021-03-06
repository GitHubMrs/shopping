package com.newbigmap.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface API {
/*
    @GET("/app/user/login/{account}/{pswd}/{systemType}")
    Call<ResponseBody> login(@Path("account") String account,
                             @Path("pswd") String pswd,
                             @Path("systemType") String systemType);*/

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> login(
            @Field("account")String account,
            @Field("pswd")String pswd,
            @Field("systemType")String systemType);



    //指定get请求方式  指定路径 有时候路径除了baseUrl还有一部分比如 http://write.blog.csdn.net/mdeditor
    //http://write.blog.csdn.net/ 一般是baseUrl
    //而 mdeditor是相对路径的
    @GET
    Call<String> baidu(@Url String url);

}
