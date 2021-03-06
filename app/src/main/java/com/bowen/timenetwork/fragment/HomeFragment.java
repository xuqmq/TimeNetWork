package com.bowen.timenetwork.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bowen.timenetwork.BaseFragment;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.activity.DetailsActivity;
import com.bowen.timenetwork.adapter.HomeFragmentRecycleAdapter;
import com.bowen.timenetwork.bean.HomeInfo;
import com.bowen.timenetwork.tools.GsonTool;
import com.bowen.timenetwork.tools.Url;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ConvenientBanner  banner;
    private Button btnCityName;
    private Button btnAllQuantity;
    private Button btnAll;
    private Button btnAlls;
    private RecyclerView recyclerView;


    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                           Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        banner = (ConvenientBanner) view.findViewById(R.id.home_fragment_banner);
        btnCityName = (Button) view.findViewById(R.id.btn_content_city_name);
        btnAllQuantity = (Button) view.findViewById(R.id.btn_all_content);
        btnAll = (Button) view.findViewById(R.id.btn_content_all);
        btnAlls = (Button) view.findViewById(R.id.btn_content_alls);
        recyclerView = (RecyclerView) view.findViewById(R.id.rlv_content_fragment);
        btnCityName.setText(mParam2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        //网络请求
        inithttp();
        return view;
    }

    private void inithttp() {
        //正在热映
        String url = Url.CITY_CONTENT + mParam1;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                HomeInfo homeDatas = GsonTool.parseJson2Object(result, HomeInfo.class);
                initDataToView(homeDatas);

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });


    }

    //初始化数据源
    private void initDataToView(HomeInfo homeInfo) {
        List<String> imageViewList = new ArrayList<>();
        for (int i = 0 ; i < 6 ; i ++){
              imageViewList.add(homeInfo.getMovies().get(i).getImg());
        }
        banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        },imageViewList)
                .setPageIndicator(new int []{R.drawable.home_point,R.drawable.home_point_f})//设置导航点
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//设置导航点的位置
                .startTurning(2000);//设置自动滚动
        btnAlls.setText("共"+homeInfo.getTotalCinemaCount()+"部");
        btnAll.setText("共"+homeInfo.getTotalComingMovie()+"部");
        btnAllQuantity.setText("共"+homeInfo.getTotalHotMovie()+"部");
        final List<HomeInfo.MoviesBean> list = homeInfo.getMovies();
        HomeFragmentRecycleAdapter homeFragmentRecycleAdapter = new HomeFragmentRecycleAdapter(getActivity(),list);
        recyclerView.setAdapter(homeFragmentRecycleAdapter);
        homeFragmentRecycleAdapter.notifyDataSetChanged();

        //recycleView条目的监听事件
        homeFragmentRecycleAdapter.setOnItemClickListener(new HomeFragmentRecycleAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("movieId",list.get(position).getMovieId()+"");
                startActivity(intent);
            }
        });
    }
    public class LocalImageHolderView implements Holder<String> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAdjustViewBounds(true);
            return imageView;
        }
        @Override
        public void UpdateUI(Context context, final int position,String url ) {
            x.image().bind(imageView,url);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
//        else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
