package com.bowen.timenetwork.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.adapter.VpPicAdapter;
import com.bowen.timenetwork.bean.PictureInfo;
import com.bowen.timenetwork.fragment.PictureFragment;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */
public class PictureActivity extends MainActivity {
    @ViewInject(R.id.tab_layout_pic)
    TabLayout tab;
    @ViewInject(R.id.vp_pic)
    ViewPager vpPic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        x.view().inject(this);

        Bundle bundle = getIntent().getExtras();
        PictureInfo pictureInfo = (PictureInfo) bundle.getSerializable("pictureInfo");

        tab.setTabGravity(TabLayout.GRAVITY_FILL);
        initAdapter(pictureInfo);
    }

    private void initAdapter(PictureInfo pictureInfo) {
        List<String> title = new ArrayList<>();
        List<Fragment> mListView = new ArrayList<>();
        ArrayList<String> mListAll = new ArrayList<>();
        for (int i = 0 ; i < pictureInfo.getImageTypes().size() ; i++){
            title.add(pictureInfo.getImageTypes().get(i).getTypeName()+"");
        }
        for (int j = 0; j < pictureInfo.getImages().size(); j++) {
            mListAll.add(pictureInfo.getImages().get(j).getImage());
        }
        Bundle bundle1 = new Bundle();
        bundle1.putStringArrayList("mList",mListAll);
        PictureFragment pict = PictureFragment.newInstance(null,null);
        pict.setArguments(bundle1);
        mListView.add(pict);
        for (int i = 1 ; i < pictureInfo.getImageTypes().size() ; i++) {
            ArrayList<String> mList = new ArrayList<>();
            for (int j = 0; j < pictureInfo.getImages().size(); j++) {
            if (pictureInfo.getImageTypes().get(i).getType() == pictureInfo.getImages().get(j).getType()){
                mList.add(pictureInfo.getImages().get(j).getImage());
            }
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("mList",mList);
            PictureFragment pic = PictureFragment.newInstance(null,null);
            pic.setArguments(bundle);
            mListView.add(pic);
        }
        VpPicAdapter vpPicAdapter = new VpPicAdapter(getSupportFragmentManager(),title,mListView);
        tab.setupWithViewPager(vpPic);
        vpPic.setAdapter(vpPicAdapter);
        vpPicAdapter.notifyDataSetChanged();

    }
}
