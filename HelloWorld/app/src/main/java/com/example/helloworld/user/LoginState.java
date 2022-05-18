package com.example.helloworld.user;


import android.app.Activity;
import android.content.Intent;

import com.example.helloworld.BoatMessageInput;
import com.example.helloworld.CargoMessageInput;
import com.example.helloworld.BoatQuery;
import com.example.helloworld.CargoQuery;
import com.example.helloworld.ui.user.UserFragment;
import com.example.helloworld.database.UserStatus;
import com.example.helloworld.database.User;

public class LoginState implements UserState {
    LoginState() {

    }

    @Override
    public void data_in(Intent intent, Activity activity, User user) {
        intent.setClass(activity, BoatMessageInput.class);
    }

    @Override
    public void data_query(Intent intent, Activity activity, User user) {
        intent.setClass(activity, BoatQuery.class);
    }

    @Override
    public void user_center(Intent intent, Activity activity) {
        intent.setClass(activity, UserFragment.class);
    }
}
