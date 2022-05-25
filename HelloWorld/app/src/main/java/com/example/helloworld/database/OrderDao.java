package com.example.helloworld.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM `order`")
    LiveData<List<Order>> getAllOrder();

    @Update
    void update(Order order);

}
