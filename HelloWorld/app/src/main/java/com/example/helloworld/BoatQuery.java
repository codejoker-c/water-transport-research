package com.example.helloworld;



import static io.grpc.okhttp.internal.Platform.logger;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.helloworld.database.Order;
import com.example.helloworld.database.User;
import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.user.UserContext;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BoatQuery extends AppCompatActivity {

    ArrayList ship_data;
    TextView query_name, query_kind, query_weight, query_site, query_des, query_phone;
    TextView mon, day;
    private WT_ViewModel mWT_ViewModel;//实例化WT_ViewModel来与数据库进行交互
    TextView test01;
    Button grab_order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_query);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        grab_order = findViewById(R.id.grab_order2);
        query_name = findViewById(R.id.name01);
        query_kind = findViewById(R.id.cargo_kind01);
        query_weight = findViewById(R.id.cargo_weight01);
        query_site = findViewById(R.id.cargo_site01);
        query_des = findViewById(R.id.cargo_des01);
        query_phone = findViewById(R.id.phone01);
        //test01 = findViewById(R.id.test01);
        mon = findViewById(R.id.cargo_month01);
        day = findViewById(R.id.cargo_day01);




        //拿到船和货的数据
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
        mWT_ViewModel.getAlluserCargo().observe(this, new Observer<List<user_Cargo>>() {
            @Override
            public void onChanged(List<user_Cargo> CargoData) {
                try {
                    List<User> BoatData = mWT_ViewModel.getAllUser().get();

                    //在此后调用python程序即可，BoatData为船主数据，CargoData为货主数据
                    //用电话去找---------------------------------------------
                    User userBoat = mWT_ViewModel.findUserWithPhone(UserContext.getUser().getPhone()).get();

                    Integer ship_length, cargo_length;
                    ship_length = BoatData.size();
                    cargo_length = CargoData.size();


                    logger.info("这是提示信息");
                    logger.info(String.valueOf(ship_length));
                    logger.info(String.valueOf(BoatData.get(0).getDepart()));
                    logger.info(String.valueOf(BoatData.get(0).getName()));
                    logger.info(String.valueOf(BoatData.get(0).getPhone()));
                    logger.info(String.valueOf(cargo_length));

                    initPython();
                    Integer cor_corgo=callPythonCode(BoatData, CargoData, ship_length, cargo_length,userBoat,test01)+1;

                    //query_name.setText(cor_corgo.toString());

                    user_Cargo userCargo=mWT_ViewModel.finduserCargoWithId(2).get();

                    //query_name.setText(mWT_ViewModel.findUserWithPhone(userBoat.getPhone()).get().getPhone());

                    query_name.setText(userCargo.name);
                    query_kind.setText(userCargo.cargo_type);
                    query_weight.setText(String.valueOf(userCargo.cargo_weight));
                    query_site.setText(userCargo.depart);
                    query_des.setText(userCargo.destin);
                    query_phone.setText(userCargo.getPhone());
                    mon.setText(String.valueOf(userCargo.month));
                    day.setText(String.valueOf(userCargo.day));

                    grab_order.setOnClickListener((View v) -> {
                        Order order =
                                new Order(UserContext.getUser().getId(), userCargo.depart, userCargo.destin, 0);
                        order.refinePara(String.valueOf(userCargo.cargo_weight), userCargo.cargo_type);

                        //logger.info("提示信息");
                        //logger.info(String.valueOf(order.getUserId()));
                        //logger.info(order.getDep());
                        //logger.info(order.getDes());

                        mWT_ViewModel.insert(order);
                        mWT_ViewModel.deleteuser_CargoWithId(userCargo.getId());
                        Toast toast = Toast.makeText(getApplicationContext(), "抢单成功", Toast.LENGTH_SHORT);
                        toast.show();
                    });

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

//        ImageButton ib_menu = findViewById(R.id.ib_menu);
//        ib_menu.setOnClickListener((View view)->{
//            Intent intent = new Intent();
//            UserContext.user_center(intent, BoatQuery.this);
//            startActivity(intent);
//        });


    }

    // 初始化Python环境
    void initPython() {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }

    // 调用python代码

    Integer callPythonCode(List<User> BoatData, List<user_Cargo> CargoData, Integer ship_length, Integer cargo_length, User userboat, TextView test01) {

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
        //test01.setText(pyList.toString());
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










