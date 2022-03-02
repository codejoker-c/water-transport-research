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
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import io.vavr.Tuple2;
import kotlin.TuplesKt;

public class ResultQuery extends AppCompatActivity {

    ArrayList ship_data;
    TextView text;
    private WT_ViewModel mWT_ViewModel;//实例化WT_ViewModel来与数据库进行交互

    //List<user_Boat> BoatData;
    //List<user_Cargo> CargoData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_query);

        text = findViewById(R.id.test);

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

                    Integer ship_length, cargo_length;
                    ship_length = BoatData.size();
                    cargo_length = CargoData.size();


                    initPython();
                    callPythonCode(BoatData, CargoData, text, ship_length, cargo_length);


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //CargoData.get(5).depart;

        //List<user_Boat> BoatData;

        /*
        mWT_ViewModel.getAlluserBoat().observe(this, new Observer<List<user_Boat>>() {
            @Override
            public void onChanged(List<user_Boat> user_boats) {
                BoatData=user_boats;
            }
        });

        */


        //Integer BoatDataLength=CargoData.size();
        //Integer CargoDatalength=CargoData.size();


        //initPython();
        //callPythonCode(BoatData,CargoData,text);


    }

    // 初始化Python环境
    void initPython() {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }

    // 调用python代码
    void callPythonCode(List<user_Boat> BoatData, List<user_Cargo> CargoData, TextView textView, Integer ship_length, Integer cargo_length) {

        Python py = Python.getInstance();
        // 调用hello.py模块中的greet函数，并传一个参数
        // 等价用法：py.getModule("hello").get("greet").call("Android");

        //PyObject用来保存python函数的返回值，一个python类型
        //py.getModule("hello").get("km_code").call(BoatData,CargoData);

/*        List<PyObject> params1 = new ArrayList<PyObject>();
        List<PyObject> params2 = new ArrayList<PyObject>();
        params1.add(PyObject.fromJava(BoatData));
        params2.add(PyObject.fromJava(CargoData));*/

        //Integer BoatDataLength=BoatData.size();
        //Integer BoatDataLength=CargoData.size();
        //Integer CargoDatalength=CargoData.size();
        PyObject obj1 = py.getModule("km_code").callAttr("final_func", BoatData, CargoData, ship_length, cargo_length);
        // 将Python返回值换为Java中的Integer类型
        //Integer sum = obj1.toJava(Integer.class);

/*        Tuple2<Integer,Integer> sum2 = obj1.toJava(Tuple2.class);
        textView.setText(sum2.toString());*/

        // 调用Python函数，将返回的Python中的list转为Java的list
        List<PyObject> pyList = obj1.asList();
        //Log.d(TAG,"get_list = "+pyList.toString());


        //List<Tuple2<Integer,Integer>> sum2 = obj1.toJava(List.class);
        //textView.setText(sum2.get(0).toString());
        textView.setText(pyList.toString());

    }

    public class MyType {
        public Integer first;
        public Integer second;
    }

}


