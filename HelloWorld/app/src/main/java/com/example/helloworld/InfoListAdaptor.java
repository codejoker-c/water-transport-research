package com.example.helloworld;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.helloworld.database.user_Cargo;
import java.util.List;

//ViewList适配器  基本套路化的写法

public class InfoListAdaptor extends BaseAdapter {

    Context context;
    List<user_Cargo>CargoData;

    public InfoListAdaptor(Context context, List<user_Cargo> cargoData) {
        this.context = context;
        CargoData = cargoData;
    }


    //决定了ListView展示的行数
    @Override
    public int getCount() {
        return CargoData.size();
    }

    //返回指定位置的数据
    @Override
    public Object getItem(int position) {
        return CargoData.get(position);
    }

    //返回这个位置对应的Id
    @Override
    public long getItemId(int position) {
        return position;
    }

    //返回指定位置的View
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder=null;
        //判断是否有滑动出去的View，如果有则下面用滑出去的写
        if (convertView ==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_infolist,null);  //将布局转换为View对象
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        //加载控件显示的内容
        //获取集合指定位置的数据
        user_Cargo user_cargo = CargoData.get(position);
        holder.cargo_name.setText(user_cargo.name);
        holder.cargo_phone.setText(user_cargo.phone);
        holder.cargo_kind.setText(user_cargo.cargo_type);
        holder.cargo_site.setText(user_cargo.depart);
        holder.cargo_des.setText(user_cargo.destin);
        holder.cargo_arrow.setText("→");


        return null;
    }

    //内部类 ViewHolder
    class ViewHolder{
        TextView cargo_name,cargo_kind,cargo_weight,cargo_site,cargo_arrow,cargo_des,cargo_phone;

        public ViewHolder(View view){
            cargo_name=view.findViewById(R.id.cargo_name);
            cargo_kind=view.findViewById(R.id.cargo_kind);
            cargo_weight=view.findViewById(R.id.cargo_weight);
            cargo_site=view.findViewById(R.id.cargo_site);
            cargo_arrow=view.findViewById(R.id.cargo_arrow);
            cargo_des=view.findViewById(R.id.cargo_des);
            cargo_phone=view.findViewById(R.id.cargo_phone);

        }


    }


}
