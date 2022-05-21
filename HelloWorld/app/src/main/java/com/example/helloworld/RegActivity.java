package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.helloworld.database.User;
import com.example.helloworld.user.UserContext;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class RegActivity extends AppCompatActivity {

    EditText reg_phone, reg_password, reg_password_confirm;
    TextView error;
    //Spinner reg_indentity;
    private WT_ViewModel mWT_ViewModel;//实例化WT_ViewModel来与数据库进行交互
    private static final Logger logger = Logger.getGlobal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        reg_phone = findViewById(R.id.reg_phone);
        reg_password = findViewById(R.id.reg_password);
        reg_password_confirm = findViewById(R.id.reg_password_confirm);
        //reg_indentity=findViewById(R.id.reg_identity);
        //显示错误信息
        error = findViewById(R.id.reg_error);

        //通过ViewModelProvider来获取WT_ViewModel实例，然后与数据库交互
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);

    }

    public void onClick(View view) throws ExecutionException, InterruptedException {
        Intent intent = new Intent(this, RegActivity.class);
        switch (view.getId()) {
            case R.id.reg_button:
                String psd = reg_password.getText().toString().trim();
                String psd_confirm = reg_password_confirm.getText().toString().trim();
                String phone = reg_phone.getText().toString().trim();
                //String str = reg_indentity.getSelectedItem().toString();
                if (phone.isEmpty()) {
                    error.setText("用户名不能为空！");
                    error.setVisibility(view.VISIBLE);
                } else if (psd.isEmpty()) {
                    error.setText("密码不能为空！");
                    error.setVisibility(view.VISIBLE);
                } else if (psd_confirm.isEmpty()) {
                    error.setText("请确认您的密码");
                    error.setVisibility(view.VISIBLE);
                } else if (!psd.equals(psd_confirm)) {
                    error.setText("两次密码输入不一致");
                    error.setVisibility(view.VISIBLE);
                } else {
                    User muser = mWT_ViewModel.findUserWithPhone(phone).get();
                    if (muser != null) {
                        error.setText("该用户名已存在！");
                        error.setVisibility(view.VISIBLE);
                    } else {

                        intent.setClass(RegActivity.this, CompleteMessage.class);
                        muser = new User(phone, psd);
                        mWT_ViewModel.insert(muser);
                        User user;
                        while((user = mWT_ViewModel.findUserWithPhone(phone).get())==null)
                            ;
                        UserContext.setLoginState(user);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.login_textview:
                intent.setClass(RegActivity.this, MainActivity.class);
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

