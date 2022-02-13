package com.example.helloworld;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class AboutAdapter extends PagerAdapter {

    List<View>viewList;

    public AboutAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {   //返回的是页面的数目
        return viewList.size();
    }

    //判读这个view和生成的view是不是同一个对象
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = viewList.get(position);
        container.addView(view);  //添加到容器中，容器就是ViewPager
        return view;
    }

    //销毁指定位置的view
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view=viewList.get(position);
        container.removeView(view);

    }
}
