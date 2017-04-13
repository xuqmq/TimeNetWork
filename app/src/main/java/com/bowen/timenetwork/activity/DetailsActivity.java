package com.bowen.timenetwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.adapter.DetailActorRecycleAdapter;
import com.bowen.timenetwork.bean.DetailInfo;
import com.bowen.timenetwork.bean.NoticeInfo;
import com.bowen.timenetwork.bean.PictureInfo;
import com.bowen.timenetwork.tools.GsonTool;
import com.bowen.timenetwork.tools.Url;
import com.bowen.timenetwork.view.ExpandableTextView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class DetailsActivity extends MainActivity {

    @ViewInject(R.id.iv_detail)
    ImageView ivDetail;
    @ViewInject(R.id.tv_detail_movie_name)
    TextView tvMovieName;
    @ViewInject(R.id.tv_detail_name)
    TextView tvName;
    @ViewInject(R.id.tv_detail_name_en)
    TextView tvNameEn;
    @ViewInject(R.id.tv_detail_mins)
    TextView tvMins;
    @ViewInject(R.id.tv_detail_time)
    TextView tvTime;
    @ViewInject(R.id.tv_detail_type)
    TextView tvType;
    @ViewInject(R.id.tv_detail_comment_special)
    TextView tvComment;
    @ViewInject(R.id.btn_detail_rat)
    Button btnRat;
    @ViewInject(R.id.tv_expandable_detail)
    ExpandableTextView tvExpandable;
    @ViewInject(R.id.rlv_detail)
    RecyclerView rlv;
    @ViewInject(R.id.iv_live)
    ImageView ivLive;
    @ViewInject(R.id.iv_detail_video)
    ImageView ivVideo;
    @ViewInject(R.id.iv_detail_pic)
    ImageView ivPic;
    @ViewInject(R.id.tv_detail_live_name)
    TextView tvLiveName;
    @ViewInject(R.id.tv_detail_live_size)
    TextView tvLiveSize;
    @ViewInject(R.id.btn_size)
    Button btnSize;
    @ViewInject(R.id.btn_video)
    Button btnVideo;
    @ViewInject(R.id.btn_pic)
    Button btnPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        x.view().inject(this);
        rlv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        String movieId = getIntent().getStringExtra("movieId");
        initHttp(movieId);

    }

    private void initListener(final NoticeInfo noticeInfo) {
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this,VideoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("noticeInfo",noticeInfo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void initHttp(String movieId) {
        String url = Url.MOVIE_ID_CONTENT + movieId;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                DetailInfo detailInfo = GsonTool.parseJson2Object(result,DetailInfo.class);
                initData(detailInfo);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
        int p = 1;
        String movieUrl = Url.NOTICE+p+"&movieId="+movieId;
        RequestParams param = new RequestParams(movieUrl);
        x.http().get(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                NoticeInfo noticeInfo = GsonTool.parseJson2Object(result,NoticeInfo.class);
                initDatas(noticeInfo);
                initListener(noticeInfo);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        String picUrl = Url.PICTURE+movieId;
        RequestParams paramPic = new RequestParams(picUrl);
        x.http().get(paramPic, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                PictureInfo pictureInfo = GsonTool.parseJson2Object(result,PictureInfo.class);
                initDataPic(pictureInfo);
                initPicListener(pictureInfo);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initPicListener(final PictureInfo pictureInfo) {
        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this,PictureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("pictureInfo",pictureInfo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initDataPic(PictureInfo pictureInfo) {
        //        for (int i = 0 ; i < noticeInfo.getVideoList().size() ; i++){
//            if (noticeInfo.getVideoList().get(i).getType() == 31){
//
//            }
//        }
        x.image().bind(ivPic,pictureInfo.getImages().get(16).getImage());
        btnPic.setText(pictureInfo.getImages().size()+"");
    }

    private void initDatas(NoticeInfo noticeInfo) {
        x.image().bind(ivVideo,noticeInfo.getVideoList().get(0).getImage());
        btnVideo.setText(noticeInfo.getVideoList().size()+"");
    }

    private void initData(DetailInfo detailInfo) {
        tvMovieName.setText(detailInfo.getData().getBasic().getName()+"");
        tvName.setText(detailInfo.getData().getBasic().getName()+"");
        tvNameEn.setText(detailInfo.getData().getBasic().getNameEn()+"");
        tvMins.setText(detailInfo.getData().getBasic().getMins()+"");
        tvComment.setText(detailInfo.getData().getBasic().getCommentSpecial()+"");
        tvType.setText(detailInfo.getData().getBasic().getType()+"");
        tvTime.setText(detailInfo.getData().getBasic().getReleaseDate()+detailInfo.getData().getBasic().getReleaseArea()+"上映");
        btnRat.setText(detailInfo.getData().getBasic().getOverallRating()+"");
        tvExpandable.setText("剧情："+detailInfo.getData().getBasic().getStory()+"");
        tvLiveName.setText(detailInfo.getData().getLive().getTitle()+"");
        tvLiveSize.setText(detailInfo.getData().getLive().getPlayNumTag()+"");
        btnSize.setText(detailInfo.getData().getBasic().getShowtimeCount()+"");
        x.image().bind(ivDetail,detailInfo.getData().getBasic().getImg());
        x.image().bind(ivLive,detailInfo.getData().getLive().getImg());
        DetailActorRecycleAdapter detailActorRecycleAdapter = new DetailActorRecycleAdapter(this,detailInfo.getData().getBasic().getActors());
        rlv.setAdapter(detailActorRecycleAdapter);
        detailActorRecycleAdapter.notifyDataSetChanged();


    }
}
