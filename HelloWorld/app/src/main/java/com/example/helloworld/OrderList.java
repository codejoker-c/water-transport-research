package com.example.helloworld;

import android.graphics.Paint;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.helloworld.database.Order;
import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.user.UserContext;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.List;

public class OrderList extends AppCompatActivity {


    RecyclerView order_list;
    private WT_ViewModel mWT_ViewModel;
    //ListView内部数据源
    List<Order> orderDate;
    private OrderListAdapter orderListAdapter;
    DividerItemDecoration mDivider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

//        TextView text_underline=findViewById(R.id.huoyuan_under_line);
//        text_underline.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
//        text_underline.getPaint().setAntiAlias(true);//抗锯齿


        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
        order_list=findViewById(R.id.order_info);
        //CargoData=new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        order_list.setLayoutManager(linearLayoutManager);
        //初始化分隔线、添加分隔线
        mDivider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        order_list.addItemDecoration(mDivider);

        //创建适配器
        orderListAdapter = new OrderListAdapter(this,orderDate);
        //设置适配器
        order_list.setAdapter(orderListAdapter);

        //添加观测，每当表单中信息发生改变时，就重新设置UI显示信息
        mWT_ViewModel.findOrdersWithuserId(UserContext.getUser().getId()).observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> Orders) {
                orderListAdapter.setCargoData(Orders);
                orderListAdapter.notifyDataSetChanged();
            }
        });

        orderListAdapter.setOnItemClickListener(new OrderListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, Order data) {
                Intent intent=new Intent();
                intent.setClass(OrderList.this,OrderDesc.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });


    }

}
