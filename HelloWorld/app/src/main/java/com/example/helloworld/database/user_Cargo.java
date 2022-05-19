package com.example.helloworld.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user_Cargo")
public class user_Cargo implements Serializable {    //你这个是可以序列化的
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;

    @NonNull
    private String phone;

    @NonNull
    private String password;

    public String name;

    public int cargo_weight;

    public String cargo_type;

    public String depart;

    public String destin;

    public int month;

    public int day;

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public String getPhone() {
        return phone;
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

    public void setCargo_weight(int cargo_weight) {
        this.cargo_weight = cargo_weight;
    }

    public void setCargo_type(String cargo_type) {
        this.cargo_type = cargo_type;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setDestin(String destin) {
        this.destin = destin;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getCargo_weight() {
        return cargo_weight;
    }

    public String getCargo_type() {
        return cargo_type;
    }

    public String getDepart() {
        return depart;
    }

    public String getDestin() {
        return destin;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public user_Cargo(String phone, String password, String name, int cargo_weight, String cargo_type, String depart, String destin, int month, int day) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.cargo_type = cargo_type;
        this.cargo_weight = cargo_weight;
        this.destin = destin;
        this.depart = depart;
        this.month = month;
        this.day = day;
    }
/*
    @Ignore
    public user_Cargo(String username, String password) {
        super(username, password);
    }
*/

}
