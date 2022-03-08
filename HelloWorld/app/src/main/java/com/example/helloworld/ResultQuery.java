package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.helloworld.database.user_Boat;
import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.user.UserContext;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import kotlin.TuplesKt;

public class ResultQuery extends AppCompatActivity {

    ArrayList ship_data;
    TextView text;
    TextView query_name, query_kind, query_weight, query_site, query_des, query_phone;
    private WT_ViewModel mWT_ViewModel;//实例化WT_ViewModel来与数据库进行交互


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_query);

        text = findViewById(R.id.test);

        query_name = findViewById(R.id.query_cargo_name);
        query_kind = findViewById(R.id.query_cargo_kind);
        query_weight = findViewById(R.id.query_cargo_weight);
        query_site = findViewById(R.id.query_cargo_site);
        query_des = findViewById(R.id.query_cargo_des);
        query_phone = findViewById(R.id.query_cargo_phone);


        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
        //拿到船和货的数据
        mWT_ViewModel.getAlluserCargo().observe(this, new Observer<List<user_Cargo>>() {
            @Override
            public void onChanged(List<user_Cargo> CargoData) {
                try {
                    List<user_Boat> BoatData = mWT_ViewModel.getAlluserBoat().get();
                    /*
                    测试，成功在result界面显示第一位船主与第一位货主的username
                    if(BoatData==null || BoatData.isEmpty()){
                        text.setText("查询失败");
                    }
                    else{
                        text.setText(BoatData.get(0).getUsername()+" "+CargoData.get(0).getUsername());
                   }
                     */

                    //在此后调用python程序即可，BoatData为船主数据，CargoData为货主数据
                    user_Boat userBoat = mWT_ViewModel.finduserBoatWithUsername(UserContext.getUser().getUsername()).get();

                    Integer ship_length, cargo_length;
                    ship_length = BoatData.size();
                    cargo_length = CargoData.size();


                    initPython();
                    Integer cor_corgo=callPythonCode(BoatData, CargoData, text, ship_length, cargo_length,userBoat)+1;

                    //query_name.setText(cor_corgo.toString());
                    user_Cargo userCargo=mWT_ViewModel.finduserCargoWithId(cor_corgo).get();

                    query_name.setText(userCargo.name);
                    query_kind.setText(userCargo.cargo_type);
                    query_weight.setText(String.valueOf(userCargo.cargo_weight));
                    query_site.setText(userCargo.depart);
                    query_des.setText(userCargo.destin);
                    query_phone.setText(userCargo.phone);



                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    // 初始化Python环境
    void initPython() {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }

    // 调用python代码
    Integer callPythonCode(List<user_Boat> BoatData, List<user_Cargo> CargoData, TextView textView, Integer ship_length, Integer cargo_length,user_Boat userboat) {

        Python py = Python.getInstance();
        // 调用hello.py模块中的greet函数，并传一个参数
        // 等价用法：py.getModule("hello").get("greet").call("Android");

        //PyObject用来保存python函数的返回值，一个python类型
        //py.getModule("hello").get("km_code").call(BoatData,CargoData);

        PyObject obj1 = py.getModule("km_code").callAttr("final_func", BoatData, CargoData, ship_length, cargo_length);
        // 将Python返回值换为Java中的Integer类型
        //Integer sum = obj1.toJava(Integer.class);

        // 调用Python函数，将返回的Python中的list转为Java的list
        List<PyObject> pyList = obj1.asList();
        //textView.setText(pyList.toString());

        //pyList.get(0).getClass()=pyObject

        //Integer a=pyList.get(0).get(0).toJava(Integer.class);
        Integer id=userboat.getId()-1;

        List<PyObject> Inner=pyList.get(id).asList();
        String str=Inner.get(1).toString();
        Integer cor_cargo_id=convertToInt(str,0);

        //textView.setText(cor_cargo_id.toString());
        return cor_cargo_id;

    }

    public class MyType {
        public Integer first;
        public Integer second;
    }
    public static int convertToInt(String number, int defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            return defaultValue;
        }
    }

}


