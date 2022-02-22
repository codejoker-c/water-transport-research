package com.example.helloworld.database;

import androidx.room.Entity;

@Entity(tableName = "user_Cargo")
public class user_Cargo extends User {
    public String name;

    public String phone;

    public int cargo_weight;

    public String cargo_type;

    public String depart;

    public String destin;

    public user_Cargo(String username,String password,Status status,String name,String phone,int cargo_weight,String cargo_type,String depart,String destin){
        super(username,password,status);
        this.name = name;
        this.phone = phone;
        this.cargo_type = cargo_type;
        this.cargo_weight = cargo_weight;
        this.destin = destin;
        this.depart = depart;
    }


}
