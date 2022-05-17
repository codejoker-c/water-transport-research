package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class InitialInterface extends AppCompatActivity {

    TextView textView,skip;
    int time=2;
    int control=0;
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (control==1) {
                ;
            }
            else
            {
                if (msg.what==1) {
                    time--;
                    if (time==0) {
                        //跳转页面
                        Intent intent=new Intent();
                        intent.setClass(InitialInterface.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        //textView.setText(time+"");//加上一个空字符串，使int变为String
                        handler.sendEmptyMessageDelayed(1,1000);
                    }

                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_interface);
        //textView=findViewById(R.id.count_down);
        skip=findViewById(R.id.skip);
        handler.sendEmptyMessageDelayed(1,1000);


    }

    public void onClick(View view) {
        Intent intent = new Intent();
        if(view.getId()==R.id.skip){
            intent.setClass(InitialInterface.this,MainActivity.class);
            control=1;
        }

        startActivity(intent);


    }
}