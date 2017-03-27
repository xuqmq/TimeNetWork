package com.bowen.timenetwork.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.adapter.PositionAdapter;
import com.bowen.timenetwork.adapter.PositionGridItemAdapter;
import com.bowen.timenetwork.adapter.PositionToAdapter;
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
    private Map<String, String> map;
    private List<String> mList;
    private List<String> list;
    private PositionAdapter positionAdapter;
    private PositionToAdapter toAdapter;
    private ListView lvItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positioning);
        x.view().inject(this);
        initHeaderView();
        listView.addHeaderView(view);
        initHttp();//网络请求
        initLisener();//监听事件
    }

    private void initLisener() {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.clearFocus();
        //监听editText焦点变化
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    btn.setVisibility(View.VISIBLE);
                    btn.setText("取消");
                    linearLayout.setVisibility(View.GONE);
                    lvItem.setVisibility(View.VISIBLE);
                }
            }
        });
        //监听button按钮
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.clearFocus();
                btn.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                lvItem.setVisibility(View.GONE);

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
        lvItem = (ListView) view.findViewById(R.id.lv_header);
        }


    private void initDatas(CityInfo cityInfo) {
         map = new HashMap<>();//初始化id和name数据
         mList = new ArrayList<>();//初始化头部数据
        list = new ArrayList<>();//初始化name数据
        for (int i = 0 ; i < cityInfo.getP().size() ; i++) {
            map.put(cityInfo.getP().get(i).getId() + "", cityInfo.getP().get(i).getN());
            list.add(cityInfo.getP().get(i).getN());
            if (i < 12) {
                mList.add(cityInfo.getP().get(i).getN());
            }
        }
            positionAdapter = new PositionAdapter(map, PositioningActivity.this, PositioningActivity.this);
            listView.setAdapter(positionAdapter);
            positionAdapter.notifyDataSetInvalidated();

            toAdapter = new PositionToAdapter(list, PositioningActivity.this);
            lvItem.setAdapter(toAdapter);
            toAdapter.notifyDataSetInvalidated();

        //头部适配器
        PositionGridItemAdapter itemAdapter = new PositionGridItemAdapter(mList,PositioningActivity.this);
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
