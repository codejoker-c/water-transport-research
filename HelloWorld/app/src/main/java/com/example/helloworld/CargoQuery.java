package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

public class CargoQuery extends AppCompatActivity {

    TextView query_name, query_kind,query_weight, query_site, query_phone;
    private WT_ViewModel mWT_ViewModel;//实例化WT_ViewModel来与数据库进行交互
    TextView test01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_query);

        query_name = findViewById(R.id.cargo_query_cargo_name);
        query_kind = findViewById(R.id.cargo_query_cargo_kind);
        query_weight = findViewById(R.id.cargo_query_cargo_weight);
        query_site = findViewById(R.id.cargo_query_cargo_site);
        query_phone = findViewById(R.id.cargo_query_cargo_phone);
        test01=findViewById(R.id.test01);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);



        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
        //拿到船和货的数据
        mWT_ViewModel.getAlluserCargo().observe(this, new Observer<List<user_Cargo>>() {
            @Override
            public void onChanged(List<user_Cargo> CargoData) {
                try {
                    List<user_Boat> BoatData = mWT_ViewModel.getAlluserBoat().get();

                    //在此后调用python程序即可，BoatData为船主数据，CargoData为货主数据
                    user_Cargo userCargo = mWT_ViewModel.finduserCargoWithUsername(UserContext.getUser().getUsername()).get();

                    Integer ship_length, cargo_length;
                    ship_length = BoatData.size();
                    cargo_length = CargoData.size();


                    initPython();
                    Integer cor_boat=callPythonCode(BoatData, CargoData, ship_length, cargo_length,userCargo,test01)+1;

                    user_Boat userBoat=mWT_ViewModel.finduserBoatWithId(cor_boat).get();

                    query_name.setText(userBoat.name);
                    query_kind.setText(String.valueOf(userBoat.weight));
                    query_weight.setText(String.valueOf(userBoat.load_weight));
                    query_site.setText(userBoat.depart);
                    query_phone.setText(userBoat.phone);



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
    Integer callPythonCode(List<user_Boat> BoatData, List<user_Cargo> CargoData, Integer ship_length, Integer cargo_length,user_Cargo userCargo,TextView test01) {

        Python py = Python.getInstance();

        PyObject obj1 = py.getModule("km_code").callAttr("final_func", BoatData, CargoData, ship_length, cargo_length);
        List<PyObject> pyList = obj1.asList();

        test01.setText(pyList.toString());

        Integer id=userCargo.getId()-1;

        List<PyObject> Inner=pyList.get(id).asList();
        String str=Inner.get(0).toString();
        Integer cor_boat_id=convertToInt(str,0);

        return cor_boat_id;

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


