package com.bowen.timenetwork.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class ContentActivity extends MainActivity {

    @ViewInject(R.id.rg_content)
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        x.view().inject(this);
        Intent intent = getIntent();
        String cityId = intent.getStringExtra("cityId");
        Log.d("aaa", "onCreate: "+cityId.toString());

        initListener();
    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home_content:
                        break;
                    case R.id.rb_paytikel_content:
                        break;
                    case R.id.rb_shop_content:
                        break;
                    case R.id.rb_discover_content:
                        break;
                    case R.id.rb_user_content:
                        break;
                }
            }
        });
    }
}
