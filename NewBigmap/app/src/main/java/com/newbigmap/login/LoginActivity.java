package com.newbigmap.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.newbigmap.R;
import com.newbigmap.config.ARouterConfig;

import butterknife.BindView;
import butterknife.ButterKnife;


@Route(path = ARouterConfig.APP_LOGIN)
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_discrobe)
    TextView tvDiscrobe;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.user_layout)
    LinearLayout userLayout;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.iv_eyes_pwd)
    ImageView ivEyesPwd;
    @BindView(R.id.pwd_layout)
    LinearLayout pwdLayout;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.tv_welcome)
    TextView tvWelcome;
    @BindView(R.id.tv_register)
    TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ARouter.getInstance().inject(this);
        initView();

    }

    private void initView() {
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int a = 10;

        switch (v.getId()) {
            case R.id.tv_register:
                ARouter.getInstance().build(ARouterConfig.APP_REGISTER).navigation();
                Log.i("LoginActivity", "我被点击了");

                break;


        }

    }
}
