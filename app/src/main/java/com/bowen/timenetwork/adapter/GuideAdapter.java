package com.bowen.timenetwork.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class GuideAdapter extends PagerAdapter {
    private List<View> list;
    public  GuideAdapter(List<View> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return list == null ?  0 : list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //添加子布局
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    //移除子布局

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }
}
