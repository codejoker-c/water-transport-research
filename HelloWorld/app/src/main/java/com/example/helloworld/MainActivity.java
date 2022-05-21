package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helloworld.database.UserStatus;
import com.example.helloworld.database.User;
import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.user.UserContext;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {

    Button login_btn;
    TextView error;
    EditText login_phone;
    EditText login_password;
    WT_ViewModel mWT_ViewModel; // 对数据库的操作
    //private static final Logger logger = Logger.getGlobal();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        login_btn = findViewById(R.id.login_btn);
        login_phone = findViewById(R.id.login_phone);
        login_password = findViewById(R.id.login_password);
        error = findViewById(R.id.login_error);
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
        //logger.info(API.getHarbour());
        /*
        if(mWT_ViewModel.finduserCargoWithId(1)!=null)
        {
            logger.info("123");
            try {
                logger.info(mWT_ViewModel.finduserCargoWithId(2).get().name);
                user_Cargo userCargo = mWT_ViewModel.finduserCargoWithId(3).get();
                logger.info(userCargo.name+' '+userCargo.depart+' '+userCargo.destin);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

         */
    }


    public void onClick(View view) throws ExecutionException, InterruptedException {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.login_btn:
                String phone = login_phone.getText().toString().trim();
                String psd = login_password.getText().toString().trim();
                if(phone.isEmpty()){
                    error.setText("电话不能为空！");
                    error.setVisibility(view.VISIBLE);
                }
                else if(psd.isEmpty()){
                    error.setText("密码不能为空！");
                    error.setVisibility(view.VISIBLE);
                }
                else{
                    User user = mWT_ViewModel.findUserWithPhone(phone).get();
                    if(user==null){
                        error.setText("该用户名不存在");
                        error.setVisibility(view.VISIBLE);
                    }
                    else{
                        if(psd.equals(user.getPassword())){
                            intent.setClass(MainActivity.this,FootbarActivity.class);
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



