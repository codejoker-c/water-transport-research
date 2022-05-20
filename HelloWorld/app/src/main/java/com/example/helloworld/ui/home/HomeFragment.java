package com.example.helloworld.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.AboutActivity2;
import com.example.helloworld.AboutAdapter;
import com.example.helloworld.BoatMessageInput;
import com.example.helloworld.BoatQuery;
import com.example.helloworld.InfoDesc;
import com.example.helloworld.InfoList;
import com.example.helloworld.InfoListAdapter;
import com.example.helloworld.R;
import com.example.helloworld.database.user_Cargo;
import com.example.helloworld.databinding.FragmentHomeBinding;
import com.example.helloworld.viewmodel.WT_ViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // 数据绑定类，编译器在编译过程自动生成。可以通过binding.(...)来获取xml中的空间对象
    private FragmentHomeBinding binding;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    RecyclerView cargo_list;
    DividerItemDecoration mDivider;
    private WT_ViewModel mWT_ViewModel;
    //ListView内部数据源
    List<user_Cargo> CargoData;
    private InfoListAdapter infoListAdapter;

    Button want;
    //ViewPager slide;
//    List<View> viewList;    //ViewPager的数据源
//    //<ImageView>pointList;  //存放显示器小点点的集
//    int[] picIds = { R.mipmap.slide_3,R.mipmap.slide_5,R.mipmap.slide_2, R.mipmap.slide_4};
//    private AboutAdapter adapter;
//
//    @SuppressLint("HandlerLeak")
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            if (msg.what == 1) {
//                //接收到消息，页面向后一页
//                int currentItem = slide.getCurrentItem();
//                slide.setCurrentItem(currentItem + 1);
//                handler.sendEmptyMessageDelayed(1, 3000);
//            }
//        }
//    };


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 向布局中填充(inflate)另一个布局
        // 第一个参数为想要添加的布局，第二个参数为容器，即要添加到哪个布局，第三个参数为是否直接添加到第二个参数布局上面
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        //slide=root.findViewById(R.id.home_slide);
        want=root.findViewById(R.id.want);
        cargo_list=root.findViewById(R.id.cargo_info_home);
        // 获取控件对象,binding中成员的命名与控件的id有关
        //final TextView textView = binding.textHome;

        want.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BoatQuery.class);
                startActivity(intent);
            }
        });



//        slide.setOutlineProvider(new ViewOutlineProvider() {
//            @Override
//            public void getOutline(View view, Outline outline) {
//                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
//            }
//        });

//        viewList = new ArrayList<>();
////初始化ViewPager页面数据
//        for (int i = 0; i < picIds.length; i++) {
//            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_aboutvp, null);
//            ImageView iv = view.findViewById(R.id.item_aboutvp_iv);
//            iv.setImageResource(picIds[i]);
//            viewList.add(view);
//        }
//
//        //创建适配器对象
//        adapter = new AboutAdapter(viewList);
//        //设置适配器
//        slide.setAdapter(adapter);
//        // 发送切换页面消息
//        handler.sendEmptyMessageDelayed(1, 2200);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        cargo_list.setLayoutManager(linearLayoutManager);
        //初始化分隔线、添加分隔线
        mDivider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        cargo_list.addItemDecoration(mDivider);

        //创建适配器
        infoListAdapter = new InfoListAdapter(getContext(),CargoData);
        //设置适配器
        cargo_list.setAdapter(infoListAdapter);

        //拿到船和货的数据
        mWT_ViewModel = new ViewModelProvider(this).get(WT_ViewModel.class);
        //添加观测，每当表单中信息发生改变时，就重新设置UI显示信息
        mWT_ViewModel.getAlluserCargo().observe(this, new Observer<List<user_Cargo>>() {
            @Override
            public void onChanged(List<user_Cargo> user_cargos) {

//                List<user_Cargo> temp = null;
//                for(int k=0;k<3;k++)
//                    temp.add(user_cargos.get(k*3+1));
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






        return root;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}