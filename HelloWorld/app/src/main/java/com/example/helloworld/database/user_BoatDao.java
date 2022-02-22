package com.example.helloworld.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

@Dao()
public interface user_BoatDao {
    @Insert
    void insert(user_Boat userBoat);

    @Query("SELECT * FROM user_Boat WHERE username = :username")
    ListenableFuture<user_Boat> finduserBoatWithUsername(String username);

    @Query("DELETE FROM user_Boat WHERE username = :username")
    void deleteuserBoatWithUsername(String username);

}
