package com.example.shopapp.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils instance;
    private  RetrofitUtils(){}
    public static RetrofitUtils getInstance(){
        if(instance==null){
            synchronized (RetrofitUtils.class){
               if(instance==null){
                   instance=new RetrofitUtils();
               }
            }
        }
        return instance;
    }
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(String Base_Url){
        retrofit = new Retrofit.Builder()
                .baseUrl(Base_Url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        return RetrofitUtils.retrofit;
    }
    //封装ok加拦截器
    private static OkHttpClient okHttpClient;
    private static synchronized OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
        return okHttpClient;
    }


    public <T> T doPost(String Base_Url,Class<T> apiService){
        Retrofit retrofit = getRetrofit(Base_Url);
        T t = retrofit.create(apiService);
        return t;
    }
}
