package com.example.shopapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopapp.R;
import com.example.shopapp.api.Api;
import com.example.shopapp.bean.RegistBean;
import com.example.shopapp.presenter.RegistPresenter;
import com.example.shopapp.view.IRegistView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends AppCompatActivity implements IRegistView {
    @BindView(R.id.regist_login)
    TextView registLogin;
    @BindView(R.id.btn_Regist)
    Button btnRegist;
    private boolean eye = false;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.yz)
    EditText yz;
    @BindView(R.id.hqyz)
    TextView hqyz;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eyes)
    ImageView eyes;
  private  RegistPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resig);
        ButterKnife.bind(this);
        presenter = new RegistPresenter(this);
    }

    @OnClick({R.id.phone, R.id.yz, R.id.hqyz, R.id.pwd, R.id.eyes,R.id.regist_login,R.id.btn_Regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phone:
                break;
            case R.id.yz:
                break;
            case R.id.hqyz:
                break;
            case R.id.pwd:
                break;
            case R.id.eyes:
                if (eye) {
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye = false;
                } else {
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eye = true;
                }
                break;
            case R.id.regist_login:
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;
            case R.id.btn_Regist:

                String phones = phone.getText().toString().trim();
                String pwds = pwd.getText().toString().trim();
                presenter.getRegistPresenter(phones,pwds);
                break;
        }
    }

    @Override
    public void getRegistView(RegistBean bean) {
        if (bean instanceof RegistBean) {
            RegistBean bean1 = bean;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (bean.getMessage().equals("注册成功")) {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        }
    }
}
