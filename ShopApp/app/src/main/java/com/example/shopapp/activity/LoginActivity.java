package com.example.shopapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopapp.R;
import com.example.shopapp.api.Api;
import com.example.shopapp.bean.LoginBean;
import com.example.shopapp.presenter.LoginPresenter;
import com.example.shopapp.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements IView {

 private boolean eye=true;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eyes)
    ImageView eyes;
    @BindView(R.id.check_jz)
    CheckBox checkJz;
    @BindView(R.id.resign)
    TextView resign;
    @BindView(R.id.login)
    Button login;
   private  SharedPreferences sharedPreferences;
  private SharedPreferences.Editor editor;
    private LoginPresenter loginPresenter;
    private boolean jz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //创建sp
       sharedPreferences = getSharedPreferences("User", 0);
       editor = sharedPreferences.edit();
        jz = sharedPreferences.getBoolean("jz", false);
        if(jz){
            String phone1 = sharedPreferences.getString("phone", null);
            String pwd1 = sharedPreferences.getString("pwd", null);
            phone.setText(phone1);
            pwd.setText(pwd1);
            checkJz.setChecked(true);
        }
        editor.commit();

        loginPresenter = new LoginPresenter(this);


    }


    @OnClick({R.id.phone, R.id.pwd, R.id.eyes, R.id.check_jz, R.id.resign, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phone:
                break;
            case R.id.pwd:
                break;
            case R.id.eyes:
                if(eye){
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye=false;
                }else {
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                     eye=true;
                }
                break;
            case R.id.check_jz:
                break;
            case R.id.resign:
                Intent intent = new Intent(LoginActivity.this,RegistActivity.class);
                this.startActivity(intent);
                break;
            case R.id.login:
                //获取输入框的值
                String phones = phone.getText().toString();
                String pwds = pwd.getText().toString();
                if(checkJz.isChecked()){
                    editor.putString("phone",phones);
                    editor.putString("pwd",pwds);
                    editor.putBoolean("jz",true);
                     editor.commit();
                }else {
                    editor.clear();
                }
                loginPresenter.getLoginPresenter(phones,pwds);

                break;
        }

    }
    @Override
    public void getLoginView(LoginBean.ResultBean bean) {
        if(bean instanceof LoginBean.ResultBean){
           Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
           startActivity(new Intent(LoginActivity.this,ShowActivity.class));

        }else {
            Toast.makeText(this,"登陆失败",Toast.LENGTH_SHORT).show();
        }

    }
}
