package com.bowen.timenetwork.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.adapter.PositionAdapter;
import com.bowen.timenetwork.adapter.PositionGridItemAdapter;
import com.bowen.timenetwork.bean.CityInfo;
import com.bowen.timenetwork.tools.GsonTool;
import com.bowen.timenetwork.tools.Url;
import com.bowen.timenetwork.view.MyGridView;

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
    private LinearLayout linearLayout;
    private EditText editText;
    private MyGridView myGridView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positioning);
        x.view().inject(this);
        initHeaderView();
        listView.addHeaderView(view);
        initHttp();//网络请求

        //监听editText焦点变化
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                   btn.setVisibility(View.GONE);
                }else {
                    btn.setVisibility(View.VISIBLE);
                    btn.setText("取消");
                }
            }
        });
        //监听button按钮
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    private void initHeaderView() {
        LayoutInflater layoutInflater = getLayoutInflater();
        view = layoutInflater.inflate(R.layout.layout_headerview, null);
         linearLayout = (LinearLayout) view.findViewById(R.id.ll_header);
        editText = (EditText) view.findViewById(R.id.et_header);
        myGridView = (MyGridView) view.findViewById(R.id.gv_header);
        btn = (Button) view.findViewById(R.id.btn_header);
        }


    private void initDatas(CityInfo cityInfo) {
        Map<String,String> map = new HashMap<>();//初始化数据
        List<String> mList = new ArrayList<>();
        for (int i = 0 ; i < cityInfo.getP().size() ; i++){
            map.put(cityInfo.getP().get(i).getId()+"",cityInfo.getP().get(i).getN());
            if (i < 12){
                mList.add(cityInfo.getP().get(i).getN());
            }
        }
        PositionAdapter positionAdapter = new PositionAdapter(map,this,this);
        listView.setAdapter(positionAdapter);
        positionAdapter.notifyDataSetInvalidated();

        //头部适配器
        PositionGridItemAdapter itemAdapter = new PositionGridItemAdapter(mList,this);
        myGridView.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetInvalidated();


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
