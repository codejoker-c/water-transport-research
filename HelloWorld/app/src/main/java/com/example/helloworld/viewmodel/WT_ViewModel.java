package com.example.helloworld.viewmodel;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.helloworld.database.User;
import com.example.helloworld.database.WT_Repository;
import com.example.helloworld.database.user_Boat;
import com.example.helloworld.database.user_Cargo;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class WT_ViewModel extends AndroidViewModel {
    private WT_Repository mRepository;


    public WT_ViewModel(Application application){
        super(application);
        mRepository = new WT_Repository(application);
    }

    //插入数据，参数为User类
    public void insert(user_Boat userBoat){
        mRepository.insert(userBoat);
    }

    public void insert(user_Cargo userCargo){
        mRepository.insert(userCargo);
    }

    //通过username字段查询数据，返回User类，因为是异步查询，即开了新线程，故加了ListenableFuture来修饰
    //通过ListenableFuture.get()方法可以获得User类
    public ListenableFuture<user_Boat> finduserBoatWithUsername(String un){
        return mRepository.finduserBoatWithUsername(un);
    }

    public ListenableFuture<user_Cargo> finduserCargoWithUsername(String un){
        return mRepository.finduserCargoWithUsername(un);
    }

    public LiveData<List<user_Boat>> getAlluserBoat(){
        return mRepository.getAlluserBoat();
    }

    public LiveData<List<user_Cargo>> getAlluserCargo(){
        return mRepository.getAlluserCargo();
    }

    public void update(user_Cargo userCargo){
        mRepository.update(userCargo);
    }

    public void update(user_Boat userBoat){
        mRepository.update(userBoat);
    }
}
