package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

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

public class CargoMessageInput extends AppCompatActivity {

    TextView spin_info2;
    EditText name_cargo,phone_cargo,weight_cargo,depart_cargo,dest_cargo,date_cargo;
    Spinner spinner2,desmonth_cargo,desday_cargo;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_message_input);

        name_cargo=findViewById(R.id.name_cargo);

        spin_info2=findViewById(R.id.spin_info_cargo);
        name_cargo=findViewById(R.id.name_cargo);
        phone_cargo=findViewById(R.id.phone_cargo);
        weight_cargo=findViewById(R.id.weight_cargo);
        depart_cargo=findViewById(R.id.depart_cargo);
        dest_cargo=findViewById(R.id.destination_cargo);
        spinner2=findViewById(R.id.spinner_cargo);
        //date_cargo=findViewById(R.id.date_cargo);
        desmonth_cargo=findViewById(R.id.cargo_desmonth);
        desday_cargo=findViewById(R.id.cargo_desday);

        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.cargo_desday_1);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 建立数据源
        String[] mItems_2 = getResources().getStringArray(R.array.cargo_desday_2);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems_2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 建立数据源
        String[] mItems_3 = getResources().getStringArray(R.array.cargo_desday_3);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems_3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //绑定 Adapter到控件
        desday_cargo.setAdapter(adapter2);

        desmonth_cargo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin_info2=(TextView) view;
                Integer m=convertToInt(spin_info2.getText().toString(),0);
                if(m==2)
                {
                    desday_cargo.setAdapter(adapter1);
                }
                else if(m==4 || m==6 ||m==9 ||m==11)
                {
                    desday_cargo.setAdapter(adapter2);
                }
                else
                {
                    desday_cargo.setAdapter(adapter3);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CargoMessageInput.this, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });


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
        Intent intent=new Intent();




        switch (view.getId()) {
            case R.id.submit_cargo:

                String name,phone,depart,desti,loadtype;
                int loadweight,desmonth,desday;
                name = name_cargo.getText().toString().trim();
                phone = phone_cargo.getText().toString().trim();
                //weight = convertToInt(weight_cargo.getText().toString(),0);
                loadweight = convertToInt(weight_cargo.getText().toString(),0);
                depart = depart_cargo.getText().toString().trim();
                desti=dest_cargo.getText().toString().trim();
                loadtype = spinner2.getSelectedItem().toString();
                desmonth=convertToInt(desmonth_cargo.getSelectedItem().toString(),0);
/*                if(desmonth==2)
                {
                    ;
                }
                else if(desmonth==4 || desmonth==6 ||desmonth==9 ||desmonth==11)
                {
                    desday_cargo.setAdapter(adapter2);
                }
                else
                {
                    desday_cargo.setAdapter(adapter3);
                }*/
                desday=convertToInt(desday_cargo.getSelectedItem().toString(),0);





                intent.setClass(CargoMessageInput.this,ResultQuery.class);
                break;
        }

        startActivity(intent);
    }

}