package com.bowen.timenetwork.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bowen.timenetwork.R;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27 0027.
 */
public class PositionToAdapter extends BaseAdapter {
    private List<String> mList;
    private Context mContext;
    private LayoutInflater mInflater;
    public PositionToAdapter(List<String> mList, Context mContext){
            this.mList = mList;
            this.mContext = mContext;
            this.mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.position_to_adapter_item,parent,false);
            viewHolder = new ViewHolder(convertView);
        }else {
           viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(mList.get(position));
        return convertView;
    }

    class  ViewHolder{
        private TextView tv;
        public ViewHolder(View itemView) {
            itemView.setTag(this);
            tv = (TextView) itemView.findViewById(R.id.tv_to_position_item);
        }
    }
}
