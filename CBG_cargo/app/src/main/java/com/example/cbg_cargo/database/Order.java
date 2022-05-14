package com.example.cbg_cargo.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Order", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"))
public class Order {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    private Integer userId;

    @NonNull
    private String dep;

    @NonNull
    private String des;

    private String weight;

    private String type;

    @NonNull
    private OrderStatus orderStatus;
}
