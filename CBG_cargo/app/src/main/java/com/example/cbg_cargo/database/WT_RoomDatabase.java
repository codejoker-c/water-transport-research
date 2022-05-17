package com.example.cbg_cargo.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class WT_RoomDatabase extends RoomDatabase {
    // 覆写了父类中的方法
    public abstract UserDao userDao();
    public abstract OrderDao orderDao();

    // volatile 修饰的变量解决了变量在不同进程间的不可见性
    public static volatile WT_RoomDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;

    //创建线程池，保证对数据库的操作都不是在主线程中进行的
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WT_RoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (WT_RoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WT_RoomDatabase.class,"WT_database")
                            //.addCallback(sRoomsRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

