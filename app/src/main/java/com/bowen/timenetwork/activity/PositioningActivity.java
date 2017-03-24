package com.bowen.timenetwork.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.adapter.PositionAdapter;
import com.bowen.timenetwork.bean.CityInfo;
import com.bowen.timenetwork.tools.Url;
import com.google.gson.Gson;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class PositioningActivity extends MainActivity {



    @ViewInject(R.id.lv_position)
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positioning);
        x.view().inject(this);
        initHttp();//网络请求

    }



    private void initDatas(CityInfo cityInfo) {
     List<String> cityListName = new ArrayList<>();//城市名
     List<Integer> cityIds = new ArrayList<>();
        for (int i = 0 ; i < cityInfo.getP().size() ; i++){
            cityListName.add(cityInfo.getP().get(i).getN());
            cityIds.add(cityInfo.getP().get(i).getId());
        }

        PositionAdapter positionAdapter = new PositionAdapter(cityListName,this);
        listView.setAdapter(positionAdapter);
        positionAdapter.notifyDataSetInvalidated();
    }

    private void initHttp() {

        RequestParams params = new RequestParams(Url.CITI_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                CityInfo cityInfo = gson.fromJson(result,CityInfo.class);
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
