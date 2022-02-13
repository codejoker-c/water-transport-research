package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    //ImageView imageview;
    TextView textView;
    EditText editText1;
    EditText editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        //imageview=findViewById(R.id.login_pic);
        textView=findViewById(R.id.textView);
        editText1=findViewById(R.id.editTextTextPersonName);
        editText2=findViewById(R.id.editTextTextPassword);


        //imageview.setImageResource(R.drawable.cdbfd312b6e69308fb8dcbcd1231d2d);
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageview.setVisibility(View.VISIBLE);
            }
        });*/

/*        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,HomeMenuActivity.class);
                startActivity(intent);
            }
        });*/

    }


    public void onClick(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.button:
                intent.setClass(MainActivity.this,HomeMenuActivity.class);
                break;
            case R.id.register:
                intent.setClass(MainActivity.this,RegActivity.class);
                break;
        }
        startActivity(intent);


    }
}



