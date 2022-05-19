package com.example.helloworld.ui.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.helloworld.AboutActivity2;
import com.example.helloworld.HomeMenuActivity;
import com.example.helloworld.InfoList;
import com.example.helloworld.R;
import com.example.helloworld.database.User;
import com.example.helloworld.databinding.FragmentUserBinding;
import com.example.helloworld.user.UserContext;

import org.w3c.dom.Text;

public class UserFragment extends Fragment {

    Button logout;
    private FragmentUserBinding binding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageView img;
    public UserFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_new_user);
//        Toolbar myToolbar = findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);
//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);
        //String name;
        //TextView name_set;
        //name_set=findViewById(R.id.get_user_name);
        //name=UserContext.getUser().getUsername();
        //name_set.setText(name);
//        logout = findViewById(R.id.logout_btn);
//
//        logout.setOnClickListener(view -> {
//            Intent intent = new Intent(UserActivity.this,MainActivity.class);
//            UserContext.setLogoutState();
//            startActivity(intent);
//        });
    }

    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        TextView abt=root.findViewById(R.id.about_us_text);

        abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity2.class);
                startActivity(intent);
            }
        });

        //img=root.findViewById(R.id.about_us);
        /*
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity2.class);
                startActivity(intent);
            }
        });
        */


        return root;
    }

    //不能用Onclick方法！！！！！！！！！
//    public void onClick(View view) {
//
//        //switch (view.getId()) {
//          //  case R.id.about_us:
//                Intent intent = new Intent(getActivity(), AboutActivity2.class);
//                startActivity(intent);
//          //      break;
//        //}
//
//    }


}