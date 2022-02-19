package com.example.helloworld;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class WT_RoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();

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
                UserDao dao = INSTANCE.userDao();
                dao.deleteAll();

                User user =new User("joker","123456");
                dao.insert(user);
            });
        }
    };
}
