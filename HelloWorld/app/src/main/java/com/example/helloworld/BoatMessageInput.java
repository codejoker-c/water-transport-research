package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.database.User;
import com.example.helloworld.database.user_Boat;
import com.example.helloworld.user.UserContext;

public class BoatMessageInput extends AppCompatActivity {

    TextView spin_info,error;
    EditText name_ship,phone_ship,weight_ship,loadweight_ship,depart_ship;
    Spinner load_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boat_message_input);

        spin_info=findViewById(R.id.spin_info_ship);
        name_ship=findViewById(R.id.name_ship);
        phone_ship=findViewById(R.id.phone_ship);
        weight_ship=findViewById(R.id.weight_ship);
        loadweight_ship=findViewById(R.id.loadweight_ship);
        depart_ship=findViewById(R.id.depart_ship);
        load_type=findViewById(R.id.spinner_01);
        error = findViewById(R.id.boat_info_error);
        /*
        load_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin_info=(TextView) view;
                spin_info.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(BoatMessageInput.this, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });

         */



    }
    //把String转化为int
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
            case R.id.submit_ship:
                String name,phone,depart,loadtype;
                int weight,loadweight;
                name = name_ship.getText().toString().trim();
                phone = phone_ship.getText().toString().trim();
                weight = convertToInt(weight_ship.getText().toString(),0);
                loadweight = convertToInt(loadweight_ship.getText().toString(),0);
                depart = depart_ship.getText().toString().trim();
                loadtype = load_type.getSelectedItem().toString();

                /*
                if(name.isEmpty()||phone.isEmpty()||weight.isEmpty()||loadweight.isEmpty()||depart.isEmpty()||loadtype.isEmpty()){
                    error.setText("信息填写不完整！");
                    error.setVisibility(view.VISIBLE);
                }
                else{
                    User user = UserContext.getUser();
                    user_Boat userBoat = new user_Boat(user.getUsername(),user.getPassword(),name,phone,loadtype,depart);
                }

                 */

                intent.setClass(BoatMessageInput.this,HomeMenuActivity.class);
                break;

        }

        startActivity(intent);
    }

}