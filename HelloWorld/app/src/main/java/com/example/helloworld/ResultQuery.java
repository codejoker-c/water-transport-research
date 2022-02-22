package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.ArrayList;

public class ResultQuery extends AppCompatActivity {

    ArrayList ship_data;
    private WT_ViewModel mWT_ViewModel;//实例化WT_ViewModel来与数据库进行交互


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_query);




    }
}