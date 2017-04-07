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
import com.bowen.timenetwork.bean.PayticketTwoInfo;
import org.xutils.x;
import java.util.List;


/**
 * Created by Administrator on 2017/3/30 0030.
 */
public class PaytictelViewTwoToAdapter extends RecyclerView.Adapter<PaytictelViewTwoToAdapter.MyViewOneHolder> implements View.OnClickListener {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<PayticketTwoInfo.MoviecomingsBean> mList;
    private TextView tvMonth;
    public PaytictelViewTwoToAdapter(Context mContext, List<PayticketTwoInfo.MoviecomingsBean> mList,TextView tvMonth){
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.mList = mList;
        this.tvMonth = tvMonth;
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
        View view = mInflater.inflate(R.layout.view_two_adapter_to_item,parent,false);
        view.setOnClickListener(this);
        MyViewOneHolder holder = new MyViewOneHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewOneHolder holder, int position) {
        x.image().bind(holder.iv,mList.get(position).getImage());
        holder.tvDay.setText(mList.get(position).getRDay()+"日");
        holder.tvName.setText(mList.get(position).getTitle());
        holder.tvType.setText(mList.get(position).getWantedCount()+"人想看-"+
            mList.get(position).getType());
        holder.tvDirector.setText("导演："+mList.get(position).getDirector());
        if (mList.get(position).isIsTicket()){
            holder.btnOne.setText("超前预售");
            holder.btnOne.setBackgroundResource(R.drawable.bt_solid_green_66);
        }else {
            holder.btnOne.setText("上映提醒");
            holder.btnOne.setBackgroundResource(R.drawable.actor_detail_unlike_background_on);
        }
            holder.btnTwo.setText("预告片");
            holder.btnTwo.setBackgroundResource(R.drawable.actor_detail_unlike_background_on);
        tvMonth.setText(mList.get(position).getRMonth()+"月");
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewOneHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tvDay;
        private TextView tvName;
        private TextView tvType;
        private TextView tvDirector;
        private Button btnOne;
        private Button btnTwo;

        public MyViewOneHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_two_item_to);
            tvDay = (TextView) itemView.findViewById(R.id.tv_two_to_day);
            tvName = (TextView) itemView.findViewById(R.id.tv_two_item_name_to);
            tvType = (TextView) itemView.findViewById(R.id.tv_two_item_type_to);
            tvDirector = (TextView) itemView.findViewById(R.id.tv_two_item_director_to);
            btnOne  = (Button) itemView.findViewById(R.id.btn_two_one_to);
            btnTwo  = (Button) itemView.findViewById(R.id.btn_two_two_to);
        }
    }
}
