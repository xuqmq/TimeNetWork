package com.bowen.timenetwork.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.bowen.timenetwork.MainActivity;
import com.bowen.timenetwork.R;

public class StartActivity extends MainActivity {


    private Intent intent;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        intentActivity();
    }

    private void intentActivity() {
        sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE);
        intent = new Intent();
        int isIntent = sharedPreferences.getInt("count",0);
        if (isIntent == 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        intent.setClass(StartActivity.this,GuideActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        intent.setClass(StartActivity.this,PositioningActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("count",++isIntent);
        editor.commit();
    }
}