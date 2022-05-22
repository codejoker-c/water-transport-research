package com.example.helloworld.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

// 存放与船主对应的订单，订单的状态有正在进行，已经完成
@Entity(tableName = "order", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"),indices = @Index(value={"userId"}))
public class Order {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    // 订单船主id
    @NonNull
    @ColumnInfo(name="userId")
    private Integer userId;

    // 货物起始地
    @NonNull
    private String dep;

    // 货物目的地
    @NonNull
    private String des;

    // 货物重量
    private String weight;

    // 货物种类
    private String type;

    // 订单状态
    @NonNull
    protected Integer orderStatus;

    // 备注信息
    private String remark;

    public Order(@NonNull Integer userId, @NonNull String dep, @NonNull String des, @NonNull Integer orderStatus){
        this.userId=userId;
        this.dep = dep;
        this.des = des;
        this.orderStatus = orderStatus;
    }

    public void refinePara(String weight,String type){
        this.weight = weight;
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark){
        this.remark=remark;
    }

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

    public void setOrderStatus(@NonNull Integer orderStatus) {
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
    public String getOrderStatus() {
        if(orderStatus.equals(0)) {
            return "正在进行";
        }else if(orderStatus.equals(1)){
            return "已经完成";
        }else if(orderStatus.equals(2)){
            return "已经取消";
        }else{
            return "Null";
        }
    }
}





