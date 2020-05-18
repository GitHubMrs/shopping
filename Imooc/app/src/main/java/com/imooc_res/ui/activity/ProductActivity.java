package com.imooc_res.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imooc_res.R;
import com.imooc_res.bean.Order;
import com.imooc_res.bean.Product;
import com.imooc_res.biz.OrderBiz;
import com.imooc_res.biz.ProductBiz;
import com.imooc_res.net.CommonCallback;
import com.imooc_res.ui.adapter.ProductAdapter;
import com.imooc_res.ui.view.refresh.SwipeRefresh;
import com.imooc_res.ui.view.refresh.SwipeRefreshLayout;
import com.imooc_res.utils.T;
import com.imooc_res.vo.ProductItem;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swf_layout)
    SwipeRefreshLayout idRefreshLayout;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.bt_pay)
    Button btPay;

    private ProductBiz mProductBiz = new ProductBiz();
    private List<ProductItem> mList = new ArrayList<>();
    private int mCurrentPage = 0;
    private ProductAdapter mProductAdapter;
    private float mTotalPrice;
    private int mTotalCount;
    private OrderBiz mOrderBiz = new OrderBiz();
    private Order mOrder = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        setUpToolbar();
        setTitle("订单");
        loadData();
        //设置开关
        idRefreshLayout.setMode(SwipeRefresh.Mode.BOTH);
        //设置颜色
        idRefreshLayout.setColorSchemeColors(Color.RED, Color.BLACK, Color.BLUE, Color.GRAY);
        mProductAdapter = new ProductAdapter(this, mList);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(mProductAdapter);

        initEvent();

    }

    private void initEvent() {

        idRefreshLayout.setOnPullUpRefreshListener(new SwipeRefreshLayout.OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                loadMore();
            }
        });

        idRefreshLayout.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTotalCount <= 0) {
                    T.showToast("您还没有选择菜品呢");
                    return;
                }
                mOrder.setCount(mTotalCount);
                mOrder.setPrice(mTotalPrice);
                mOrder.setRestaurant(mList.get(0).getRestaurant());

                //去支付
                startLodingProgress();
                mOrderBiz.add(mOrder, new CommonCallback() {
                    @Override
                    public void onError(Exception e) {
                        stopLodingProress();
                        T.showToast(e.getMessage());

                    }

                    @Override
                    public void onSuccess(Object response) {
                        stopLodingProress();
                        T.showToast("订单支付成功");
                        setResult(RESULT_OK);
                        finish();
                    }
                });


            }
        });
        mProductAdapter.setOnProductListent(new ProductAdapter.OnProductListent() {
            @Override
            public void onProductAdd(ProductItem productItem) {
                mTotalCount++;
                tvCount.setText("数量: " + mTotalCount);
                mTotalPrice += productItem.getPrice();
                btPay.setText(mTotalPrice + "元 立即支付");
                mOrder.addProduct(productItem);

            }

            @Override
            public void onProductSub(ProductItem productItem) {
                mTotalCount--;
                if (mTotalCount <= 0) {
                    mTotalPrice=0;
                    T.showToast("不能再减了!");
                    return;
                }




                tvCount.setText("数量: " + mTotalCount);
                mTotalPrice -= productItem.getPrice();
                btPay.setText(mTotalPrice + "元 立即支付");
                mOrder.removeProduct(productItem);
            }
        });


    }

    private void loadData() {
        startLodingProgress();

        mProductBiz.listByPage(0, new CommonCallback<List<Product>>() {
            @Override
            public void onError(Exception e) {
                stopLodingProress();
                T.showToast(e.getMessage());
                if (idRefreshLayout.isRefreshing()) {
                    idRefreshLayout.setRefreshing(false);
                }

            }

            @Override
            public void onSuccess(List<Product> response) {
                stopLodingProress();
                if (idRefreshLayout.isRefreshing()) {
                    idRefreshLayout.setRefreshing(false);
                }
                mList.clear();
                for (Product product : response) {
                    mList.add(new ProductItem(product));
                }
                mProductAdapter.notifyDataSetChanged();
                //清空选择的数据,价格
                mTotalPrice = 0;
                mTotalCount = 0;
                tvCount.setText("数量: " + mTotalCount);
                btPay.setText(mTotalPrice + "元 立即支付");

            }
        });

    }

    private void loadMore() {
        startLodingProgress();

        mProductBiz.listByPage(++mCurrentPage, new CommonCallback<List<Product>>() {
            @Override
            public void onError(Exception e) {
                stopLodingProress();
                T.showToast(e.getMessage());
                mCurrentPage--;
                idRefreshLayout.setPullUpRefreshing(false);
            }

            @Override
            public void onSuccess(List<Product> response) {
                stopLodingProress();
                idRefreshLayout.setPullUpRefreshing(false);

                mCurrentPage = 0;
                if (response.size() == 0) {
                    T.showToast("已经到最低了~");
                    return;
                }
                T.showToast("又找到" + response.size() + "道菜~");

                for (Product product : response) {
                    mList.add(new ProductItem(product));
                }

                mProductAdapter.notifyDataSetChanged();

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProductBiz.onDestroy();
    }
}
