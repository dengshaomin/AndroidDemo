package com.gxz.stickynavlayout;

import android.content.Context;

/**
 * Created by dengshaomin on 2017/7/7.
 */

public class Tools {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
