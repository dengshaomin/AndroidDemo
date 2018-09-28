package com.code.myapplication;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by dengshaomin on 2018/4/13.
 */

public class L {

    public static void e(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Log.e("code", msg);
    }

}
