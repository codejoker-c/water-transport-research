package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.helloworld.database.user_Boat;
import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ResultQuery extends AppCompatActivity {

    ArrayList ship_data;
    TextView text;
    private WT_ViewModel mWT_ViewModel;//实例化WT_ViewModel来与数据库进行交互

    List<user_Boat> BoatData;
    List<user_Cargo> CargoData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_query);

        text=findViewById(R.id.test);

        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
        //拿到船和货的数据
        mWT_ViewModel.getAlluserCargo().observe(this, new Observer<List<user_Cargo>>() {
            @Override
            public void onChanged(List<user_Cargo> user_cargos) {
                CargoData=user_cargos;
            }
        });

        mWT_ViewModel.getAlluserBoat().observe(this, new Observer<List<user_Boat>>() {
            @Override
            public void onChanged(List<user_Boat> user_boats) {
                BoatData=user_boats;
            }
        });

        initPython();
        callPythonCode(BoatData,CargoData,text);


    }

    // 初始化Python环境
    void initPython(){
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }
    // 调用python代码
    void callPythonCode(List<user_Boat> BoatData,List<user_Cargo> CargoData,TextView textView){

        Python py = Python.getInstance();
        // 调用hello.py模块中的greet函数，并传一个参数
        // 等价用法：py.getModule("hello").get("greet").call("Android");

        //PyObject用来保存python函数的返回值，一个python类型
        //py.getModule("hello").get("km_code").call(BoatData,CargoData);
        PyObject obj1 = py.getModule("km_code").callAttr("final_func", BoatData,CargoData);
        // 将Python返回值换为Java中的Integer类型
        //Integer sum = obj1.toJava(Integer.class);
        //List<List<Integer>> sum2 = obj1.toJava(List<List<Integer>>.class);
        //textView.setText(sum2[0][0].toString());

    }



}

