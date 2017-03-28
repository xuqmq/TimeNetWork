package com.bowen.timenetwork.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bowen.timenetwork.R;
import com.bowen.timenetwork.activity.ContentActivity;
import com.bowen.timenetwork.bean.CityData;
import com.bowen.timenetwork.tools.CharacterParser;
import com.bowen.timenetwork.tools.GetCityData;
import com.bowen.timenetwork.view.MyGridView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class PositionAdapter extends BaseAdapter {

    private  String[] b = {"A", "B", "C", "D", "E", "F", "G", "H",
            "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T",
            "W", "X", "Y", "Z"};
    private  List<String> titles=new ArrayList<>();
    private Map<String,String> mMap;//城市ID和名字
    private LayoutInflater mInflater;
    private Context  mContext;
    private List<CityData> citylist;//列表集合
    private Activity activity;
    private TextView tvCityName;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;

    public PositionAdapter(Map<String,String> mList, Context mContext, Activity activity,TextView tvCityName) {
        this.activity =activity;
        this.mMap = mList;
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.tvCityName = tvCityName;
        for (String title : b) {
            titles.add(title);
        }
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
    }
    @Override
    public int getCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles == null ? null : titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder  ;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.position_adapter_item,null);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.titileTv.setText(titles.get(position));
        getCity();
        //分类过的城市集合
        final List<String> list = new ArrayList<>();
        //分类城市
        for (CityData citys : citylist) {
            if (citys.getFristA().equals(titles.get(position))) {
                list.add(citys.getName());
            }
        }
        PositionGridItemAdapter positionGridItemAdapter = new PositionGridItemAdapter(list,mContext);
        viewHolder.myGridView.setAdapter(positionGridItemAdapter);
        positionGridItemAdapter.notifyDataSetInvalidated();


        viewHolder.myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvCityName.setText(list.get(position));
                Iterator<Map.Entry<String, String>> it = mMap.entrySet().iterator();
                while (it.hasNext()){
                    Map.Entry<String,String> entry =  it.next();
                    //根据value值来找对应的id、
                    if (list.get(position).equals(entry.getValue())){
                        Intent intent = new Intent(mContext, ContentActivity.class);
                        intent.putExtra("cityId",entry.getKey());
                        mContext.startActivity(intent);
                        activity.finish();
                    }
                }
            }
        });
        return convertView;
    }

    public void getCity() {
        List<String> cityLists = new ArrayList<>();//获取城市列表
        //遍历map
        Iterator<Map.Entry<String, String>> it = mMap.entrySet().iterator();
        while (it.hasNext()){
           Map.Entry<String,String> entry =  it.next();
            cityLists.add(entry.getValue());
        }
        // 获取城市的信息
        GetCityData cityList = new GetCityData(characterParser);
        // 获得城市的数据列表
        citylist = cityList.filledData(cityLists);
    }

    public final  class ViewHolder{
        private TextView titileTv;
        private MyGridView myGridView;
        public ViewHolder(View view){
            titileTv = (TextView) view.findViewById(R.id.tv_item_position);
            myGridView = (MyGridView) view.findViewById(R.id.gv_item_position);
            view.setTag(this);
        }
    }

}
