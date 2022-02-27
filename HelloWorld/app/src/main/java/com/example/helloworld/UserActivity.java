package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.helloworld.user.UserContext;

public class UserActivity extends AppCompatActivity {

    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        logout = findViewById(R.id.logout_btn);

        logout.setOnClickListener(view -> {
            Intent intent = new Intent(UserActivity.this,MainActivity.class);
            UserContext.setLogoutState();
            startActivity(intent);
        });
    }

}