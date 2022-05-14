package com.example.cbg_cargo.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class WT_Repository {
    private final UserDao mUserDao;
    private final OrderDao mOrderDao;


    public WT_Repository(Application application){
        WT_RoomDatabase db = WT_RoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mOrderDao = db.orderDao();
    }

    public void insert(User user){
        WT_RoomDatabase.databaseWriteExecutor.execute(()->{
            mUserDao.insert(user);
        });
    }

    public void insert(Order order){
        WT_RoomDatabase.databaseWriteExecutor.execute(()->{
            mOrderDao.insert(order);
        });
    }

    public ListenableFuture<User> findUserWithId(Integer id){
        return mUserDao.findUserWithId(id);
    }

    public LiveData<List<Order>> findOrdersWithuserId(Integer userId){
        return mOrderDao.findOrdersWithuserId(userId);
    }

    public void deleteOrderWithuserId(Integer userId){
        WT_RoomDatabase.databaseWriteExecutor.execute(()->{
            mOrderDao.deleteOrdersWithuserId(userId);
        });
    }
}
