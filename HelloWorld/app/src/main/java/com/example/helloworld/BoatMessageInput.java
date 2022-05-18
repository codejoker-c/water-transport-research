package com.example.helloworld;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.helloworld.viewmodel.WT_ViewModel;

public class BoatMessageInput extends AppCompatActivity {

    TextView spin_info,error;
    EditText name_ship,phone_ship,weight_ship,loadweight_ship;
    Spinner depart_ship;
    Spinner load_type;
    WT_ViewModel mWT_ViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boat_message_input);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        spin_info = findViewById(R.id.spin_info_ship);
        name_ship = findViewById(R.id.name_ship);
        phone_ship = findViewById(R.id.phone_ship);
        weight_ship = findViewById(R.id.weight_ship);
        loadweight_ship = findViewById(R.id.loadweight_ship);
        depart_ship = findViewById(R.id.depart_ship);
        load_type = findViewById(R.id.spinner_01);
        error = findViewById(R.id.boat_info_error);
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
    }

        /*
        load_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin_info=(TextView) view;
                spin_info.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(BoatMessageInput.this, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });

         */
        //刚进入页面时的初始化
        /*
        try {
            user_Boat userBoat = mWT_ViewModel.finduserBoatWithUsername(UserContext.getUser().getUsername()).get();
            if(userBoat.name!=null){
                name_ship.setText(userBoat.name);
                phone_ship.setText(userBoat.phone);
                weight_ship.setText(userBoat.weight+"");
                loadweight_ship.setText(userBoat.load_weight+"");
                String[] str = getResources().getStringArray(R.array.spinner_array);
                int position=0;
                for(String i:str){
                    if(i.equals(userBoat.load_type))
                        break;
                    position++;
                }
                load_type.setSelection(position,true);
                String[] str1 = getResources().getStringArray(R.array.port_list);
                int position1=0;
                for(String i:str1){
                    if(i.equals(userBoat.depart))
                        break;
                    position1++;
                }
                depart_ship.setSelection(position1,true);
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
        Intent intent=new Intent();

        switch (view.getId()) {
            case R.id.submit_ship:
                String name,phone,depart,loadtype;
                int weight,loadweight;
                name = name_ship.getText().toString().trim();
                phone = phone_ship.getText().toString().trim();
                weight = convertToInt(weight_ship.getText().toString(),0);
                loadweight = convertToInt(loadweight_ship.getText().toString(),0);
                //depart = depart_ship.getText().toString().trim();
                depart = depart_ship.getSelectedItem().toString();
                loadtype = load_type.getSelectedItem().toString();


                if(name.isEmpty()||phone.isEmpty()||weight==0||loadweight==0||depart.isEmpty()||loadtype.isEmpty()){
                    error.setText("信息填写不完整！");
                    error.setVisibility(view.VISIBLE);
                }
                else{
                    User user = UserContext.getUser();
                    user_Boat userBoat = new user_Boat(user.getUsername(),user.getPassword(),name,phone,weight,loadweight,loadtype,depart);
                    userBoat.setId(user.getId());
                    userBoat.setIsFillInfo(); // 设置该用户已经完善信息
                    userBoat.userStatus = UserStatus.boat;
                    UserContext.setLoginState(userBoat);
                    mWT_ViewModel.update(userBoat);

                    intent.setClass(BoatMessageInput.this,HomeMenuActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ib_menu:
                UserContext.user_center(intent, BoatMessageInput.this);
                break;
        }
    }
*/
}