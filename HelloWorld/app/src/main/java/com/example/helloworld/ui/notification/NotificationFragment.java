package com.example.helloworld.ui.notification;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.helloworld.InfoDesc;
import com.example.helloworld.InfoList;
import com.example.helloworld.InfoListAdapter;
import com.example.helloworld.R;
import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.databinding.FragmentNotificationBinding;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    RecyclerView cargo_list;
    private WT_ViewModel mWT_ViewModel;
    //ListView内部数据源
    List<user_Cargo> CargoData;
    private InfoListAdapter infoListAdapter;
    DividerItemDecoration mDivider;


    private FragmentNotificationBinding binding;

    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(inflater,container,false);
        View root = binding.getRoot();




        TextView text_underline=root.findViewById(R.id.huoyuan_under_line);
        text_underline.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        text_underline.getPaint().setAntiAlias(true);//抗锯齿


        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
        cargo_list=root.findViewById(R.id.cargo_info);
        //CargoData=new ArrayList<>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        cargo_list.setLayoutManager(linearLayoutManager);
        //初始化分隔线、添加分隔线
        mDivider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        cargo_list.addItemDecoration(mDivider);

        //创建适配器
        infoListAdapter = new InfoListAdapter(getActivity(),CargoData);
        //设置适配器
        cargo_list.setAdapter(infoListAdapter);

        //添加观测，每当表单中信息发生改变时，就重新设置UI显示信息
        mWT_ViewModel.getAlluserCargo().observe(this, new Observer<List<user_Cargo>>() {
            @Override
            public void onChanged(List<user_Cargo> user_cargos) {
                infoListAdapter.setCargoData(user_cargos);
                infoListAdapter.notifyDataSetChanged();
            }
        });

        infoListAdapter.setOnItemClickListener(new InfoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, user_Cargo data) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), InfoDesc.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }

        });
        TextView textView = binding.textNotification;

        return root;
    }
}