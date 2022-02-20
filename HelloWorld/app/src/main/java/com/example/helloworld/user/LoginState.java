package com.example.helloworld.user;


import android.app.Activity;
import android.content.Intent;

import com.example.helloworld.AboutActivity2;
import com.example.helloworld.UserActivity;

public class LoginState implements UserState {
    LoginState(){

    }

    @Override
    public void data_in(Intent intent, Activity activity){
        intent.setClass(activity, AboutActivity2.class);
    }

    @Override
    public void data_query(Intent intent,Activity activity){
        intent.setClass(activity,AboutActivity2.class);
    }

    @Override
    public void user_center(Intent intent, Activity activity){
        intent.setClass(activity, UserActivity.class);
    }
}
