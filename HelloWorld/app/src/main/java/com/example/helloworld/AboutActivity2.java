package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.Kwarg;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity2 extends AppCompatActivity {

    ViewPager aboutVp;
    TextView shareTv,Test;
    LinearLayout pointLayout;
    Button button;
    List<View>viewList;    //ViewPager的数据源
    //<ImageView>pointList;  //存放显示器小点点的集合
    int[]picIds={R.mipmap.test,R.mipmap.dragon,R.mipmap.mercury,R.mipmap.joker};
    private AboutAdapter adapter;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==1)
            {
                //接收到消息，页面向后一页
                int currentItem=aboutVp.getCurrentItem();
                aboutVp.setCurrentItem(currentItem+1);
                handler.sendEmptyMessageDelayed(1,3000);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about2);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        aboutVp=findViewById(R.id.about_vp);
        button=findViewById(R.id.advice_submit);
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
        // 发送切换页面消息
        handler.sendEmptyMessageDelayed(1,3000);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutActivity2.this, "提交成功！感谢您的反馈", Toast.LENGTH_SHORT).show();
            }
        });


    }


}