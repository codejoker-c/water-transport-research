package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.ArrayList;
import java.util.List;

public class InfoList extends AppCompatActivity {

    RecyclerView cargo_list;
    private WT_ViewModel mWT_ViewModel;
    //ListView内部数据源
    List<user_Cargo> CargoData;
    private InfoListAdaptor infoListAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);

        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
        cargo_list=findViewById(R.id.cargo_info);
        CargoData=new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cargo_list.setLayoutManager(linearLayoutManager);


        //找到数据源
        //CargoData=mWT_ViewModel.getAlluserCargo().getValue();
        //创建适配器
        infoListAdaptor = new InfoListAdaptor(this,CargoData);
        //设置适配器
        cargo_list.setAdapter(infoListAdaptor);


    }
}