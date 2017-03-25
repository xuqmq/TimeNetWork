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
 * Created by Administrator on 2017/3/25 0025.
 */
public class PositionGridItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public PositionGridItemAdapter(List<String> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_position, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

            viewHolder.tv.setText(mList.get(position));
        return convertView;
    }

    class ViewHolder {
        private TextView tv;
        public ViewHolder(View view) {
            tv = (TextView) view.findViewById(R.id.tv_item_gv_position);
            view.setTag(this);
        }
    }


}