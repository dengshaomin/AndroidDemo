package com.example.dengshaomin.viewpagerfragment;

/**
 * Created by dengshaomin on 2017/9/20.
 */

public class Fragment2 extends LazyFragment {
    @Override
    protected void lazyLoad() {
        CLog.e("Fragment2 lazyLoad");
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_2;
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        CLog.e("Fragment2 onVisible");
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
        CLog.e("Fragment2 onInvisible");
    }
}
