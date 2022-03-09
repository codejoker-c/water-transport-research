package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.helloworld.database.user_Cargo;

public class InfoDesc extends AppCompatActivity {

    TextView huoming,dunwei,dangqiandi,mudidi,qiwangriqi,huozhuxingming,huozhudianhua;
    TextView cargo_day2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_desc);
        initView();
//接受上一级传来的数据
        Intent intent=getIntent();
        user_Cargo userCargo = (user_Cargo) intent.getSerializableExtra("data");
//设置数据
        huoming.setText(userCargo.cargo_type);
        dunwei.setText(String.valueOf(userCargo.cargo_weight));
        dangqiandi.setText(userCargo.depart);
        mudidi.setText(userCargo.destin);
        qiwangriqi.setText(String.valueOf(userCargo.month));
        huozhuxingming.setText(userCargo.name);
        huozhudianhua.setText(userCargo.phone);
        cargo_day2.setText(String.valueOf(userCargo.day));
    }

    public void initView(){
        huoming=findViewById(R.id.cargo_huoming);
        dunwei=findViewById(R.id.cargo_dunwei);
        dangqiandi=findViewById(R.id.cargo_dangqiandi);
        mudidi=findViewById(R.id.cargo_mudidi);
        qiwangriqi=findViewById(R.id.cargo_qiwangriqi);
        huozhuxingming=findViewById(R.id.cargo_huozhuxingming);
        huozhudianhua=findViewById(R.id.cargo_huozhudianhua);
        cargo_day2=findViewById(R.id.cargo_day2);
    }


}