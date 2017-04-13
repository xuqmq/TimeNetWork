package com.bowen.timenetwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowen.timenetwork.R;
import com.bowen.timenetwork.bean.DetailInfo;
import org.xutils.x;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public  class DetailActorRecycleAdapter extends RecyclerView.Adapter<DetailActorRecycleAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<DetailInfo.DataBean.BasicBean.ActorsBean> mList;
    public DetailActorRecycleAdapter(Context mContext,List<DetailInfo.DataBean.BasicBean.ActorsBean> mList){
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mList = mList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.actor_item,parent,false);
            return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            x.image().bind(holder.iv,mList.get(position).getImg());
           holder.tvName.setText(mList.get(position).getName()+"");
           holder.tvNameEn.setText(mList.get(position).getNameEn()+"");

    }




    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tvName;
        private TextView tvNameEn;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_actor);
            tvName = (TextView) itemView.findViewById(R.id.tv_name_actor);
            tvNameEn = (TextView) itemView.findViewById(R.id.tv_name_en_actor);
        }
    }

}
