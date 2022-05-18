package com.example.helloworld.database;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "User")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;

    @NonNull
    private String phone;

    @NonNull
    private String password;

    //后续这些数据都有可能更改，故使用public更方便
    private String name;

    private int weight;  //船重

    private int load_weight;//载重
    // private String load_type;

    private String depart;

    @NonNull
    protected int isFillInfo;

    //将枚举类型忽略，不存入数据库中
    @Ignore
    public UserStatus userStatus = UserStatus.logout;

    //ignore的字段不能放入构造函数中
    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
        isFillInfo = 0;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setLoad_weight(int load_weight) {
        this.load_weight = load_weight;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setIsFillInfo(int isFillInfo) {
        this.isFillInfo = isFillInfo;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getLoad_weight() {
        return load_weight;
    }

    public String getDepart() {
        return depart;
    }

    public int getIsFillInfo() {
        return isFillInfo;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }
}
