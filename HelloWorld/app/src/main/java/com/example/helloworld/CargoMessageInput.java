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

public class CargoMessageInput extends AppCompatActivity {

    TextView spin_info2;
    EditText name_cargo,phone_cargo,weight_cargo,depart_cargo,dest_cargo;
    Spinner spinner2;
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

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin_info2=(TextView) view;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CargoMessageInput.this, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void onClick(View view) {
        Intent intent=new Intent();

        switch (view.getId()) {
            case R.id.submit_cargo:
                intent.setClass(CargoMessageInput.this,HomeMenuActivity.class);
        }

        startActivity(intent);
    }

}