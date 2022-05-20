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

    @Query("SELECT * FROM user_Cargo WHERE phone = :phone")
    ListenableFuture<user_Cargo> finduserCargoWithPhone(String phone);

    @Query("SELECT * FROM user_Cargo WHERE id = :userid")
    ListenableFuture<user_Cargo> finduserCargoWithId(Integer userid);

    @Query("DELETE FROM user_Cargo WHERE phone = :phone")
    void deleteuserCargoWithPhone(String phone);

    @Update
    void updata(user_Cargo userCargo);

    @Query("SELECT * FROM user_Cargo")
    LiveData<List<user_Cargo>> getAlluserCargo();

    @Query("DELETE FROM user_Cargo")
    void deleteAllCargo();

    @Query("DELETE FROM user_Cargo WHERE id=:id")
    void deleteuser_CargoWithId(Integer id);
}
