package com.example.helloworld;

import static io.grpc.okhttp.internal.Platform.logger;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.database.User;
import com.example.helloworld.database.UserStatus;
import com.example.helloworld.user.UserContext;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.concurrent.ExecutionException;

public class CompleteMessage extends AppCompatActivity {

    TextView error;
    EditText name_ship, weight_ship, loadweight_ship;
    Spinner depart_ship;
    Spinner load_type;
    WT_ViewModel mWT_ViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_message);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        name_ship = findViewById(R.id.name_ship_complete);
        weight_ship = findViewById(R.id.weight_ship_complete);
        loadweight_ship = findViewById(R.id.loadweight_ship_complete);
        depart_ship = findViewById(R.id.depart_ship_complete);
        error = findViewById(R.id.boat_info_error_complete);
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);

        //刚进入页面时的初始化

        try {
            User userBoat = mWT_ViewModel.findUserWithPhone(UserContext.getUser().getPhone()).get();
            if (userBoat.getName() != null) {
                name_ship.setText(userBoat.getName());
                weight_ship.setText(userBoat.getWeight() + "");
                loadweight_ship.setText(userBoat.getLoad_weight() + "");
                String[] str = getResources().getStringArray(R.array.spinner_array);
                int position = 0;
                for (String i : str) {
                    if (i.equals(userBoat.getLoad_weight()))
                        break;
                    position++;
                }
                String[] str1 = getResources().getStringArray(R.array.port_list);
                int position1 = 0;
                for (String i : str1) {
                    if (i.equals(userBoat.getDepart()))
                        break;
                    position1++;
                }
                depart_ship.setSelection(position1, true);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    //把String转化为int
    public static int convertToInt(String number, int defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.submit_ship_complete:
                String name, phone, depart;
                int weight, loadweight;
                name = name_ship.getText().toString().trim();
                weight = convertToInt(weight_ship.getText().toString(), 0);
                loadweight = convertToInt(loadweight_ship.getText().toString(), 0);
                depart = depart_ship.getSelectedItem().toString();

                if (name.isEmpty() || weight == 0 || loadweight == 0 || depart.isEmpty()) {
                    error.setText("信息填写不完整！");
                    error.setVisibility(view.VISIBLE);
                } else {
                    User user = UserContext.getUser();
                    //User userBoat = new User(user.getPhone(),user.getPassword());
                    user.setName(name);
                    user.setDepart(depart);
                    user.setLoad_weight(loadweight);
                    user.setWeight(weight);
                    user.setIsFillInfo(1); // 设置该用户已经完善信息

                    logger.info("这是提示信息");
                    logger.info(name);
                    logger.info(depart);
                    logger.info(user.getName());
                    logger.info(user.getDepart());
                    logger.info(String.valueOf(user.getId()));

                    //user.userStatus = UserStatus.boat;
                    //设置登录状态------------------------------------------------------------
                    //UserContext.setLoginState(user);
                    //------------------------------------------------------------
                    mWT_ViewModel.update(user);

                    intent.setClass(CompleteMessage.this, FootbarActivity.class);
                    startActivity(intent);
                }
                break;

        }
    }

}
