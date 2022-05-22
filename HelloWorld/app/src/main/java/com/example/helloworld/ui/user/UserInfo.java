package com.example.helloworld.ui.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.widget.Toolbar;

import com.example.helloworld.R;
import com.example.helloworld.database.User;
import com.example.helloworld.user.UserContext;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.logging.Logger;

public class UserInfo extends AppCompatActivity {

    EditText loadWeight,weight;
    Spinner depart;
    WT_ViewModel mWT_ViewModel;
    Toolbar mtoolbar;
    private static final Logger logger = Logger.getGlobal();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        User user = UserContext.getUser();
        initView();
        loadWeight.setOnEditorActionListener((textView, i, keyEvent) -> {
            user.setLoad_weight(Integer.parseInt(textView.getText().toString()));
            mWT_ViewModel.update(user);
            return false;
        });

        weight.setOnEditorActionListener((textView, i, keyEvent) ->{
            user.setWeight(Integer.parseInt(textView.getText().toString()));
            mWT_ViewModel.update(user);
            return false;
        } );

        depart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String info = adapterView.getItemAtPosition(i).toString();//获取i所在的文本
                user.setDepart(info);
                mWT_ViewModel.update(user);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        } );
        loadWeight.setText(String.valueOf(user.getLoad_weight()));
        weight.setText(String.valueOf(user.getWeight()));
        setSupportActionBar(mtoolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    void initView(){
        loadWeight = findViewById(R.id.settings_load_w);
        weight = findViewById(R.id.settings_weight);
        depart = findViewById(R.id.settings_depart);
        mtoolbar = findViewById(R.id.toolbar);
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
    }
}