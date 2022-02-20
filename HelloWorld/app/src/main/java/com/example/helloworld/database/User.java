package com.example.helloworld.database;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    public User(String username,String password){
        this.username = username;
        this.password = password;
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
}
