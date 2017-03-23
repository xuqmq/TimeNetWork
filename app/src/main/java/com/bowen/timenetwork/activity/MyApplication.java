package com.bowen.timenetwork.activity;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(MyApplication.this);
    }
}
