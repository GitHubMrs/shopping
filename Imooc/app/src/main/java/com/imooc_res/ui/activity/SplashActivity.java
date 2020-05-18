package com.imooc_res.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.imooc_res.R;

public class SplashActivity extends AppCompatActivity {

private Button mButton;
private Handler mHandler = new Handler();
private Runnable mRunnableToLogin = new Runnable() {
    @Override
    public void run() {
        toLoginActivity();
    }
};




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        initEvent();

        mHandler.postDelayed(mRunnableToLogin,3000);
    }

    private void initEvent() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mHandler.removeCallbacks(mRunnableToLogin);
                 toLoginActivity();
            }
        });
    }

    private void initView() {
        mButton = findViewById(R.id.btn_skip);
    }

    public void toLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnableToLogin);
    }
}
