package com.example.shopapp.presenter;

import com.example.shopapp.bean.RegistBean;
import com.example.shopapp.model.RegistModel;
import com.example.shopapp.view.IRegistView;

public class RegistPresenter {
    private IRegistView iRegistView;
    private   RegistModel registModel;

    public RegistPresenter(IRegistView iRegistView) {
        this.iRegistView = iRegistView;
        registModel = new RegistModel();
    }
    public void getRegistPresenter(String phone,String pwd){
        registModel.getRegistModel(phone, pwd);
        registModel.setRegistListener(new RegistModel.onRegistListener() {
            @Override
            public void onRegist(RegistBean bean) {
                iRegistView.getRegistView(bean);
            }
        });
    }
}
