package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity2 extends AppCompatActivity {

    ViewPager aboutVp;
    TextView shareTv;
    LinearLayout pointLayout;
    List<View>viewList;    //ViewPager的数据源
    int[]picIds={R.mipmap.test,R.mipmap.dragon,R.mipmap.mercury,R.mipmap.mountain};
    private AboutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about2);
        aboutVp=findViewById(R.id.about_vp);
        //shareTv=findViewById(R.id.about)
        pointLayout=findViewById(R.id.about_layout_point);
        viewList=new ArrayList<>();


        //初始化ViewPager页面数据
        for (int i = 0; i < picIds.length; i++) {
            View view= LayoutInflater.from(this).inflate(R.layout.item_aboutvp,null);
            ImageView iv=view.findViewById(R.id.item_aboutvp_iv);
            iv.setImageResource(picIds[i]);
            viewList.add(view);
        }

        //创建适配器对象
        adapter=new AboutAdapter(viewList);
        //设置适配器
        aboutVp.setAdapter(adapter);




    }
}