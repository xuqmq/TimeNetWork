package com.bowen.timenetwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowen.timenetwork.R;
import com.bowen.timenetwork.bean.HomeInfo;

import org.xutils.x;

import java.util.List;


/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class HomeFragmentRecycleAdapter  extends RecyclerView.Adapter<HomeFragmentRecycleAdapter.MyViewHolder> implements View.OnClickListener{

    private Context mContext;
    private List<HomeInfo.MoviesBean> mList ;
    private LayoutInflater mInflater;

    public HomeFragmentRecycleAdapter(Context mContext,List<HomeInfo.MoviesBean> mList ){
        this.mContext = mContext;
        this.mList = mList ;
        mInflater = LayoutInflater.from(mContext);
    }
    private OnItemClickListener onItemClickListener = null;

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            onItemClickListener.onClick(view, (Integer) view.getTag());
        }
    }
    public interface OnItemClickListener{
        void onClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycle_item_fragment,parent,false);

        view.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {
        x.image().bind(holder.iv,mList.get(position).getImg());
        holder.tvName.setText(mList.get(position).getTitleCn());
        if (mList.get(position).isIsIMAX3D()){
            holder.tv3D.setVisibility(View.VISIBLE);
        }
        Time t = new Time();
        t.setToNow();
        if (mList.get(position).getRYear() <= t.year && mList.get(position).getRMonth() <= t.month +1
                && mList.get(position).getRDay() <= t.monthDay && mList.get(position).getRatingFinal() > 0){
            holder.tvRating.setVisibility(View.VISIBLE);
            holder.tvRating.setText(mList.get(position).getRatingFinal()+"");
            holder.btnPay.setBackgroundResource(R.drawable.bt_solid_orange_66);
            holder.btnPay.setText("购票");
        }else {
            holder.tvRating.setVisibility(View.GONE);
            holder.btnPay.setBackgroundResource(R.drawable.bt_solid_green_66);
            holder.btnPay.setText("预售");
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv3D;
        private TextView tvRating;
        private TextView tvName;
        private Button btnPay;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_fragment_recycle_adapter);
            tv3D = (TextView) itemView.findViewById(R.id.tv_fragment_recycle_adapter_3d);
            tvName = (TextView) itemView.findViewById(R.id.tv_fragment_recycle_adapter);
            tvRating = (TextView) itemView.findViewById(R.id.tv_fragment_recycle_adapter_ratingFinal);
            btnPay = (Button) itemView.findViewById(R.id.btn_fragment_recycle_adapter);
        }
    }
}