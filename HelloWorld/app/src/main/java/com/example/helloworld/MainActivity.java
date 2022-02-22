package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import android.util.Log;
import com.chaquo.python.Kwarg;
import com.chaquo.python.PyObject;
import com.chaquo.python.android.AndroidPlatform;
import com.chaquo.python.Python;
import java.util.ArrayList;
import java.util.List;

import com.example.helloworld.database.User;
import com.example.helloworld.user.UserContext;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button login_btn;
    //ImageView imageview;
    TextView textView,error;
    EditText login_username;
    EditText login_password;
    Spinner login_identity;
    WT_ViewModel mWT_ViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = findViewById(R.id.login_btn);
        //imageview=findViewById(R.id.login_pic);
        textView = findViewById(R.id.reg_textView);
        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        login_identity=findViewById(R.id.login_identity);
        error = findViewById(R.id.login_error);
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);

        login_identity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str=((TextView) view).toString();   //str 用来存放船主或货主的身份信息


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void onClick(View view) throws ExecutionException, InterruptedException {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.login_btn:
                String username = login_username.getText().toString().trim();
                String psd = login_password.getText().toString().trim();
                if(username.isEmpty()){
                    error.setText("用户名不能为空！");
                    error.setVisibility(view.VISIBLE);
                }
                else if(psd.isEmpty()){
                    error.setText("密码不能为空！");
                    error.setVisibility(view.VISIBLE);
                }
                else{
                    User user = mWT_ViewModel.findUserWithUsername(username).get();
                    if(user==null){
                        error.setText("该用户名不存在");
                        error.setVisibility(view.VISIBLE);
                    }
                    else{
                        if(psd.equals(user.getPassword())){
                            intent.setClass(MainActivity.this,HomeMenuActivity.class);
                            UserContext.setLoginState(user);
                            startActivity(intent);
                        }
                        else{
                            error.setText("密码错误！");
                            error.setVisibility(view.VISIBLE);
                        }
                    }
                }
                break;
            case R.id.register:
                intent.setClass(MainActivity.this,RegActivity.class);
                startActivity(intent);
                break;
            case R.id.ib_menu:
                UserContext.user_center(intent,MainActivity.this);
                startActivity(intent);
                break;
        }


    }


}



