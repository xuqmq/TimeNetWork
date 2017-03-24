package com.bowen.timenetwork.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bowen.timenetwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class PositionAdapter extends BaseAdapter {

    private  String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    private  List<String> titles=new ArrayList<>();
    private List<String> mList;
    private LayoutInflater mInflater;
    private Context  mContext;
    public PositionAdapter(List<String> mList, Context mContext){
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        for (String title :b){
            titles.add(title);
        }
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
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.position_adapter_item,null);
            viewHolder.titileTv = (TextView) convertView.findViewById(R.id.tv_item_position);
            viewHolder.recyclerView = (RecyclerView) convertView.findViewById(R.id.rv_item_position);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.titileTv.setText(titles.get(position));
        viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(mContext,4));
        PositionItemAdapter positionItemAdapter = new PositionItemAdapter(mList,mContext);
        viewHolder.recyclerView.setAdapter(positionItemAdapter);
        return convertView;
    }

    public final  class ViewHolder{
        private TextView titileTv;
        private RecyclerView recyclerView;
    }
}
