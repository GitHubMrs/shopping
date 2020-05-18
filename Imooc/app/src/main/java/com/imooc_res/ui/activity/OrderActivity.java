package com.imooc_res.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imooc_res.R;
import com.imooc_res.UserInfoHolder;
import com.imooc_res.bean.Order;
import com.imooc_res.bean.UserBean;
import com.imooc_res.biz.OrderBiz;
import com.imooc_res.net.CommonCallback;
import com.imooc_res.ui.adapter.OrderAdapter;
import com.imooc_res.ui.view.CircleTransform;
import com.imooc_res.ui.view.refresh.SwipeRefresh;
import com.imooc_res.ui.view.refresh.SwipeRefreshLayout;
import com.imooc_res.utils.T;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.id_iv_icon)
    ImageView idIvIcon;
    @BindView(R.id.id_tv_name)
    TextView idTvName;
    @BindView(R.id.id_btn_take_order)
    Button idBtnTakeOrder;
    @BindView(R.id.id_recyclerview)
    RecyclerView idRecyclerview;
    @BindView(R.id.id_refresh_layout)
    SwipeRefreshLayout idRefreshLayout;
    @BindView(R.id.activity_order)
    LinearLayout activityOrder;
    private OrderAdapter mAdapter;
    private ArrayList<Order> mDatas = new ArrayList<>();
    private OrderBiz mOrderBiz = new OrderBiz();
    private int mCurrentPage=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        initViews();
        initEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();

        startLodingProgress();
        loadData();

    }




    private void initEvents() {
        idBtnTakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProductListActivity();
            }


        });


        idRefreshLayout.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        idRefreshLayout.setOnPullUpRefreshListener(new SwipeRefreshLayout.OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                loadMore();
            }
        });


    }

    private void toProductListActivity() {
        Intent intent = new Intent(this, ProductActivity.class);
        startActivityForResult(intent,1001);
    }


    private void initViews() {

        UserBean userBean = UserInfoHolder.getInstance().getUser();
        if (userBean != null) {
            idTvName.setText(userBean.getUserName());
        } else {
            toLoginActivity();
            return;
        }

        //设置开关
        idRefreshLayout.setMode(SwipeRefresh.Mode.BOTH);
        //设置颜色
        idRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK, Color.BLUE, Color.GRAY);

        mAdapter = new OrderAdapter(this, mDatas);
        idRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        idRecyclerview.setAdapter(mAdapter);

        Picasso.with(this).load(R.drawable.icon).placeholder(R.drawable.pictures_no).transform(new CircleTransform()).into(idIvIcon);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void loadMore() {
        startLodingProgress();
        mOrderBiz.listByPage(++mCurrentPage, new CommonCallback<List<Order>>() {
            @Override
            public void onError(Exception e) {
                stopLodingProress();
                T.showToast(e.getMessage());
                idRefreshLayout.setPullUpRefreshing(false);
                mCurrentPage--;

                String message = e.getMessage();
                if (message.contains("用户未登录")) {
                    toLoginActivity();
                }

            }

            @Override
            public void onSuccess(List<Order> response) {
                stopLodingProress();
                if (response.size() == 0) {
                    T.showToast("没有历史订单啦~~~");
                    idRefreshLayout.setPullUpRefreshing(false);
                    return;
                }
                T.showToast("订单加载成功~~~");
                mDatas.addAll(response);
                mAdapter.notifyDataSetChanged();
                idRefreshLayout.setPullUpRefreshing(false);
            }
        });
    }

    private void loadData() {
        startLodingProgress();
        mOrderBiz.listByPage(0, new CommonCallback<List<Order>>() {
            @Override
            public void onError(Exception e) {
                stopLodingProress();
                T.showToast(e.getMessage());
                if (idRefreshLayout.isRefreshing()) {
                    idRefreshLayout.setRefreshing(false);
                }
                if (e.getMessage().contains("用户未登录")) {

                }

            }

            @Override
            public void onSuccess(List<Order> response) {
                stopLodingProress();
                mCurrentPage = 0;
                T.showToast("更新订单成功~~~");
                mDatas.clear();
                mDatas.addAll(response);
                mAdapter.notifyDataSetChanged();
                if (idRefreshLayout.isRefreshing()) {
                    idRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1001&&requestCode==RESULT_OK){


        }
    }
}
