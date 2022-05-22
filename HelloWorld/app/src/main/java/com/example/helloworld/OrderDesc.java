package com.example.helloworld;

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

    TextView huoming, dunwei, dangqiandi, mudidi,  huozhuxingming, huozhudianhua;


    WT_ViewModel mWT_ViewModel;
    private static final Logger logger = Logger.getGlobal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_desc);
//        initView();
////接受上一级传来的数据
//        Intent intent = getIntent();
//        Order order = (Order) intent.getSerializableExtra("data");
////设置数据
//        huoming.setText(order.getType());
//        dunwei.setText(String.valueOf(order.getWeight()));
//        dangqiandi.setText(order.getDep());
//        mudidi.setText(order.getDes());
//
//        huozhuxingming.setText(""+order.getUserId());
//        huozhudianhua.setText(""+order.getOrderStatus());
//
//
//
//
//
//        //设置任务栏
//        Toolbar myToolbar = findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);
//        //获取任务栏，并且添加返回父activity的按钮
//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void initView() {
        huoming = findViewById(R.id.order_huoming);
        dunwei = findViewById(R.id.order_dunwei);
        dangqiandi = findViewById(R.id.order_dangqiandi);
        mudidi = findViewById(R.id.order_mudidi);

        huozhuxingming = findViewById(R.id.order_huozhuxingming);
        huozhudianhua = findViewById(R.id.order_huozhudianhua);

        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
    }

}

