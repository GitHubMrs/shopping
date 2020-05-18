package com.imooc_res.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.imooc_res.R;
import com.imooc_res.UserInfoHolder;
import com.imooc_res.bean.UserBean;
import com.imooc_res.biz.UserBiz;
import com.imooc_res.net.CommonCallback;
import com.imooc_res.utils.T;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

import okhttp3.CookieJar;

public class LoginActivity extends BaseActivity {

  private   UserBiz userBiz = new UserBiz();

    private EditText mUserName , mPassword;
    private Button mButtonLogin;
    private TextView mTvRegister;
    private static final String KEY_USERNAME = "key_username";
    private static final String KEY_PASSWORD = "key_password";

    public static void launch(Context mContext, String userName, String password) {
        Intent intent = new Intent(mContext,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("key_username",userName);
        intent.putExtra("key_password",password);
        mContext.startActivity(intent);


    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initIntent(intent);
    }

    private void initIntent(Intent intent) {
        if (intent==null)
            return;



        String key_username = intent.getStringExtra("key_username");
        String key_password = intent.getStringExtra("key_password");

        if (TextUtils.isEmpty(key_username)||TextUtils.isEmpty(key_password))
            return;

        mUserName.setText(key_username);
        mPassword.setText(key_password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
        initIntent(getIntent());
    }

    private void initEvent() {
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = mUserName.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                    T.showToast("账号和密码不能为空!");
                    return;
                }

                startLodingProgress();



                userBiz.login(username, password, new CommonCallback<UserBean>() {
                    @Override
                    public void onError(Exception e) {
                        stopLodingProress();
                        T.showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(UserBean response) {
                        stopLodingProress();
                        T.showToast("登录成功!");
                      //保存用户信息
                        UserInfoHolder.getInstance().setUser(response);

                        toOrderActivity();

                    }


                });




                

            }
        });

        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterActivity();
            }
        });
    }



    private void toRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);


    }

    private void toOrderActivity() {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
        finish();

    }

    private void initView() {
    mUserName = findViewById(R.id.et_username);
    mPassword = findViewById(R.id.et_password);
    mButtonLogin = findViewById(R.id.bt_login);
    mTvRegister = findViewById(R.id.tv_register);

    }


    @Override
    protected void onResume() {
        super.onResume();
        CookieJarImpl cookieJar = (CookieJarImpl) OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        cookieJar.getCookieStore().removeAll();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        userBiz.onDestory();
    }
}
