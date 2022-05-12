package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.user.UserContext;

public class UserActivity extends AppCompatActivity {

    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
//        Toolbar myToolbar = findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);
//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);
        String name;
        TextView name_set;
        name_set=findViewById(R.id.get_user_name);
        name=UserContext.getUser().getUsername();
        name_set.setText(name);
//        logout = findViewById(R.id.logout_btn);
//
//        logout.setOnClickListener(view -> {
//            Intent intent = new Intent(UserActivity.this,MainActivity.class);
//            UserContext.setLogoutState();
//            startActivity(intent);
//        });
    }

}