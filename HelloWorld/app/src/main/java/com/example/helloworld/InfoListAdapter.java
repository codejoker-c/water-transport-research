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

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.ViewHolder> {
    Context context;
    List<user_Cargo> CargoData;

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
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        user_Cargo user_cargo = CargoData.get(position);
        holder.cargo_name.setText(user_cargo.name);
        holder.cargo_phone.setText(user_cargo.phone);
        holder.cargo_kind.setText(user_cargo.cargo_type);
        holder.cargo_site.setText(user_cargo.depart);
        holder.cargo_des.setText(user_cargo.destin);
        holder.cargo_arrow.setText("→");
        holder.cargo_weight.setText(String.valueOf(user_cargo.cargo_weight));
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

        public ViewHolder(View view) {
            super(view);
            cargo_name = view.findViewById(R.id.cargo_name);
            cargo_kind = view.findViewById(R.id.cargo_kind);
            cargo_weight = view.findViewById(R.id.cargo_weight);
            cargo_site = view.findViewById(R.id.cargo_site);
            cargo_arrow = view.findViewById(R.id.cargo_arrow);
            cargo_des = view.findViewById(R.id.cargo_des);
            cargo_phone = view.findViewById(R.id.cargo_phone);

        }


    }
}










