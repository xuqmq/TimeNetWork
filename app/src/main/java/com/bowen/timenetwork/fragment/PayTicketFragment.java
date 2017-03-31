package com.bowen.timenetwork.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;


import com.bowen.timenetwork.BaseFragment;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.adapter.GuideAdapter;
import com.bowen.timenetwork.adapter.PaytictelViewOneAdapter;
import com.bowen.timenetwork.adapter.PaytictelViewTwoAdapter;
import com.bowen.timenetwork.bean.PayticketOneInfo;
import com.bowen.timenetwork.bean.PayticketTwoInfo;
import com.bowen.timenetwork.tools.GsonTool;
import com.bowen.timenetwork.tools.Url;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PayTicketFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PayTicketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayTicketFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private  TabLayout tableLayout;
    private ViewPager viewPager;
    private LayoutInflater mInflater;
    private RadioGroup group;
    private LinearLayout lldisplay;
    private RecyclerView recyclerViewOne;
    private RecyclerView recyclerViewTwo;
    private RecyclerView recyclerViewTwoTo;
    private Button btnCityName;


    private OnFragmentInteractionListener mListener;
    private View viewOne;
    private View viewTwo;


    public PayTicketFragment() {
        // Required empty public constructor
    }



    public static PayTicketFragment newInstance(String param1, String param2) {
        PayTicketFragment fragment = new PayTicketFragment();
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
        View view = inflater.inflate(R.layout.fragment_pay_ticket, container, false);
        initView(view);//初始化ui
        group.check(R.id.rbnt_pay_ticket_film);
        initLisener();//监听事件
        initData();//初始化数据
        return view;
    }

    private void initView(View view) {
        tableLayout = (TabLayout) view.findViewById(R.id.tab_layout_pay);
        viewPager = (ViewPager) view.findViewById(R.id.vp_pay_ticket);
        group = (RadioGroup) view.findViewById(R.id.rg_pay_ticket);
        lldisplay = (LinearLayout) view.findViewById(R.id.ll_pay_ticket_display);
        btnCityName = (Button) view.findViewById(R.id.btn_pay_ticket_city_name);
        mInflater = LayoutInflater.from(getContext());

        viewOne = mInflater.inflate(R.layout.title_layout_one,null);
        recyclerViewOne = (RecyclerView) viewOne.findViewById(R.id.rlv_view_one);
        viewTwo = mInflater.inflate(R.layout.title_layout_two,null);
        recyclerViewTwo = (RecyclerView) viewTwo.findViewById(R.id.rlv_view_two);
        recyclerViewTwoTo = (RecyclerView) viewTwo.findViewById(R.id.rlv_view_two_to);


        tableLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        btnCityName.setText(mParam2);//
    }

    private void initLisener() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbnt_pay_ticket_film:
                        lldisplay.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rbtn_pay_ticket_cinema:
                        break;
                }
            }
        });
    }

    private void initData() {
        List<String> titles = new ArrayList<>();
        titles.add("正在热映");
        titles.add("即将上映");
        List<View> titleView = new ArrayList<>();
        titleView.add(viewOne);
        titleView.add(viewTwo);

        GuideAdapter guideAdapter = new GuideAdapter(titleView,titles);
        viewPager.setAdapter(guideAdapter);
        tableLayout.setupWithViewPager(viewPager);
        guideAdapter.notifyDataSetChanged();

        //网络请求数据
        initHttp();


    }

    private void initHttp() {
        String urlOne = Url.LOCATION_MOVIE_ID + mParam1;
        RequestParams params = new RequestParams(urlOne);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                PayticketOneInfo payticketInfo = GsonTool.parseJson2Object(result,PayticketOneInfo.class);
                initViewPagerOneData(payticketInfo);
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
        String urlTwo = Url.MOVIE_COMING_NEW + mParam1;
        RequestParams param = new RequestParams(urlTwo);
        x.http().get(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                PayticketTwoInfo payticketTwoInfo = GsonTool.parseJson2Object(result,PayticketTwoInfo.class);
                initViewPagerTwoData(payticketTwoInfo);
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

    //第二个viewTwo数据
    private void initViewPagerTwoData(PayticketTwoInfo payticketTwoInfo) {
        recyclerViewTwo.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        PaytictelViewTwoAdapter paytictelViewTwoAdapter = new PaytictelViewTwoAdapter(getContext(),payticketTwoInfo.getAttention());
        recyclerViewTwo.setAdapter(paytictelViewTwoAdapter);
        paytictelViewTwoAdapter.notifyDataSetChanged();

        recyclerViewTwoTo.setLayoutManager(new LinearLayoutManager(getContext()));
        PaytictelViewTwoAdapter paytictelViewTwoAdapterTo = new PaytictelViewTwoAdapter(getContext(),payticketTwoInfo.getAttention());
        recyclerViewTwoTo.setAdapter(paytictelViewTwoAdapterTo);
        paytictelViewTwoAdapter.notifyDataSetChanged();
    }

    //第一个viewOne数据
    private void initViewPagerOneData(PayticketOneInfo payticketInfo) {
        recyclerViewOne.setLayoutManager(new LinearLayoutManager(getContext()));
        PaytictelViewOneAdapter paytictelViewOneAdapter =
                new PaytictelViewOneAdapter(getContext(),payticketInfo.getMs(),payticketInfo.getDate());
        recyclerViewOne.setAdapter(paytictelViewOneAdapter);
        paytictelViewOneAdapter.notifyDataSetChanged();
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
