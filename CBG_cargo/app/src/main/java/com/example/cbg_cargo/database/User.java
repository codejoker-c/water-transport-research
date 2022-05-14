package com.example.cbg_cargo.database;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "user",indices = {@Index(value={"phone"},unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    private String phone;

    @NonNull
    private String password;

    public User(){};


    public String getPhone(){
        return phone;
    }

    public String getPassword(){
        return password;
    }
}
