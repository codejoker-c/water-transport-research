package com.example.helloworld.database;

import android.app.Application;


import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class WT_Repository {
    private UserDao muserDao;
    private OrderDao morderDao;

    private user_CargoDao muser_cargoDao;
    //private LiveData<List<user_Boat>> mAll_userBoat;
    //private LiveData<List<user_Cargo>> mAll_userCargo;


    public WT_Repository(Application application) {
        WT_RoomDatabase db = WT_RoomDatabase.getDatabase(application);
        muserDao = db.UserDao();
        morderDao = db.orderDao();

        muser_cargoDao = db.user_cargoDao();
    }

    public void insert(User user) {
        WT_RoomDatabase.databaseWriteExecutor.execute(() -> {
            muserDao.insert(user);
        });
    }

    public void insert(Order order) {
        WT_RoomDatabase.databaseWriteExecutor.execute(() -> {
            morderDao.insert(order);
        });
    }

    public ListenableFuture<User> findUserWithPhone(String phone) {
        return muserDao.findUserWithPhone(phone);
    }

    public void deleteAllUser() {
        WT_RoomDatabase.databaseWriteExecutor.execute(() -> {
            muserDao.deleteAll();
        });
    }


    public void insert(user_Cargo userCargo) {
        WT_RoomDatabase.databaseWriteExecutor.execute(() -> {
            muser_cargoDao.insert(userCargo);
        });
    }


    public ListenableFuture<user_Cargo> finduserCargoWithId(Integer id) {
        return muser_cargoDao.finduserCargoWithId(id);
    }

    public ListenableFuture<user_Cargo> finduserCargoWithPhone(String phone) {
        return muser_cargoDao.finduserCargoWithPhone(phone);
    }

    public void update(user_Cargo userCargo) {
        WT_RoomDatabase.databaseWriteExecutor.execute(() -> {
            muser_cargoDao.updata(userCargo);
        });
    }


    public LiveData<List<user_Cargo>> getAlluserCargo() {
        return muser_cargoDao.getAlluserCargo();
    }

    public LiveData<List<User>> getAllUser(){
        return muserDao.getAllUser();
    }

    public void deleteAllCargo() {
        WT_RoomDatabase.databaseWriteExecutor.execute(() -> {
            muser_cargoDao.deleteAllCargo();
        });
    }
    // 通过id删除货主信息
    public void deleteuser_CargoWithId(Integer id) {
        WT_RoomDatabase.databaseWriteExecutor.execute(()->{
            muser_cargoDao.deleteuser_CargoWithId(id);
        });
    }

    public LiveData<List<Order>> findOrdersWithuserId(Integer userId){
        return morderDao.findOrdersWithuserId(userId);
    }

}
