package com.example.helloworld.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {user_Boat.class,user_Cargo.class},version = 5,exportSchema = false)
public abstract class WT_RoomDatabase extends RoomDatabase {
    //public abstract UserDao userDao();
    public abstract user_BoatDao user_boatDao();
    public abstract user_CargoDao user_cargoDao();

    private static volatile WT_RoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    //创建线程池，保证对数据库的操作都不是在主线程中进行的
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WT_RoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            //开了线程
            synchronized (WT_RoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WT_RoomDatabase.class,"WT_database")
                            .addCallback(sRoomsRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //数据库初始插入信息
    private static RoomDatabase.Callback sRoomsRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                user_CargoDao user_cargoDao = INSTANCE.user_cargoDao();
                user_BoatDao user_boatDao = INSTANCE.user_boatDao();
                user_cargoDao.deleteAllCargo();
                user_boatDao.deleteAllBoat();

                user_Boat userBoat = new user_Boat("liuzheng","123","刘铮","123456",1000,1000,"沙子","上海");
                user_Cargo userCargo = new user_Cargo("lijianqiang","123","李建强","123456",1000,"沙子","上海","重庆",1,1);

                user_cargoDao.insert(userCargo);
                user_boatDao.insert(userBoat);
            });
        }
    };
}
