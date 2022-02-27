package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.database.User;
import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.user.UserContext;
import com.example.helloworld.viewmodel.WT_ViewModel;

public class CargoMessageInput extends AppCompatActivity {

    TextView spin_info2;
    EditText name_cargo, phone_cargo, weight_cargo, depart_cargo, dest_cargo, arrival_time;
    Spinner type_cargo;
    String str;
    WT_ViewModel mWT_ViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_message_input);

        spin_info2 = findViewById(R.id.spin_info_cargo);
        name_cargo = findViewById(R.id.name_cargo);
        phone_cargo = findViewById(R.id.phone_cargo);
        weight_cargo = findViewById(R.id.weight_cargo);
        depart_cargo = findViewById(R.id.depart_cargo);
        dest_cargo = findViewById(R.id.destination_cargo);
        type_cargo = findViewById(R.id.spinner_cargo);
        arrival_time = findViewById(R.id.date_cargo);
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
        /*
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
        */

    }

    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.submit_cargo:
                String name, phone, depart, destin, type, date;
                int weight;
                name = name_cargo.getText().toString().trim();
                phone = phone_cargo.getText().toString().trim();
                depart = depart_cargo.getText().toString().trim();
                destin = dest_cargo.getText().toString().trim();
                type = type_cargo.getSelectedItem().toString();
                date = arrival_time.getText().toString().trim();
                weight = Integer.parseInt(weight_cargo.getText().toString());


                User user = UserContext.getUser();
                user_Cargo userCargo = new user_Cargo(user.getUsername(), user.getPassword(), name, phone, weight, type, depart, destin, date);
                userCargo.setId(user.getId());
                mWT_ViewModel.update(userCargo);
                intent.setClass(CargoMessageInput.this,HomeMenuActivity.class);
                startActivity(intent);
                break;
        }

        startActivity(intent);
    }

}