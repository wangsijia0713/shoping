package com.example.shopapp.model;

import com.example.shopapp.api.Api;
import com.example.shopapp.api.ApiService;
import com.example.shopapp.bean.LoginBean;
import com.example.shopapp.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class LoginModel {
    public void getLoginModel(String phone,String pwd){
        ApiService apiService = RetrofitUtils.getInstance().doPost(Api.Base_Url, ApiService.class);
        Flowable<LoginBean> login = apiService.getLogin(phone, pwd);
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .subscribeWith(new DisposableSubscriber<LoginBean>() {
                     @Override
                     public void onNext(LoginBean loginBean) {
                         LoginBean.ResultBean result = loginBean.getResult();
                         if(loginModelListener!=null){
                             loginModelListener.getLoginModel(result);
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
    public interface onLoginModelListener{
        void getLoginModel(LoginBean.ResultBean bean);
    }
    onLoginModelListener loginModelListener;

    public void setLoginModelListener(onLoginModelListener loginModelListener) {
        this.loginModelListener = loginModelListener;
    }
}
