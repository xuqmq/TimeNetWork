package com.bowen.timenetwork.activity;

import android.os.Bundle;
import android.util.Log;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.bean.CityInfo;
import com.bowen.timenetwork.tools.Url;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class PositioningActivity extends MainActivity {

    private List<CityInfo.PBean> citys;
    private List<String> cityListName = new ArrayList<>();//城市名
    private List<Integer> cityIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positioning);
        initHttp();//网络请求
        initDatas();//初始化数据
    }

    private void initDatas() {
        for (int i = 0 ; i < citys.size() ; i++){
            cityListName.add(citys.get(i).getN());
            cityIds.add(citys.get(i).getId());
        }
    }

    private void initHttp() {
        RequestParams params = new RequestParams(Url.CITI_URL);
        x.http().get(params, new Callback.CommonCallback<CityInfo>() {
            @Override
            public void onSuccess(CityInfo result) {
                 citys= result.getP();
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
