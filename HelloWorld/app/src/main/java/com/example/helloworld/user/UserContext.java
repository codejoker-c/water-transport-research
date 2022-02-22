package com.example.helloworld.user;


import android.app.Activity;
import android.content.Intent;

import com.example.helloworld.database.User;


public class UserContext {
    private static boolean isLogin=false;

    private static User user=null;

    private static UserState mState=new LogoutState();



    public static void data_in(Intent intent, Activity activity){
        mState.data_in(intent, activity);
    }

    public static void data_query(Intent intent, Activity activity){
        mState.data_query(intent, activity);
    }

    public static void user_center(Intent intent, Activity activity){
        mState.user_center(intent, activity);
    }

    public static void setLoginState(User user1){
        user = user1;
        isLogin = true;
        mState = new LoginState();
    }

    public static void setLogoutState(){
        user = null;
        isLogin = false;
        mState = new LogoutState();
    }

    public static boolean getState(){
        return isLogin;
    }

    public static User getUser(){
        return user;
    }


}
