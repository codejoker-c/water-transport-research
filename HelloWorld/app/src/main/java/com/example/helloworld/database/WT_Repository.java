package com.example.helloworld.database;

import android.app.Application;


import com.google.common.util.concurrent.ListenableFuture;

public class WT_Repository {
    private user_BoatDao muser_boatDao;
    private user_CargoDao muser_cargoDao;


    public WT_Repository(Application application){
        WT_RoomDatabase db = WT_RoomDatabase.getDatabase(application);
        muser_boatDao = db.user_boatDao();
        muser_cargoDao = db.user_cargoDao();
    }

    public void insert(user_Boat userBoat){
        WT_RoomDatabase.databaseWriteExecutor.execute(()-> {
            muser_boatDao.insert(userBoat);
        });
    }

    public void insert(user_Cargo userCargo){
        WT_RoomDatabase.databaseWriteExecutor.execute(()->{
            muser_cargoDao.insert(userCargo);
        });
    }

    public ListenableFuture<user_Boat> finduserBoatWithUsername(String un){
        return muser_boatDao.finduserBoatWithUsername(un);
    }

    public ListenableFuture<user_Cargo> finduserCargoWithUsername(String un){
        return muser_cargoDao.finduserCargoWithUsername(un);
    }
}
