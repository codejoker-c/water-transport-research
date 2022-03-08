package com.example.helloworld.user;


import android.app.Activity;
import android.content.Intent;

import com.example.helloworld.AboutActivity2;
import com.example.helloworld.MainActivity;
import com.example.helloworld.database.User;

public class LogoutState implements UserState {

    LogoutState(){

    }

    @Override
    public void data_in(Intent intent, Activity activity, User user){
        intent.setClass(activity, MainActivity.class);
    }

    @Override
    public void data_query(Intent intent,Activity activity,User user){
        intent.setClass(activity, MainActivity.class);
    }

    @Override
    public void user_center(Intent intent,Activity activity){
        intent.setClass(activity, MainActivity.class);
    }
}
