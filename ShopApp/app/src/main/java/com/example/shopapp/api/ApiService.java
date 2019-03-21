package com.example.shopapp.api;

import com.example.shopapp.bean.LoginBean;
import com.example.shopapp.bean.RegistBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    //登陆post请求
     @POST("v1/login")
     @FormUrlEncoded
      Flowable<LoginBean>getLogin(@Field("phone") String phone,@Field("pwd") String pwd);
    @POST("v1/register")
    @FormUrlEncoded
    Flowable<RegistBean>getRegist(@Field("phone") String phone, @Field("pwd") String pwd);
}
