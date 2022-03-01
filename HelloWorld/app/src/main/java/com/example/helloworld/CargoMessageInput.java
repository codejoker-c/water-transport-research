package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.database.User;
import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.user.UserContext;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CargoMessageInput extends AppCompatActivity {

    TextView spin_info2, error;
    EditText name_cargo, phone_cargo, weight_cargo, depart_cargo, dest_cargo;
    Spinner type_cargo, desmonth_cargo, desday_cargo;
    String str;
    WT_ViewModel mWT_ViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_message_input);
        //设置任务栏
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //获取任务栏，并且添加返回父activity的按钮
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        name_cargo = findViewById(R.id.name_cargo);

        spin_info2 = findViewById(R.id.spin_info_cargo);
        name_cargo = findViewById(R.id.name_cargo);
        phone_cargo = findViewById(R.id.phone_cargo);
        weight_cargo = findViewById(R.id.weight_cargo);
        depart_cargo = findViewById(R.id.depart_cargo);
        dest_cargo = findViewById(R.id.destination_cargo);
        type_cargo = findViewById(R.id.spinner_cargo);
        //date_cargo=findViewById(R.id.date_cargo);
        desmonth_cargo = findViewById(R.id.cargo_desmonth);
        desday_cargo = findViewById(R.id.cargo_desday);
        error = findViewById(R.id.cargo_message_error);
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);

        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.cargo_desday_1);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 建立数据源
        String[] mItems_2 = getResources().getStringArray(R.array.cargo_desday_2);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems_2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 建立数据源
        String[] mItems_3 = getResources().getStringArray(R.array.cargo_desday_3);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems_3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //绑定 Adapter到控件
        desday_cargo.setAdapter(adapter2);

        desmonth_cargo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin_info2 = (TextView) view;
                Integer m = convertToInt(spin_info2.getText().toString(), 0);
                if (m == 2) {
                    desday_cargo.setAdapter(adapter1);
                } else if (m == 4 || m == 6 || m == 9 || m == 11) {
                    desday_cargo.setAdapter(adapter2);
                } else {
                    desday_cargo.setAdapter(adapter3);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CargoMessageInput.this, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });

        //进入页面时的信息处理
        try {
            user_Cargo userCargo = mWT_ViewModel.finduserCargoWithUsername(UserContext.getUser().getUsername()).get();
            //只用判断一个即可，因为填入信息时要求所有必填
            if (userCargo.name != null) {
                name_cargo.setText(userCargo.name);
                phone_cargo.setText(userCargo.phone);
                weight_cargo.setText(userCargo.cargo_weight + "");
                String[] str = getResources().getStringArray(R.array.spinner_array);
                int position = 0;
                for (String i : str) {
                    if (i.equals(userCargo.cargo_type))
                        break;
                    position++;
                }
                type_cargo.setSelection(position, true);
                depart_cargo.setText(userCargo.depart);
                dest_cargo.setText(userCargo.destin);
                desmonth_cargo.setSelection(userCargo.month - 1, true);
                desday_cargo.setSelection(userCargo.day - 1, true);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


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

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.submit_cargo:
                String name, phone, depart, destin, loadtype;
                int loadweight, desmonth, desday;
                name = name_cargo.getText().toString().trim();
                phone = phone_cargo.getText().toString().trim();
                //weight = convertToInt(weight_cargo.getText().toString(),0);
                loadweight = convertToInt(weight_cargo.getText().toString(), 0);
                depart = depart_cargo.getText().toString().trim();
                destin = dest_cargo.getText().toString().trim();
                loadtype = type_cargo.getSelectedItem().toString();
                desmonth = convertToInt(desmonth_cargo.getSelectedItem().toString(), 0);
                desday = convertToInt(desday_cargo.getSelectedItem().toString(), 0);

                if (name.isEmpty() || phone.isEmpty() || loadweight == 0 || depart.isEmpty() || destin.isEmpty() || loadtype.isEmpty() || desmonth == 0 || desday == 0) {
                    error.setText("输入信息不全");
                    error.setVisibility(View.VISIBLE);
                } else {
                    User user = UserContext.getUser();
                    user_Cargo userCargo = new user_Cargo(user.getUsername(), user.getPassword(), name, phone, loadweight, loadtype, depart, destin, desmonth, desday);
                    userCargo.setId(user.getId());
                    mWT_ViewModel.update(userCargo);
                    intent.setClass(CargoMessageInput.this, HomeMenuActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ib_menu:
                UserContext.user_center(intent, CargoMessageInput.this);
                break;
        }
    }

}