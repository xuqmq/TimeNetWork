package com.bowen.timenetwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.adapter.VideoAdapter;
import com.bowen.timenetwork.bean.NoticeInfo;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class VideoActivity extends MainActivity {

    @ViewInject(R.id.recycle_video)
    RecyclerView rlvVideo;
    @ViewInject(R.id.tv_title_name)
    TextView tv;
    @ViewInject(R.id.iv_title_back)
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        x.view().inject(this);
        Intent intent = getIntent();
        NoticeInfo noticeInfo = (NoticeInfo) intent.getSerializableExtra("noticeInfo");
        tv.setText("预告片&拍摄...");
        rlvVideo.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        VideoAdapter adapter = new VideoAdapter(this,noticeInfo.getVideoList());
        rlvVideo.setAdapter(adapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
