package com.example.helloworld;

import android.app.Application;

import com.google.common.util.concurrent.ListenableFuture;

class WT_Repository {
    private UserDao mUserDao;

    WT_Repository(Application application){
        WT_RoomDatabase db = WT_RoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
    }

    void insert(User user){
        WT_RoomDatabase.databaseWriteExecutor.execute(()-> {
            mUserDao.insert(user);
        });
    }

    ListenableFuture<User> findUserWithUsername(String un){
        return mUserDao.findUserWithUsername(un);
    }
}
