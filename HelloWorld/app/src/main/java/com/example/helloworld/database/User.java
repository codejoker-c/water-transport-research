package com.example.helloworld.database;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//使用枚举来记录用户的状态
enum Status{
    logout,boat,cargo;
}

@Entity(tableName = "User")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;

    @NonNull
    protected String username;

    @NonNull
    protected String password;

    //将枚举类型忽略，不存入数据库中
    @Ignore
    protected Status status;

    public User(String username,String password,Status status){
        this.username = username;
        this.password = password;
        this.status = status;
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

    public Status getStatus(){
        return status;
    }
}
