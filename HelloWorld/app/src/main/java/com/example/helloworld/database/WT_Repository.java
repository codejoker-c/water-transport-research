package com.example.helloworld.database;

import android.app.Application;


import com.google.common.util.concurrent.ListenableFuture;

public class WT_Repository {
    private UserDao mUserDao;

    public WT_Repository(Application application){
        WT_RoomDatabase db = WT_RoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
    }

    public void insert(User user){
        WT_RoomDatabase.databaseWriteExecutor.execute(()-> {
            mUserDao.insert(user);
        });
    }

    public ListenableFuture<User> findUserWithUsername(String un){
        return mUserDao.findUserWithUsername(un);
    }
}
