package com.example.helloworld.viewmodel;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.helloworld.database.User;
import com.example.helloworld.database.WT_Repository;
import com.google.common.util.concurrent.ListenableFuture;

public class WT_ViewModel extends AndroidViewModel {
    private WT_Repository mRepository;


    public WT_ViewModel(Application application){
        super(application);
        mRepository = new WT_Repository(application);
    }

    //插入数据，参数为User类
    public void insert(User user){
        mRepository.insert(user);
    }

    //通过username字段查询数据，返回User类，因为是异步查询，即开了新线程，故加了ListenableFuture来修饰
    //通过ListenableFuture.get()方法可以获得User类
    public ListenableFuture<User> findUserWithUsername(String un){
        return mRepository.findUserWithUsername(un);
    }
}
