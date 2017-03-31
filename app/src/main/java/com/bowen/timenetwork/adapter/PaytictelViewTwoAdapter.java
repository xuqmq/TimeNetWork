package com.bowen.timenetwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowen.timenetwork.R;
import com.bowen.timenetwork.bean.PayticketOneInfo;
import com.bowen.timenetwork.bean.PayticketTwoInfo;

import org.xutils.x;

import java.util.List;


/**
 * Created by Administrator on 2017/3/30 0030.
 */
public class PaytictelViewTwoAdapter extends RecyclerView.Adapter<PaytictelViewTwoAdapter.MyViewOneHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<PayticketTwoInfo.AttentionBean> mList;
    public PaytictelViewTwoAdapter(Context mContext, List<PayticketTwoInfo.AttentionBean> mList){
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.mList = mList;

    }

    @Override
    public MyViewOneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_two_adapter_item,parent,false);
        MyViewOneHolder holder = new MyViewOneHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewOneHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewOneHolder extends RecyclerView.ViewHolder{

        public MyViewOneHolder(View itemView) {
            super(itemView);

        }
    }
}
