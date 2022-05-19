package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.helloworld.database.user_Cargo;

import java.util.List;

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.ViewHolder>  implements View.OnClickListener{

    Context context;
    List<user_Cargo> CargoData;
    private RecyclerView recyclerView;

    public InfoListAdapter(Context context, List<user_Cargo> cargoData) {
        this.context = context;
        CargoData = cargoData;
    }

    public void setCargoData(List<user_Cargo> cargoData) {
        CargoData = cargoData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_infolist, parent, false);
        itemView.setOnClickListener(this);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        user_Cargo user_cargo = CargoData.get(position);
        holder.cargo_name.setText(user_cargo.name);
        holder.cargo_phone.setText(user_cargo.getPhone());
        holder.cargo_kind.setText(user_cargo.cargo_type);
        holder.cargo_site.setText(user_cargo.depart);
        holder.cargo_des.setText(user_cargo.destin);
        //holder.cargo_arrow.setText("→");
        holder.cargo_weight.setText(String.valueOf(user_cargo.cargo_weight));
        holder.cargo_month.setText(String.valueOf(user_cargo.month));
        holder.cargo_day.setText(String.valueOf(user_cargo.day));
    }

    @Override
    public int getItemCount() {
        if (CargoData != null)
            return CargoData.size();
        else
            return 0;
    }

    //内部类 ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView cargo_name, cargo_kind, cargo_weight, cargo_site, cargo_arrow, cargo_des, cargo_phone;
        TextView cargo_month,cargo_day;
        public ViewHolder(View view) {
            super(view);
            cargo_name = view.findViewById(R.id.const_user_item_name);
            cargo_kind = view.findViewById(R.id.cargo_kind);
            cargo_weight = view.findViewById(R.id.cargo_weight);
            cargo_site = view.findViewById(R.id.cargo_site);
            cargo_month = view.findViewById(R.id.cargo_month);
            cargo_day = view.findViewById(R.id.cargo_day);
            //cargo_arrow = view.findViewById(R.id.cargo_arrow);
            cargo_des = view.findViewById(R.id.cargo_des);
            cargo_phone = view.findViewById(R.id.const_phone_item_name);

        }


    }

    @Override
    public void onClick(View view) {
        //根据RecyclerView获得当前View的位置
        int position = recyclerView.getChildAdapterPosition(view);
        //程序执行到此，会去执行具体实现的onItemClick()方法
        if (onItemClickListener!=null){
            onItemClickListener.onItemClick(recyclerView,view,position,CargoData.get(position));
        }
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 定义RecyclerView选项单击事件的回调接口
     */
    public interface OnItemClickListener{
        //参数（父组件，当前单击的View,单击的View的位置，数据）
        void onItemClick(RecyclerView parent,View view, int position, user_Cargo data);
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










