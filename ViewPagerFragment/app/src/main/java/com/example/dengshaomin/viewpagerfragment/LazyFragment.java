package com.example.dengshaomin.viewpagerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by dengshaomin on 2017/9/20.
 */

public abstract class LazyFragment extends Fragment {
    protected boolean isVisible;
    private boolean isPrepared;
    private View rootView;
    private boolean hasLoad;

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
            if (isVisible && isPrepared && !hasLoad) {
                lazyLoad();
                hasLoad = true;
            }
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isVisible = isPrepared = hasLoad = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setContentView() <= 0) {
            Toast.makeText(getActivity(), "setContentView First!", Toast.LENGTH_SHORT).show();
            isPrepared = false;
            return null;
        }
        isPrepared = true;
        if (isVisible && isPrepared && !hasLoad) {
            lazyLoad();
            hasLoad = true;
        }
        return inflater.inflate(setContentView(), container, false);
    }

    protected void onVisible() {
//        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {
    }

    public abstract int setContentView();


}
