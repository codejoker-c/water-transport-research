package com.example.helloworld.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

// 存放与船主对应的订单，订单的状态有正在进行，已经完成
@Entity(tableName = "order", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"),indices = @Index(value={"userId"}))
public class Order {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    @ColumnInfo(name="userId")
    private Integer userId;

    @NonNull
    private String dep;

    @NonNull
    private String des;

    private String weight;

    private String type;

    @NonNull
    private OrderStatus orderStatus;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(@NonNull Integer userId) {
        this.userId = userId;
    }

    public void setDep(@NonNull String dep) {
        this.dep = dep;
    }

    public void setDes(@NonNull String des) {
        this.des = des;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOrderStatus(@NonNull OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getId() {
        return id;
    }

    @NonNull
    public Integer getUserId() {
        return userId;
    }

    @NonNull
    public String getDep() {
        return dep;
    }

    @NonNull
    public String getDes() {
        return des;
    }

    public String getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    @NonNull
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
