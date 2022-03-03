package com.example.helloworld.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao()
public interface user_BoatDao {
    @Insert
    void insert(user_Boat userBoat);

    @Query("SELECT * FROM user_Boat WHERE username = :username")
    ListenableFuture<user_Boat> finduserBoatWithUsername(String username);

    @Query("SELECT * FROM user_Boat WHERE id = :userid")
    ListenableFuture<user_Boat> finduserBoatWithId(Integer userid);

    @Query("DELETE FROM user_Boat WHERE username = :username")
    void deleteuserBoatWithUsername(String username);

    @Update
    void update(user_Boat userBoat);

    @Query("SELECT * FROM USER_BOAT")
    ListenableFuture<List<user_Boat>> getAlluserBoat();

}
