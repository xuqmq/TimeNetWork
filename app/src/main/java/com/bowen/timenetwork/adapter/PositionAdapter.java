package com.bowen.timenetwork.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bowen.timenetwork.R;
import com.bowen.timenetwork.bean.CityData;
import com.bowen.timenetwork.tools.CharacterParser;
import com.bowen.timenetwork.tools.GetCityData;
import com.bowen.timenetwork.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class PositionAdapter extends BaseAdapter {

    private  String[] b = {"A", "B", "C", "D", "E", "F", "G", "H",
            "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T",
            "W", "X", "Y", "Z"};
    private  List<String> titles=new ArrayList<>();
    private List<String> mList;
    private LayoutInflater mInflater;
    private Context  mContext;
    private List<CityData> citylist;//列表集合

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;

    public PositionAdapter(List<String> mList, Context mContext) {
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
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
        List<String> list = new ArrayList<>();
        //分类城市
        for (CityData citys : citylist) {
            if (citys.getFristA().equals(titles.get(position))) {
                list.add(citys.getName());
            }
        }
        PositionGridItemAdapter positionGridItemAdapter = new PositionGridItemAdapter(list,mContext);
        viewHolder.myGridView.setAdapter(positionGridItemAdapter);
        positionGridItemAdapter.notifyDataSetInvalidated();
        return convertView;
    }

    public void getCity() {
        // 获取城市的信息
        GetCityData cityList = new GetCityData(characterParser);
        // 获得城市的数据列表
        citylist = cityList.filledData(mList);
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
