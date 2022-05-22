package com.example.helloworld;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.helloworld.database.Order;

import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.logging.Logger;

public class OrderDesc extends AppCompatActivity {

    TextView huoming, dunwei, dangqiandi, mudidi, huozhuxingming;
    TextView order_status;


    WT_ViewModel mWT_ViewModel;
    private static final Logger logger = Logger.getGlobal();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_desc);
        initView();
//接受上一级传来的数据
        Intent intent = getIntent();
        Order order = (Order) intent.getSerializableExtra("data");
//设置数据
        huoming.setText(order.getType());
        dunwei.setText(String.valueOf(order.getWeight()));
        dangqiandi.setText(order.getDep());
        mudidi.setText(order.getDes());

        huozhuxingming.setText("" + order.getUserId());
        order_status.setText("" + order.getOrderStatus());

        if (order_status.getText().equals("正在进行")) {
            order_status.setTextColor(order_status.getResources().getColor(R.drawable.green11));
        } else if (order_status.getText().equals("已经完成")) {
            order_status.setTextColor(order_status.getResources().getColor(R.drawable.red11));
        } else if (order_status.getText().equals("已经取消")) {
            order_status.setTextColor(order_status.getResources().getColor(R.drawable.black11));
        } else {
            order_status.setTextColor(order_status.getResources().getColor(R.drawable.grey11));
        }


        //设置任务栏
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //获取任务栏，并且添加返回父activity的按钮
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void initView() {
        huoming = findViewById(R.id.order_huoming);
        dunwei = findViewById(R.id.order_dunwei);
        dangqiandi = findViewById(R.id.order_dangqiandi);
        mudidi = findViewById(R.id.order_mudidi);
        order_status = findViewById(R.id.order_status);


        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
    }

}

