package com.newbigmap.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.newbigmap.R;
import com.newbigmap.config.ARouterConfig;
import com.newbigmap.net.API;
import com.newbigmap.net.HttpConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



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
        btLogin.setOnClickListener(this);
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
            case R.id.bt_login:

             /*   Retrofit build = new Retrofit.Builder()
                        //指定baseurl，这里有坑，最后后缀出带着/
                        .baseUrl("http://www.baidu.com/")
                        //设置内容格式,这种对应的数据返回值是String类型
                        .addConverterFactory(ScalarsConverterFactory.create())
                        //定义client类型
                        .client(new OkHttpClient())
                        .build();
                //通过retrofit和定义的有网络访问方法的接口关联
                API api = build.create(API.class);
                Call<ResponseBody> login = api.login("17537751005", "hyj88888", "APP");
                login.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        Log.i("LoginActivity", "onResponse: "+ response.body().toString());
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();

                }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_LONG).show();

                    }
                });
*/


                break;

        }

    }
}
