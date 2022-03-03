package com.example.helloworld.viewmodel;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.helloworld.database.User;
import com.example.helloworld.database.WT_Repository;
import com.example.helloworld.database.user_Boat;
import com.example.helloworld.database.user_Cargo;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import javax.annotation.Nullable;

public class WT_ViewModel extends AndroidViewModel {
    private WT_Repository mRepository;
    //private LiveData<List<user_Boat>> mAll_userBoat;
    private final LiveData<List<user_Cargo>> mAll_userCargo;
    //private MediatorLiveData<List<user_Cargo>> mAll_userCargo = new MediatorLiveData<>();


    public WT_ViewModel(Application application) {
        super(application);
        mRepository = new WT_Repository(application);
        //mAll_userBoat = mRepository.getAlluserBoat();
        mAll_userCargo = mRepository.getAlluserCargo();
    }

    //插入数据，参数为User类
    public void insert(user_Boat userBoat) {
        mRepository.insert(userBoat);
    }

    public void insert(user_Cargo userCargo) {
        mRepository.insert(userCargo);
    }

    //通过username字段查询数据，返回User类，因为是异步查询，即开了新线程，故加了ListenableFuture来修饰
    //通过ListenableFuture.get()方法可以获得User类
    public ListenableFuture<user_Boat> finduserBoatWithUsername(String un) {
        return mRepository.finduserBoatWithUsername(un);
    }

    public ListenableFuture<user_Boat> finduserBoatWithId(Integer id) {
        return mRepository.finduserBoatWithId(id);
    }

    public ListenableFuture<user_Cargo> finduserCargoWithUsername(String un) {
        return mRepository.finduserCargoWithUsername(un);
    }

    public ListenableFuture<user_Cargo> finduserCargoWithId(Integer id) {
        return mRepository.finduserCargoWithId(id);
    }

    //Boat查询改为获取数据库中信息的快照，即使用ListenableFuture类
    public ListenableFuture<List<user_Boat>> getAlluserBoat() {
        return mRepository.getAlluserBoat();
    }

    //Cargo查询仍然使用LiveData，因为LiveData方便货主信息界面更新信息
    public LiveData<List<user_Cargo>> getAlluserCargo() {
        return mAll_userCargo;
    }

    public void update(user_Cargo userCargo) {
        mRepository.update(userCargo);
    }

    public void update(user_Boat userBoat) {
        mRepository.update(userBoat);
    }
}
