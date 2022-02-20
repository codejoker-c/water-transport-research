package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BoatMessageInput extends AppCompatActivity {

    TextView spin_info;
    EditText name_ship,phone_ship,weight_ship,kg_ship,depart_ship;
    Spinner spinner;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boat_message_input);

        spin_info=findViewById(R.id.spin_info_ship);
        name_ship=findViewById(R.id.name_ship);
        phone_ship=findViewById(R.id.phone_ship);
        weight_ship=findViewById(R.id.weight_ship);
        kg_ship=findViewById(R.id.kg_ship);
        depart_ship=findViewById(R.id.depart_ship);
        spinner=findViewById(R.id.spinner_01);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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



    }

    public void onClick(View view) {
        Intent intent=new Intent();

        switch (view.getId()) {
            case R.id.submit_ship:
                intent.setClass(BoatMessageInput.this,HomeMenuActivity.class);
        }

        startActivity(intent);
    }

}