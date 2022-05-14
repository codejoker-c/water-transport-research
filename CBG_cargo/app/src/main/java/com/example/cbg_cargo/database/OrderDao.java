package com.example.cbg_cargo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    void insert(Order order);

    @Query("SELECT * FROM `order` WhERE userId=:userid")
    LiveData<List<Order>> findOrdersWithuserId(Integer userid);

    @Query("DELETE FROM `order` WHERE userId=:userid")
    void deleteOrdersWithuserId(Integer userid);



}
