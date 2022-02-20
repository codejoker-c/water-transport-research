package com.example.helloworld.user;

import android.app.Activity;
import android.content.Intent;

public interface UserState {
    public void data_in(Intent intent, Activity activity);

    public void data_query(Intent intent,Activity activity);

    public void user_center(Intent intent,Activity activity);
}
