package com.bowen.timenetwork.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.adapter.DetailActorRecycleAdapter;
import com.bowen.timenetwork.bean.DetailInfo;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        x.view().inject(this);
        rlv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        String movieId = getIntent().getStringExtra("movieId");
        initHttp(movieId);
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
        x.image().bind(ivDetail,detailInfo.getData().getBasic().getImg());
        DetailActorRecycleAdapter detailActorRecycleAdapter = new DetailActorRecycleAdapter(this,detailInfo.getData().getBasic().getActors());
        rlv.setAdapter(detailActorRecycleAdapter);
        detailActorRecycleAdapter.notifyDataSetChanged();


    }
}
