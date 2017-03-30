package com.bowen.timenetwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowen.timenetwork.R;
import com.bowen.timenetwork.bean.PayticketInfo;

import org.xutils.x;

import java.util.List;


/**
 * Created by Administrator on 2017/3/30 0030.
 */
public class PaytictelViewOneAdapter extends RecyclerView.Adapter<PaytictelViewOneAdapter.MyViewOneHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<PayticketInfo.MsBean> mList;
    private String date;
    public PaytictelViewOneAdapter(Context mContext, List<PayticketInfo.MsBean> mList,String date){
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.mList = mList;
        this.date = date;
    }

    @Override
    public MyViewOneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_one_adapter_item,parent,false);
        MyViewOneHolder holder = new MyViewOneHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewOneHolder holder, int position) {
        x.image().bind(holder.iv,mList.get(position).getImg());
        holder.tvMoveName.setText(mList.get(position).getTCn());
        holder.tvSpecial.setText("“"+mList.get(position).getCommonSpecial());

        String dates = mList.get(position).getRd();
        String month = new StringBuffer().append(dates.charAt(4)).append(dates.charAt(5)).toString();
        String day = new StringBuffer().append(dates.charAt(6)).append(dates.charAt(7)).toString();
        String mMonth = new StringBuffer().append(date.charAt(5)).append(date.charAt(6)).toString();
        String mDay = new StringBuffer().append(date.charAt(8)).append(date.charAt(9)).toString();
//        判断时间
        if (Integer.valueOf(month) <= Integer.valueOf(mMonth)  && Integer.valueOf(day) <= Integer.valueOf(mDay)){
            holder.tvRa.setVisibility(View.VISIBLE);
            holder.tvRa.setText(mList.get(position).getR()+"");
        }
        holder.tvDate.setText(month+"月"+day+"日上映");
        holder.tvAll.setText("今日"+mList.get(position).getNearestCinemaCount()+"家影院上映"+mList.get(position).getNearestShowtimeCount()+"场");

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewOneHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tvMoveName;
        private TextView tvRa;
        private TextView tvSpecial;
        private TextView tvDate;
        private TextView tvAll;
        public MyViewOneHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_view_one_item);
            tvMoveName = (TextView) itemView.findViewById(R.id.tv_one_move_name);
            tvRa = (TextView) itemView.findViewById(R.id.tv_one_ra);
            tvSpecial = (TextView) itemView.findViewById(R.id.tv_one_common_special);
            tvDate = (TextView) itemView.findViewById(R.id.tv_one_rdata);
            tvAll = (TextView) itemView.findViewById(R.id.tv_one_all_ccd);
        }
    }
}
