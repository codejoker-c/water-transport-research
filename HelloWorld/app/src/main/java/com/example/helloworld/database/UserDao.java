package com.example.helloworld.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("DELETE FROM User")
    void deleteAll();

    @Query("SELECT * FROM User")
    ListenableFuture<List<User>> getAllUser();

    @Query("SELECT * FROM User WHERE phone = :phone")
    ListenableFuture<User> findUserWithPhone(String phone);

    @Update
    void update(User user);
}
