package com.example.cbg_cargo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;


@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM `user` WHERE id=:userid")
    ListenableFuture<User> findUserWithId(Integer userid);
}
