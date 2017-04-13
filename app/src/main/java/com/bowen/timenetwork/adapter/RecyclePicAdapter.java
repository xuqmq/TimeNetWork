package com.bowen.timenetwork.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bowen.timenetwork.R;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */
public class RecyclePicAdapter extends RecyclerView.Adapter<RecyclePicAdapter.MyViewHolder>{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mList;
    public RecyclePicAdapter(Context mContext, List<String> mList){
        this.mContext = mContext;
        this.mList = mList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.pic_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        x.image().bind(holder.iv,mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_piv_item);
        }
    }
}
