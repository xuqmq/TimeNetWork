package com.bowen.timenetwork.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.adapter.PositionAdapter;
import com.bowen.timenetwork.bean.CityInfo;
import com.bowen.timenetwork.tools.GsonTool;
import com.bowen.timenetwork.tools.Url;
import com.google.gson.Gson;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PositioningActivity extends MainActivity {



    @ViewInject(R.id.lv_position)
    ListView listView;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positioning);
        x.view().inject(this);
        initHttp();//网络请求
        initHeaderView();
        listView.addHeaderView(view);
    }

    private void initHeaderView() {
        LayoutInflater layoutInflater = getLayoutInflater();
        view = layoutInflater.inflate(R.layout.layout_headerview, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_header);
         RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rlv_header);
    }


    private void initDatas(CityInfo cityInfo) {
        Map<String,String> map = new HashMap<>();
        for (int i = 0 ; i < cityInfo.getP().size() ; i++){
            map.put(cityInfo.getP().get(i).getId()+"",cityInfo.getP().get(i).getN());
        }

        PositionAdapter positionAdapter = new PositionAdapter(map,this,this);
        listView.setAdapter(positionAdapter);
        positionAdapter.notifyDataSetInvalidated();
    }

    private void initHttp() {

        RequestParams params = new RequestParams(Url.CITI_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                CityInfo cityInfo = GsonTool.parseJson2Object(result,CityInfo.class);
                initDatas(cityInfo);//初始化数据
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
}
