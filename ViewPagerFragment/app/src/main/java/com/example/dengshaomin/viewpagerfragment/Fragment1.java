package com.example.dengshaomin.viewpagerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dengshaomin on 2017/9/20.
 */

public class Fragment1 extends LazyFragment {
    @Override
    protected void lazyLoad() {
        CLog.e("Fragment1 lazyLoad");
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_1;
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        CLog.e("Fragment1 onVisible");
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
        CLog.e("Fragment1 onInvisible");
    }
}
