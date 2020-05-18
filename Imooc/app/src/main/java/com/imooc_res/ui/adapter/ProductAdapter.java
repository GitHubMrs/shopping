package com.imooc_res.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imooc_res.R;
import com.imooc_res.bean.Product;
import com.imooc_res.config.Config;
import com.imooc_res.utils.T;
import com.imooc_res.vo.ProductItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.PrductAdapterViewHodler> {

    private Context mContext;
    private List<ProductItem> mProductItem;
    private LayoutInflater mLayoutInflater;


    public ProductAdapter(Context mContext, List<ProductItem> mProductItem) {
        this.mContext = mContext;
        this.mProductItem = mProductItem;
       mLayoutInflater = LayoutInflater.from(mContext);

    }


    @NonNull
    @Override
    public PrductAdapterViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.item_product, parent, false);

        return new PrductAdapterViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrductAdapterViewHodler holder, int position) {

        ProductItem productItem = mProductItem.get(position);
        Picasso.with(mContext)
                .load(Config.BASE_URL+productItem.getIcon())
                .placeholder(R.drawable.pictures_no)
                .into(holder.iv_picture);

        holder.tv_name.setText(productItem.getName());
        holder.tv_label.setText(productItem.getLabel());
        holder.tv_count.setText(productItem.count+"");
        holder.tv_price.setText(productItem.getPrice()+"元/份");
    }

    @Override
    public int getItemCount() {
        return mProductItem.size();
    }

    class PrductAdapterViewHodler extends RecyclerView.ViewHolder {

        private TextView tv_name, tv_label, tv_price, tv_count;
        private ImageView iv_picture,iv_add, iv_sub;

        public PrductAdapterViewHodler(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_label = itemView.findViewById(R.id.tv_label);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_count = itemView.findViewById(R.id.tv_count);
            iv_add = itemView.findViewById(R.id.iv_add);
            iv_sub = itemView.findViewById(R.id.iv_sub);
            iv_picture = itemView.findViewById(R.id.iv_picture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getLayoutPosition();
                    ProductItem productItem = mProductItem.get(position);
                    productItem.count += 1;
                    tv_count.setText(productItem.count + "");

                    //回调到Activity
                    if (mOnProductListent != null) {
                        mOnProductListent.onProductAdd(productItem);
                    }


                }
            });

            iv_sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    ProductItem productItem = mProductItem.get(position);

                    if (productItem.count >= 0) {
                        T.showToast("不能再减了");
                        return;
                    }
                    productItem.count -= 1;
                    tv_count.setText(productItem.count + "");

                    //回调到Activity
                    if (mOnProductListent != null) {
                        mOnProductListent.onProductAdd(productItem);
                    }
                }
            });

        }
    }


    public interface OnProductListent {
        void onProductAdd(ProductItem productItem);

        void onProductSub(ProductItem productItem);
    }

    private OnProductListent mOnProductListent;

    public void setOnProductListent(OnProductListent onProductListent) {
        mOnProductListent = onProductListent;
    }
}
