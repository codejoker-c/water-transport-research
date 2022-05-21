package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.helloworld.ui.user.UserFragment;
import com.example.helloworld.user.UserContext;

import java.util.ArrayList;
import java.util.List;

public class HomeMenuActivity extends AppCompatActivity {

    Button datainput_btn, btn3, btn4;
    ViewPager menu;

    List<View> viewList;    //ViewPager的数据源
    //<ImageView>pointList;  //存放显示器小点点的集
    int[] picIds = { R.mipmap.slide_3,R.mipmap.slide_5,R.mipmap.slide_2, R.mipmap.slide_4};
    private AboutAdapter adapter;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                //接收到消息，页面向后一页
                int currentItem = menu.getCurrentItem();
                menu.setCurrentItem(currentItem + 1);
                handler.sendEmptyMessageDelayed(1, 3000);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        datainput_btn = findViewById(R.id.datainput_btn);
        btn3 = findViewById(R.id.home_btn3);
        btn4 = findViewById(R.id.home_btn4);
        menu = findViewById(R.id.menu_vp);
        viewList = new ArrayList<>();


        //初始化ViewPager页面数据
        for (int i = 0; i < picIds.length; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_aboutvp, null);
            ImageView iv = view.findViewById(R.id.item_aboutvp_iv);
            iv.setImageResource(picIds[i]);
            viewList.add(view);
        }

        //创建适配器对象
        adapter = new AboutAdapter(viewList);
        //设置适配器
        menu.setAdapter(adapter);
        // 发送切换页面消息
        handler.sendEmptyMessageDelayed(1, 3000);
    }

    //添加一个属性就不需要添加点击事件了
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.datainput_btn:
                UserContext.data_in(intent,HomeMenuActivity.this);
                startActivity(intent);
                break;
            case R.id.home_btn2:
                UserContext.data_query(intent,HomeMenuActivity.this);
                break;
            case R.id.home_btn3:
                intent.setClass(HomeMenuActivity.this, InfoList.class);
                startActivity(intent);
                break;
            case R.id.home_btn4:
                intent.setClass(HomeMenuActivity.this, AboutActivity2.class);
                startActivity(intent);
                break;
        }

    }
}
