package com.newbigmap.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.newbigmap.R;
import com.newbigmap.config.ARouterConfig;

@Route(path = ARouterConfig.APP_REGISTER)
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // TODO: 2020/5/19
    }
}
