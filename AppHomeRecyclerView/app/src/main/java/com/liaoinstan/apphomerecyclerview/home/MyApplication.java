package com.liaoinstan.apphomerecyclerview.home;

import android.app.Application;


import com.liaoinstan.apphomerecyclerview.BuildConfig;

import org.xutils.x;

/**
 * Created by Administrator on 2015/11/26.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
