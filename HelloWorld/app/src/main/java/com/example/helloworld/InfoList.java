package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.ArrayList;
import java.util.List;

public class InfoList extends AppCompatActivity {

    RecyclerView cargo_list;
    private WT_ViewModel mWT_ViewModel;
    //ListView内部数据源
    List<user_Cargo> CargoData;
    private InfoListAdapter infoListAdapter;
    DividerItemDecoration mDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
        cargo_list=findViewById(R.id.cargo_info);
        //CargoData=new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cargo_list.setLayoutManager(linearLayoutManager);
        //初始化分隔线、添加分隔线
        mDivider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        cargo_list.addItemDecoration(mDivider);

        //创建适配器
        infoListAdapter = new InfoListAdapter(this,CargoData);
        //设置适配器
        cargo_list.setAdapter(infoListAdapter);

        //添加观测，每当表单中信息发生改变时，就重新设置UI显示信息
        mWT_ViewModel.getAlluserCargo().observe(this, new Observer<List<user_Cargo>>() {
            @Override
            public void onChanged(List<user_Cargo> user_cargos) {
                infoListAdapter.setCargoData(user_cargos);
                infoListAdapter.notifyDataSetChanged();
                CargoData = user_cargos;
            }
        });
    }
}

