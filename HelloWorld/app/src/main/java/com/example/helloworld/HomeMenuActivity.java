package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.user.UserContext;

public class HomeMenuActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        btn1=findViewById(R.id.home_btn1);
        btn2=findViewById(R.id.home_btn2);
        btn3=findViewById(R.id.home_btn3);
        btn4=findViewById(R.id.home_btn4);
    }

    //添加一个属性就不需要添加点击事件了
    public void onClick(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.home_btn1:
                intent.setClass(HomeMenuActivity.this,BoatMessageInput.class);
                break;
            case R.id.home_btn2:
                intent.setClass(HomeMenuActivity.this,CargoMessageInput.class);
                break;
            case R.id.home_btn3:
                intent.setClass(HomeMenuActivity.this,AboutActivity2.class);
                break;
            case R.id.home_btn4:
                intent.setClass(HomeMenuActivity.this,InfoList.class);
                break;
            case R.id.ib_menu:
                UserContext.user_center(intent,HomeMenuActivity.this);
                break;
        }
        startActivity(intent);
    }
}
