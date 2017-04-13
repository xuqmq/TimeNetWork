package com.bowen.timenetwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bowen.timenetwork.R;
import com.bowen.timenetwork.bean.NoticeInfo;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<NoticeInfo.VideoListBean> mList;
    public VideoAdapter(Context mContext,List<NoticeInfo.VideoListBean> mList){
        this.mList = mList;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder myHolder;
        View view = mInflater.inflate(R.layout.video_item,parent,false);
        myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        x.image().bind(holder.iv,mList.get(position).getImage());
        holder.tvTitle.setText(mList.get(position).getTitle()+"");
        holder.tvTime.setText("片长："+Integer.valueOf(mList.get(position).getLength()) / 60+"分"+
                                 Integer.valueOf(mList.get(position).getLength()) / 60+"秒");
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MyHolder  extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tvTitle;
        private TextView tvTime;
        public MyHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_video_item);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_video_item_title);
            tvTime = (TextView) itemView.findViewById(R.id.tv_video_item_time);
        }
    }
}
