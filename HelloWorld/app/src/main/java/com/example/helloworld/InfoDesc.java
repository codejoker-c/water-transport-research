package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.database.Order;
import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.user.UserContext;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.logging.Logger;

public class InfoDesc extends AppCompatActivity {

    TextView huoming, dunwei, dangqiandi, mudidi, qiwangriqi, huozhuxingming, huozhudianhua;
    TextView cargo_day2;
    Button grabOrder;
    WT_ViewModel mWT_ViewModel;
    private static final Logger logger = Logger.getGlobal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_desc);
        initView();
//接受上一级传来的数据
        Intent intent = getIntent();
        user_Cargo userCargo = (user_Cargo) intent.getSerializableExtra("data");
//设置数据
        huoming.setText(userCargo.cargo_type);
        dunwei.setText(String.valueOf(userCargo.cargo_weight));
        dangqiandi.setText(userCargo.depart);
        mudidi.setText(userCargo.destin);
        qiwangriqi.setText(String.valueOf(userCargo.month));
        huozhuxingming.setText(userCargo.name);
        huozhudianhua.setText(userCargo.getPhone());
        cargo_day2.setText(String.valueOf(userCargo.day));

        grabOrder.setOnClickListener((View v) -> {
            Order order =
                    new Order(UserContext.getUser().getId(), userCargo.depart, userCargo.destin, 0);
            order.refinePara(String.valueOf(userCargo.cargo_weight), userCargo.cargo_type);

            //logger.info("提示信息");
            //logger.info(String.valueOf(order.getUserId()));
            //logger.info(order.getDep());
            //logger.info(order.getDes());

            mWT_ViewModel.insert(order);
            mWT_ViewModel.deleteuser_CargoWithId(userCargo.getId());
            Toast toast = Toast.makeText(getApplicationContext(), "抢单成功", Toast.LENGTH_SHORT);
            toast.show();
        });


        //设置任务栏
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //获取任务栏，并且添加返回父activity的按钮
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void initView() {
        huoming = findViewById(R.id.cargo_huoming);
        dunwei = findViewById(R.id.cargo_dunwei);
        dangqiandi = findViewById(R.id.cargo_dangqiandi);
        mudidi = findViewById(R.id.cargo_mudidi);
        qiwangriqi = findViewById(R.id.cargo_qiwangriqi);
        huozhuxingming = findViewById(R.id.cargo_huozhuxingming);
        huozhudianhua = findViewById(R.id.cargo_huozhudianhua);
        cargo_day2 = findViewById(R.id.cargo_day2);
        grabOrder = findViewById(R.id.grab_order);
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
    }


}