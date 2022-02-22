package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        Test=findViewById(R.id.test);

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


        initPython();
        callPythonCode(Test);



    }

    // 初始化Python环境
    void initPython(){
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }
    // 调用python代码
    void callPythonCode(TextView Test){
        Python py = Python.getInstance();
        // 调用hello.py模块中的greet函数，并传一个参数
        // 等价用法：py.getModule("hello").get("greet").call("Android");
        py.getModule("hello").callAttr("greet", "Android");

        // 调用python内建函数help()，输出了帮助信息
        // py.getBuiltins().get("help").call();

        //PyObject用来保存python函数的返回值，一个python类型
        PyObject obj1 = py.getModule("hello").callAttr("add", 2,3);
        // 将Python返回值换为Java中的Integer类型
        Integer sum = obj1.toJava(Integer.class);
        Test.setText(sum.toString());

    }


}