package com.bowen.timenetwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.adapter.GuideAdapter;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends MainActivity {

    @ViewInject(R.id.vp_guide)
    ViewPager viewPager;
    @ViewInject(R.id.ll_guide)
    LinearLayout linearLayout;
    private List<View> mList = new ArrayList<>();
    private ImageView [] imageViewDots;
    private LayoutInflater inflater;
    private int isSelected;//选中状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guideactivity);
        x.view().inject(this);
        inflater = LayoutInflater.from(this);

        //初始化数据
        initData();
        GuideAdapter guideAdapter = new GuideAdapter(mList);
        viewPager.setAdapter(guideAdapter);
        guideAdapter.notifyDataSetChanged();
        View view = inflater.inflate(R.layout.lead_four,null);
//        //初始化底部原点
//        initDots();
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                setDots(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


    }

            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this,PositioningActivity.class);
                startActivity(intent);
            }
//    private void initDots() {
//        imageViewDots = new ImageView[mList.size()];
//
//        for (int i = 0 ; i < imageViewDots.length ; i++){
//            imageViewDots[i] = (ImageView) linearLayout.getChildAt(i);
//            imageViewDots[i].setSelected(false); // 都没选中，全部设置为灰色
//        }
//            isSelected = 0 ;
//            imageViewDots[isSelected].setSelected(true);
//    }
//    private void setDots(int position){
//        if (position < 0 || position > mList.size() ){
//            return;
//        }else {
//            imageViewDots[position].setSelected(true);
//            imageViewDots[isSelected].setSelected(false);
//        }
//        isSelected = position;
//    }

    private void initData() {
        mList.add(inflater.inflate(R.layout.lead_one,null));
        mList.add(inflater.inflate(R.layout.lead_two,null));
        mList.add(inflater.inflate(R.layout.lead_three,null));
        mList.add(inflater.inflate(R.layout.lead_four,null));
    }
}
