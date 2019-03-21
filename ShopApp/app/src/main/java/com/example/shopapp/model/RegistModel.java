package com.example.shopapp.model;

import com.example.shopapp.api.Api;
import com.example.shopapp.api.ApiService;
import com.example.shopapp.bean.LoginBean;
import com.example.shopapp.bean.RegistBean;
import com.example.shopapp.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class RegistModel {

    public void getRegistModel(String phone,String pwd){
        ApiService apiService = RetrofitUtils.getRetrofit(Api.Base_Url).create(ApiService.class);
        Flowable<RegistBean> regist = apiService.getRegist(phone, pwd);
        regist.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RegistBean>() {
                    @Override
                    public void onNext(RegistBean bean) {
                        if(registListener!=null){
                            registListener.onRegist(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface onRegistListener{
        void onRegist(RegistBean bean);
    }
    onRegistListener registListener;

    public void setRegistListener(onRegistListener registListener) {
        this.registListener = registListener;
    }
}
