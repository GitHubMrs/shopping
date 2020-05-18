package com.imooc_res.ui.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imooc_res.R;
import com.imooc_res.bean.Order;
import com.imooc_res.config.Config;
import com.imooc_res.ui.activity.BaseActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context mContext;
    private ArrayList<Order> mDatas;
    private LayoutInflater mInflater;
    public OrderAdapter(Context context,ArrayList<Order> list){
        mContext = context;
        mDatas = list;
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_order, parent,false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = mDatas.get(position);
        Picasso.with(mContext)
                .load(Config.BASE_URL+order.getRestaurant().getIcon())
                .placeholder(R.drawable.pictures_no)
                .into(holder.iv_pritrue);

        if (order.getPs().size()>0)

            holder.tv_lable.setText(order.getPs().get(0).product.getName()+"等"+order.getCount()+"件商品");

        else {

            holder.tv_lable.setText("无消费");

        }


        holder.tv_name.setText(order.getRestaurant().getName());
        holder.tv_price.setText("共消费: "+order.getPrice()+"元");

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_pritrue;
        TextView tv_name,tv_lable,tv_price;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            iv_pritrue = itemView.findViewById(R.id.iv_picture);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_lable = itemView.findViewById(R.id.tv_label);
            tv_price = itemView.findViewById(R.id.tv_price);

        }
    }
}
