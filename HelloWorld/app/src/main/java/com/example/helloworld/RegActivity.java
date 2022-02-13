package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RegActivity extends AppCompatActivity {

    EditText reg_username,reg_password,reg_password_confirm;
    TextView wrong_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        reg_username=findViewById(R.id.reg_username);
        reg_password=findViewById(R.id.reg_password);
        reg_password_confirm=findViewById(R.id.reg_password_confirm);
        wrong_pass=findViewById(R.id.wrong_password);



    }

    public void onClick(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.reg_button:

                String msg1=reg_password.getText().toString().trim();
                String msg2=reg_password_confirm.getText().toString().trim();
/*                wrong_pass.setText(msg2);
                wrong_pass.setVisibility(view.VISIBLE);*/
                if(msg1.equals(msg2)==true){
                    intent.setClass(RegActivity.this,HomeMenuActivity.class);
/*                    if (TextUtils.isEmpty(msg1)) {
                        Toast.makeText(this,"输入内容不能为空！",Toast.LENGTH_SHORT).show();
                        return;
                    }*/
                }
                else{
                    wrong_pass.setVisibility(view.VISIBLE);
                    return;
                }

                break;
        }
        startActivity(intent);
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

