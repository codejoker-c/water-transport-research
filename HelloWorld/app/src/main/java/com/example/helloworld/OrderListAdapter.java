package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.helloworld.database.Order;
import com.example.helloworld.database.user_Cargo;

import java.util.List;
import java.util.logging.Logger;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder>  implements View.OnClickListener{

    Context context;
    List<Order> OrderList;
    private RecyclerView recyclerView;
    private static final Logger logger = Logger.getGlobal();

    public OrderListAdapter(Context context, List<Order> Orderlist) {
        this.context = context;
        OrderList = Orderlist;
    }

    public void setCargoData(List<Order> Orderlist) {
        OrderList = Orderlist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orderlist, parent, false);
        itemView.setOnClickListener(this);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = OrderList.get(position);

        logger.info("提示信息");
        logger.info(String.valueOf(order.getUserId()));
        logger.info(order.getOrderStatus());
        logger.info(order.getDep());
        logger.info(order.getDes());



        holder.order_name.setText(""+order.getUserId());
        holder.order_phone.setText(order.getOrderStatus());
        if(holder.order_phone.getText().equals("doing")) {
        holder.order_phone.setTextColor(0x1d953f);
        }else if(holder.order_phone.getText().equals("finish")){
            holder.order_phone.setTextColor(0x130c0e);
        }else if(holder.order_phone.getText().equals("revoke")){
            holder.order_phone.setTextColor(0xef4136);
        }else{
            holder.order_phone.setTextColor(0xdecb00);
        }
        holder.order_kind.setText(order.getType());
        holder.order_site.setText(order.getDep());
        holder.order_des.setText(order.getDes());
        //holder.cargo_arrow.setText("→");
        holder.order_weight.setText(String.valueOf(order.getWeight()));

    }

    @Override
    public int getItemCount() {
        if (OrderList != null)
            return OrderList.size();
        else
            return 0;
    }

    //内部类 ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView order_name, order_kind, order_weight, order_site,  order_des, order_phone;
        TextView cargo_month,cargo_day;
        public ViewHolder(View view) {
            super(view);
            order_name = view.findViewById(R.id.order_user_item_name);
            order_kind = view.findViewById(R.id.order_kind);
            order_weight = view.findViewById(R.id.order_weight);
            order_site = view.findViewById(R.id.order_site);

            //cargo_arrow = view.findViewById(R.id.cargo_arrow);
            order_des = view.findViewById(R.id.order_des);
            order_phone = view.findViewById(R.id.const_phone_item_name);

        }


    }

    @Override
    public void onClick(View view) {
//        //根据RecyclerView获得当前View的位置
//        int position = recyclerView.getChildAdapterPosition(view);
//        //程序执行到此，会去执行具体实现的onItemClick()方法
//        if (onItemClickListener!=null){
//            onItemClickListener.onItemClick(recyclerView,view,position,OrderList.get(position));
//        }
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
      //  this.onItemClickListener = onItemClickListener;
    }

    /**
     * 定义RecyclerView选项单击事件的回调接口
     */
    public interface OnItemClickListener{
        //参数（父组件，当前单击的View,单击的View的位置，数据）
        void onItemClick(RecyclerView parent,View view, int position, Order data);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView= recyclerView;
    }
    /**
     *   将RecycleView从Adapter解除
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }
}










