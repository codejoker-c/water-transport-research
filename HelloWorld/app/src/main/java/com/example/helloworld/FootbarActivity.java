package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;


import com.example.helloworld.database.Order;
import com.example.helloworld.database.OrderStatus;
import com.example.helloworld.databinding.ActivityFootbarBinding;
import com.example.helloworld.viewmodel.WT_ViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class FootbarActivity extends AppCompatActivity {

    private ActivityFootbarBinding binding;
    WT_ViewModel mWT_ViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);

        Order order = new Order(1,"123","123", OrderStatus.doing);
        mWT_ViewModel.insert(order);


        //setContentView(R.layout.activity_footbar);
        // 获取布局填充部分
        binding = ActivityFootbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // 将导航中四个fragment的id传入，底部导航栏的配置
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_order, R.id.navigation_notification, R.id.navigation_user).build();

        // 获取导航控制器
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}