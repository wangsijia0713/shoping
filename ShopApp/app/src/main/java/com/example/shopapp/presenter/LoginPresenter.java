package com.example.shopapp.presenter;

import com.example.shopapp.bean.LoginBean;
import com.example.shopapp.model.LoginModel;
import com.example.shopapp.view.IView;

public class LoginPresenter {
    private IView iView;
    private LoginModel loginModel;

    public LoginPresenter(IView iView) {
        this.iView = iView;
       loginModel = new LoginModel();
    }
    public void getLoginPresenter(String phone,String pwd){
        loginModel.getLoginModel(phone,pwd);
        loginModel.setLoginModelListener(new LoginModel.onLoginModelListener() {
            @Override
            public void getLoginModel(LoginBean.ResultBean bean) {
                iView.getLoginView(bean);
            }
        });
    }
}
