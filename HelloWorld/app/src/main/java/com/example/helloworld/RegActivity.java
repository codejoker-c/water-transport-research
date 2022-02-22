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

import com.example.helloworld.database.Status;
import com.example.helloworld.database.User;
import com.example.helloworld.database.user_Boat;
import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RegActivity extends AppCompatActivity {

    EditText reg_username,reg_password,reg_password_confirm;
    TextView error;
    Spinner reg_indentity;
    private WT_ViewModel mWT_ViewModel;//实例化WT_ViewModel来与数据库进行交互

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        reg_username=findViewById(R.id.reg_username);
        reg_password=findViewById(R.id.reg_password);
        reg_password_confirm=findViewById(R.id.reg_password_confirm);
        reg_indentity=findViewById(R.id.reg_identity);
        //显示错误信息
        error=findViewById(R.id.reg_error);

        //通过ViewModelProvider来获取WT_ViewModel实例，然后与数据库交互
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);

        /*
        reg_indentity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str=((TextView)view).toString();   //str存放选择的字符串

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */

    }

    public void onClick(View view) throws ExecutionException, InterruptedException {
        Intent intent=new Intent(this,RegActivity.class);
        switch (view.getId()) {
            case R.id.reg_button:
                String psd=reg_password.getText().toString().trim();
                String psd_confirm=reg_password_confirm.getText().toString().trim();
                String username = reg_username.getText().toString().trim();
                String str = reg_indentity.getSelectedItem().toString();
                if(username.isEmpty()){
                    error.setText("用户名不能为空！");
                    error.setVisibility(view.VISIBLE);
                }
                else if(psd.isEmpty()){
                    error.setText("密码不能为空！");
                    error.setVisibility(view.VISIBLE);
                }
                else if(psd_confirm.isEmpty()){
                    error.setText("请确认您的密码");
                    error.setVisibility(view.VISIBLE);
                }
                else if (!psd.equals(psd_confirm)){
                    error.setText("两次密码输入不一致");
                    error.setVisibility(view.VISIBLE);
                }
                else{
                    User muser = null;

                    if(str.equals("船主"))
                        muser = mWT_ViewModel.finduserBoatWithUsername(username).get();
                    else
                        muser = mWT_ViewModel.finduserCargoWithUsername(username).get();

                    if(muser!=null){
                        error.setText("该用户名已存在！");
                        error.setVisibility(view.VISIBLE);
                    }
                    else{
                        if(str.equals("船主"))
                            mWT_ViewModel.insert(new user_Boat(username,psd));
                        else
                            mWT_ViewModel.insert(new user_Cargo(username,psd));

                        intent.setClass(RegActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    List<user_Boat> u = mWT_ViewModel.getAlluserBoat().getValue();
                    /*
                    User muser = mWT_ViewModel.findUserWithUsername(username).get();
                    if(muser!=null){
                        error.setText("该用户名已存在！");
                        error.setVisibility(view.VISIBLE);
                    }
                    else{
                        if(psd.equals(psd_confirm)){
                            intent.setClass(RegActivity.this,MainActivity.class);
                            User new_user = new User(username,psd);
                            mWT_ViewModel.insert(new_user);
                            startActivity(intent);
                        }
                        else{
                            error.setText("两次输入的密码不一致！");
                            error.setVisibility(view.VISIBLE);
                        }
                    }
                    */
                }
                break;
            case R.id.login_textview:
                intent.setClass(RegActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    //你需要监听的edit对象调用addTextChangedListener方法
//传递参数时重写这个MyEditTextChangeListener类的onTextChanged方法
//在onTextChanged方法中s.toString就是你正在输入的结果
/*        reg_username.addTextChangedListener(new MyEditTextChangeListener(){
        *//**
         * 编辑框的内容正在发生改变时的回调方法 >>用户正在输入
         * 我们可以在这里实时地 通过搜索匹配用户的输入
         *//*
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String sss = s.toString();
            Log.d("值",sss);
        }

    });*/

}

