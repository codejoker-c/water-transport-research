package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class InitialInterface extends AppCompatActivity {

    TextView textView;
    int time=5;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what==1) {
                time--;
                if (time==0) {
                    //跳转页面
                    Intent intent=new Intent();
                    intent.setClass(InitialInterface.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    textView.setText(time+"");//加上一个空字符串，使int变为String
                    handler.sendEmptyMessageDelayed(1,1000);
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_interface);
        textView=findViewById(R.id.count_down);
        handler.sendEmptyMessageDelayed(1,1000);


    }
}