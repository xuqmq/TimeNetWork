package com.bowen.timenetwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bowen.timenetwork.R;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class PositionItemAdapter extends RecyclerView.Adapter<PositionItemAdapter.MyViewHolder> {

    private List<String> mList;
    private LayoutInflater mInflater;

    public  PositionItemAdapter(List<String> mList, Context mContext){
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycle_position,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class  MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv ;
        public MyViewHolder(View itemView) {
            super(itemView);
           tv = (TextView) itemView.findViewById(R.id.tv_item_ry_position);
        }
    }
}
