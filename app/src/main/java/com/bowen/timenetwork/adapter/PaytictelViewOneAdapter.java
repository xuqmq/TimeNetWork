package com.bowen.timenetwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowen.timenetwork.R;
import com.bowen.timenetwork.bean.PayticketOneInfo;

import org.xutils.x;

import java.util.List;


/**
 * Created by Administrator on 2017/3/30 0030.
 */
public class PaytictelViewOneAdapter extends RecyclerView.Adapter<PaytictelViewOneAdapter.MyViewOneHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<PayticketOneInfo.MsBean> mList;
    private String date;
    public PaytictelViewOneAdapter(Context mContext, List<PayticketOneInfo.MsBean> mList, String date){
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
        if (Integer.valueOf(month) < Integer.valueOf(mMonth)  && Integer.valueOf(day) < Integer.valueOf(mDay)
                && mList.get(position).getR() > 0){
            holder.tvRa.setVisibility(View.VISIBLE);
            holder.tvRa.setText(mList.get(position).getR()+"");
            holder.btnOne.setText("购票");
            holder.btnOne.setBackgroundResource(R.drawable.bt_solid_orange_66);
        }else if(Integer.valueOf(month) == Integer.valueOf(mMonth)  && Integer.valueOf(day) == Integer.valueOf(mDay)){
            holder.ivGone.setVisibility(View.VISIBLE);
            holder.btnOne.setText("购票");
            holder.btnOne.setBackgroundResource(R.drawable.bt_solid_orange_66);
        }else {
            holder.btnOne.setText("预售");
            holder.btnOne.setBackgroundResource(R.drawable.bt_solid_green_66);
        }
        holder.tvDate.setText(month+"月"+day+"日上映");
        holder.tvAll.setText("今日"+mList.get(position).getNearestCinemaCount()+"家影院上映"+mList.get(position).getNearestShowtimeCount()+"场");
        if (mList.get(position).isIs3D()){
            holder.iv3D.setVisibility(View.VISIBLE);
        }
        if (mList.get(position).isIsIMAX3D()){
            holder.ivIMax.setVisibility(View.VISIBLE);
        }
        for (int i = 0 ; i < mList.get(position).getVersions().size() ; i++) {
        if (mList.get(position).getVersions().get(i).getEnumX() == 6){
            holder.ivLarget.setVisibility(View.VISIBLE);
        }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewOneHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private ImageView ivGone;
        private TextView tvMoveName;
        private TextView tvRa;
        private TextView tvSpecial;
        private TextView tvDate;
        private TextView tvAll;
        private Button btnOne;
        private ImageView iv3D;
        private ImageView ivIMax;
        private ImageView ivLarget;
        public MyViewOneHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_view_one_item);
            ivGone = (ImageView) itemView.findViewById(R.id.iv_view_one_item_gone);
            tvMoveName = (TextView) itemView.findViewById(R.id.tv_one_move_name);
            tvRa = (TextView) itemView.findViewById(R.id.tv_one_ra);
            tvSpecial = (TextView) itemView.findViewById(R.id.tv_one_common_special);
            tvDate = (TextView) itemView.findViewById(R.id.tv_one_rdata);
            tvAll = (TextView) itemView.findViewById(R.id.tv_one_all_ccd);
            btnOne = (Button) itemView.findViewById(R.id.btn_one_pay);
            iv3D = (ImageView) itemView.findViewById(R.id.iv_one_3d);
            ivIMax = (ImageView) itemView.findViewById(R.id.iv_one_imax);
            ivLarget = (ImageView) itemView.findViewById(R.id.iv_one_larget);
        }
    }
}
