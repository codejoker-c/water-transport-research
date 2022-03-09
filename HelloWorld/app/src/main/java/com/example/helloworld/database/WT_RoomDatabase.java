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

                //user_Boat[] userBoat = new user_Boat[10];// = new user_Boat("liuzheng","123","刘铮","123456",1000,1000,"沙土石子","上海");
                //userBoat.setIsFillInfo();
                user_Cargo[] userCargo = new user_Cargo[10];//new user_Cargo("lijianqiang","123","李建强","123456",1000,"沙土石子","上海","重庆",1,1);

                userCargo[0] = new
                        user_Cargo("mql","123","马千里","10086",800,"沙土石子","重庆","上海",3,26);
                userCargo[1] = new
                        user_Cargo("cj","123","陈静","10086",1000,"煤炭","镇江","上海",3,29);
                userCargo[2] = new
                        user_Cargo("wm","123","吴敏","10086",2000,"沙土石子","巫山","南京",4,6);
                userCargo[3] = new
                        user_Cargo("lhf","123","刘航帆","10086",600,"煤矿","江阴","岳阳",4,3);
                userCargo[4] = new
                        user_Cargo("lhp","123","李和平","10086",1100,"矿石","重庆","南通",3,25);
                userCargo[5] = new
                        user_Cargo("cjn","123","程敬南","10086",2800,"矿石","黄石","铜陵",3,20);
                userCargo[6] = new
                        user_Cargo("hy","123","华勇","10086",1200,"沙土石子","芜湖","上海",4,5);
                userCargo[7] = new
                        user_Cargo("wj","123","王军","10086",1800,"沙土石子","江陵","南京",4,6);
                userCargo[8] = new
                        user_Cargo("zzw","123","张中文","10086",1250,"煤炭","黄石","镇江",4,5);
                userCargo[9] = new
                        user_Cargo("zsm","123","赵生明","10086",1400,"矿石","岳阳","江阴",4,7);



                for(int i=0;i<10;i++){
                    userCargo[i].setIsFillInfo();
                    user_cargoDao.insert(userCargo[i]);
                }

                //userCargo.setIsFillInfo();
                //user_cargoDao.insert(userCargo);
                //user_boatDao.insert(userBoat);
            });
        }
    };
}
