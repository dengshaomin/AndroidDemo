package com.brothergang.demo.aop;

import android.util.Log;

/**
 * Created by brothergang on 2017/3/27.
 */

public class LogUtils {

    private static final boolean DEBUG = true;

    public static void e(String loginfo) {
        Log.e("code", loginfo);
    }
}
