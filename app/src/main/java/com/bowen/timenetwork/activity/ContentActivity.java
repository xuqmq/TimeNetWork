package com.bowen.timenetwork.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;
import com.bowen.timenetwork.fragment.DiscoverFragment;
import com.bowen.timenetwork.fragment.HomeFragment;
import com.bowen.timenetwork.fragment.PayTicketFragment;
import com.bowen.timenetwork.fragment.ShopFragment;
import com.bowen.timenetwork.fragment.UserFragment;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class ContentActivity extends MainActivity {

    @ViewInject(R.id.rg_content)
    RadioGroup radioGroup;
    private HomeFragment homeFragment;
    private PayTicketFragment payTicketFragment;
    private ShopFragment shopFragment;
    private DiscoverFragment discoverFragment;
    private UserFragment userFragment;
    private android.support.v4.app.FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        x.view().inject(this);
        Intent intent = getIntent();
        String cityId = intent.getStringExtra("cityId");
        initListener(cityId);
        radioGroup.check(R.id.rb_home_content);//起始选中首页
    }

    private void initListener(final String cityId) {
        fragmentManager = getSupportFragmentManager();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                hideFragment(fragmentTransaction);
                switch (checkedId){
                    case R.id.rb_home_content:
                        if (homeFragment == null){
                            homeFragment = HomeFragment.newInstance(cityId,null);
                            fragmentTransaction.add(R.id.fl_content,homeFragment);
                        }else {
                            fragmentTransaction.show(homeFragment);
                        }
                        break;
                    case R.id.rb_paytikel_content:
                        if (payTicketFragment == null){
                            payTicketFragment = PayTicketFragment.newInstance(null,null);
                            fragmentTransaction.add(R.id.fl_content,payTicketFragment);
                        }else {
                            fragmentTransaction.show(payTicketFragment);
                        }
                        break;
                    case R.id.rb_shop_content:
                        if (shopFragment == null){
                            shopFragment = ShopFragment.newInstance(null,null);
                            fragmentTransaction.add(R.id.fl_content,shopFragment);
                        }else {
                            fragmentTransaction.show(shopFragment);
                        }
                        break;
                    case R.id.rb_discover_content:
                        if (discoverFragment == null){
                            discoverFragment = DiscoverFragment.newInstance(null,null);
                            fragmentTransaction.add(R.id.fl_content,discoverFragment);
                        }else {
                            fragmentTransaction.show(discoverFragment);
                        }
                        break;
                    case R.id.rb_user_content:
                        if (userFragment == null){
                            userFragment = UserFragment.newInstance(null,null);
                            fragmentTransaction.add(R.id.fl_content,userFragment);
                        }else {
                            fragmentTransaction.show(userFragment);
                        }
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }
    private void hideFragment(FragmentTransaction fragmentTransaction){
        if (homeFragment != null) fragmentTransaction.hide(homeFragment);
        if (payTicketFragment != null) fragmentTransaction.hide(payTicketFragment);
        if (shopFragment != null) fragmentTransaction.hide(shopFragment);
        if (discoverFragment != null) fragmentTransaction.hide(discoverFragment);
        if (userFragment != null) fragmentTransaction.hide(userFragment);
    }
}
