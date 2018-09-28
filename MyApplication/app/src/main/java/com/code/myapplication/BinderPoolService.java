package com.code.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.code.myapplication.BinderPool.BinderPoolImpl;

/**
 * Created by dengshaomin on 2018/4/17.
 */

public class BinderPoolService extends Service {

    private Binder mBinderPool = new BinderPoolImpl();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinderPool;
    }
}
