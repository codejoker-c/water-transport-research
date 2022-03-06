package com.example.helloworld.user;



import android.app.Activity;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContracts;

import com.example.helloworld.AboutActivity2;
import com.example.helloworld.BoatMessageInput;
import com.example.helloworld.CargoMessageInput;
import com.example.helloworld.ResultQuery;
import com.example.helloworld.UserActivity;
import com.example.helloworld.database.Status;
import com.example.helloworld.database.User;

public class LoginState implements UserState {
    LoginState(){

    }

    @Override
    public void data_in(Intent intent, Activity activity, User user){
        if(user.status== Status.boat)
            intent.setClass(activity, BoatMessageInput.class);
        else
            intent.setClass(activity, CargoMessageInput.class);
    }

    @Override
    public void data_query(Intent intent,Activity activity){
        intent.setClass(activity, ResultQuery.class);
    }

    @Override
    public void user_center(Intent intent, Activity activity){
        intent.setClass(activity, UserActivity.class);
    }
}
