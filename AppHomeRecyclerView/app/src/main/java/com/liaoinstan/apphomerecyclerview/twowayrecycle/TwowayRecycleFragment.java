package com.liaoinstan.apphomerecyclerview.twowayrecycle;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.liaoinstan.apphomerecyclerview.R;

import org.lucasr.twowayview.widget.StaggeredGridLayoutManager;
import org.lucasr.twowayview.widget.TwoWayView;

public class TwowayRecycleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View rootView = null;//缓存Fragment view
    private TwowayRecycleAdapter adapter;
    private SwipeRefreshLayout lay_fresh;

    public static TwowayRecycleFragment newInstance() {
        TwowayRecycleFragment f = new TwowayRecycleFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_twowayrecycle, container, false);
        initBase();
        return rootView;
    }

    private void initBase() {
        lay_fresh = (SwipeRefreshLayout) this.rootView.findViewById(R.id.lay_refresh);
        lay_fresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        lay_fresh.setOnRefreshListener(this);

        TwoWayView twoWayView = (TwoWayView) this.rootView.findViewById(R.id.twowayView);
        twoWayView.setHasFixedSize(true);
        twoWayView.setLongClickable(true);
        twoWayView.setAdapter(adapter = new TwowayRecycleAdapter(getActivity()));
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lay_fresh.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }
}
