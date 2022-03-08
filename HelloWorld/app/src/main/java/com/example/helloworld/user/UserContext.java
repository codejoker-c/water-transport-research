package com.example.helloworld.user;


import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.helloworld.database.User;


public class UserContext {
    private static boolean isLogin = false;

    private static User user = null;

    private static UserState mState = new LogoutState();


    public static void data_in(Intent intent, Activity activity) {
        mState.data_in(intent, activity, user);
    }

    public static void data_query(Intent intent, Activity activity) {
        if (user.getIsFillInfo() == 1) {
            mState.data_query(intent, activity, user);
            activity.startActivity(intent);
        } else
            Toast.makeText(activity, "请先补充个人信息", Toast.LENGTH_SHORT).show();
    }

    public static void user_center(Intent intent, Activity activity) {
        mState.user_center(intent, activity);
    }

    public static void setLoginState(User user1) {
        user = user1;
        isLogin = true;
        mState = new LoginState();
    }

    public static void setLogoutState() {
        user = null;
        isLogin = false;
        mState = new LogoutState();
    }

    public static boolean getState() {
        return isLogin;
    }

    public static User getUser() {
        return user;
    }

}
