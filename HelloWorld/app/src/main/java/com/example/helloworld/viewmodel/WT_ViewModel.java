package com.example.helloworld.viewmodel;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.helloworld.database.Order;
import com.example.helloworld.database.User;
import com.example.helloworld.database.WT_Repository;
import com.example.helloworld.database.user_Cargo;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class WT_ViewModel extends AndroidViewModel {
    private WT_Repository mRepository;

    private final LiveData<List<user_Cargo>> mAll_userCargo;
    private final ListenableFuture<List<User>> mAllUser;
    //private MediatorLiveData<List<user_Cargo>> mAll_userCargo = new MediatorLiveData<>();


    public WT_ViewModel(Application application) {
        super(application);
        mRepository = new WT_Repository(application);
        mAll_userCargo = mRepository.getAlluserCargo();
        mAllUser = mRepository.getAllUser();
    }

    //插入数据，参数为User类

    public void insert(user_Cargo userCargo) {
        mRepository.insert(userCargo);
    }

    public void insert(User user){
        mRepository.insert(user);
    }

    public void insert(Order order){mRepository.insert(order);}

    //通过username字段查询数据，返回User类，因为是异步查询，即开了新线程，故加了ListenableFuture来修饰
    //通过ListenableFuture.get()方法可以获得User类

    public ListenableFuture<User> findUserWithPhone(String phone){
        return mRepository.findUserWithPhone(phone);
    }

    public ListenableFuture<user_Cargo> finduserCargoWithPhone(String phone) {
        return mRepository.finduserCargoWithPhone(phone);
    }

    public ListenableFuture<user_Cargo> finduserCargoWithId(Integer id) {
        return mRepository.finduserCargoWithId(id);
    }

    //Cargo查询仍然使用LiveData，因为LiveData方便货主信息界面更新信息
    public LiveData<List<user_Cargo>> getAlluserCargo() {
        return mAll_userCargo;
    }

    public ListenableFuture<List<User>> getAllUser(){
        return mAllUser;
    }

    public void update(user_Cargo userCargo) {
        mRepository.update(userCargo);
    }
    public void update(User user) {
        mRepository.update(user);
    }

    public void deleteAllCargo() {
        mRepository.deleteAllCargo();
    }

    public void deleteuser_cargoWithId(Integer id){
        mRepository.deleteuser_CargoWithId(id);
    }

    public LiveData<List<Order>> findOrdersWithuserId(Integer userId){
        return mRepository.findOrdersWithuserId(userId);
    }

}
