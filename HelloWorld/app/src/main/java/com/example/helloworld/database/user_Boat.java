package com.example.helloworld.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.io.Serializable;

@Entity(tableName = "user_Boat")
public class user_Boat extends User implements Serializable {
    //后续这些数据都有可能更改，故使用public更方便
    public String name;

    public String phone;

    public int weight;  //船重

    public int load_weight;//载重

    public String load_type;

    public String depart;

    public user_Boat(String username, String password, String name, String phone, int weight, int load_weight, String load_type, String depart) {
        super(username, password);
        this.name = name;
        this.phone = phone;
        this.weight = weight;
        this.load_type = load_type;
        this.load_weight = load_weight;
        this.depart = depart;
    }

    @Ignore
    public user_Boat(String username, String password) {
        super(username, password);
    }



    /*
    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public String getLoad_type(){
        return load_type;
    }

    public String getDepart(){
        return depart;
    }

    public int getWeight(){
        return weight;
    }

    public int getLoad_weight(){
        return load_weight;
    }
    */
}
