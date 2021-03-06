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

import java.util.Calendar;
import java.util.List;


/**
 * Created by Administrator on 2017/3/30 0030.
 */
public class PaytictelViewTwoAdapter extends RecyclerView.Adapter<PaytictelViewTwoAdapter.MyViewOneHolder> implements View.OnClickListener{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<PayticketTwoInfo.AttentionBean> mList;
    public PaytictelViewTwoAdapter(Context mContext, List<PayticketTwoInfo.AttentionBean> mList){
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.mList = mList;

    }

    private OnItemClickListener onItemClickListener;

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null){
            onItemClickListener.onClick(v, (Integer) v.getTag());
        }
    }

    public interface OnItemClickListener{
        void onClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public MyViewOneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_two_adapter_item,parent,false);
        MyViewOneHolder holder = new MyViewOneHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewOneHolder holder,  int position) {
        x.image().bind(holder.iv,mList.get(position).getImage());
        holder.tvMonth.setText(mList.get(position).getRMonth()+"月"+mList.get(position).getRDay()+"日");
        holder.tvName.setText(mList.get(position).getTitle());
        holder.tvType.setText(mList.get(position).getWantedCount()+"人想看-"+mList.get(position).getType());
        holder.tvDirector.setText("导演："+mList.get(position).getDirector());
        holder.tvActor.setText("演员："+mList.get(position).getActor1()+","+mList.get(position).getActor2());


        holder.btnTwo.setText("预告片");
        holder.btnTwo.setBackgroundResource(R.drawable.actor_detail_unlike_background_on);
        Calendar c = Calendar.getInstance();
        if (mList.get(position).isIsTicket()){
            holder.btnOne.setText("超前预售");
            holder.btnOne.setBackgroundResource(R.drawable.bt_solid_green_66);
        }else {
            holder.btnOne.setText("上映提醒");
            holder.btnOne.setBackgroundResource(R.drawable.actor_detail_unlike_background_on);
        }
      holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewOneHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tvName;
        private TextView tvType;
        private TextView tvDirector;
        private TextView tvActor;
        private TextView tvMonth;
        private Button btnOne;
        private Button btnTwo;

        public MyViewOneHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_two_item);
            tvName  = (TextView) itemView.findViewById(R.id.tv_two_item_name);
            tvType  = (TextView) itemView.findViewById(R.id.tv_two_item_type);
            tvDirector  = (TextView) itemView.findViewById(R.id.tv_two_item_director);
            tvActor  = (TextView) itemView.findViewById(R.id.tv_two_item_actor);
            tvMonth  = (TextView) itemView.findViewById(R.id.tv_two_item_month);
            btnOne  = (Button) itemView.findViewById(R.id.btn_two_one);
            btnTwo  = (Button) itemView.findViewById(R.id.btn_two_two);
        }
    }
}
