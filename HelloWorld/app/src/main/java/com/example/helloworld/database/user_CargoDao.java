package com.example.helloworld.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface user_CargoDao {
    @Insert
    void insert(user_Cargo userCargo);

    @Query("SELECT * FROM user_Cargo WHERE username = :username")
    ListenableFuture<user_Cargo> finduserCargoWithUsername(String username);

    @Query("DELETE FROM user_Cargo WHERE username = :username")
    void deleteuserCargoWithUsername(String username);

    @Update
    void updata(user_Cargo userCargo);

    @Query("SELECT * FROM user_Cargo")
    LiveData<List<user_Cargo>> getAlluserCargo();
}
