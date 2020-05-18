package com.imooc_res.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.imooc_res.R;
import com.imooc_res.UserInfoHolder;
import com.imooc_res.bean.UserBean;
import com.imooc_res.biz.UserBiz;
import com.imooc_res.net.CommonCallback;
import com.imooc_res.utils.T;

public class RegisterActivity extends BaseActivity {

    private EditText mPhoneNumber, mPassword, mRePassword;
    private Button mBtRegister;

    private UserBiz mUserBiz = new UserBiz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpToolbar();
        initView();
        initEvent();
        setTitle("注册");
    }


    private void initEvent() {
        mBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = mPhoneNumber.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String repassword = mRePassword.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
                    T.showToast("账号和密码都不能为空!");
                    return;
                }

                if (!password.equals(repassword)) {
                    T.showToast("两次输入的密码不一致!");
                    return;
                }


                startLodingProgress();


                mUserBiz.register(username, password, new CommonCallback<UserBean>() {
                    @Override
                    public void onError(Exception e) {
                        stopLodingProress();
                        T.showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(UserBean response) {
                        stopLodingProress();
                        Log.i("zhyre",response.toString());
                        T.showToast("注册成功!" + response.getUserName());

                        LoginActivity.launch(RegisterActivity.this, response.getUserName(), response.getPassword());
                        finish();
                    }


                });


            }
        });

    }

    private void initView() {
        mPhoneNumber = findViewById(R.id.et_phone_number);
        mPassword = findViewById(R.id.et_password);
        mRePassword = findViewById(R.id.et_re_password);
        mBtRegister = findViewById(R.id.bt_register);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserBiz.onDestory();
    }
}
