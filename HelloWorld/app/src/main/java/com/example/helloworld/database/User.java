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
    protected String username;

    @NonNull
    protected String password;

    @NonNull
    protected int isFillInfo;

    //将枚举类型忽略，不存入数据库中
    @Ignore
    public Status status=Status.logout;

    //ignore的字段不能放入构造函数中
    public User(String username,String password){
        this.username = username;
        this.password = password;
        isFillInfo = 0;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setIsFillInfo(){
        isFillInfo = 1;
    }

    public int getIsFillInfo(){
        return isFillInfo;
    }

}
