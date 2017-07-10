package com.gxz.stickynavlayout.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gxz.PagerSlidingTabStrip;
import com.gxz.stickynavlayout.R;
import com.gxz.stickynavlayout.StickyNavLayoutNest;
import com.gxz.stickynavlayout.adapter.FragmentsViewPagerAdapter;
import com.gxz.stickynavlayout.fragments.BaseFragment;
import com.gxz.stickynavlayout.fragments.RecycleViewFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 顾修忠-guxiuzhong@youku.com/gfj19900401@163.com
 * @Title: BaseFragment
 * @Package com.gxz.stickynavlayout.fragments
 * @Description: 基类Fragment
 * @date 16/1/1
 * @time 下午13:17
 */
public class NestActivity extends AppCompatActivity {

    @Bind(R.id.id_stickynavlayout_indicator)
    PagerSlidingTabStrip pagerSlidingTabStrip;
    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager viewPager;
    @Bind(R.id.id_stick)
    StickyNavLayoutNest stickyNavLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest);
        ButterKnife.bind(this);

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(RecycleViewFragment.newInstance());
        fragments.add(RecycleViewFragment.newInstance());
        fragments.add(RecycleViewFragment.newInstance());
        fragments.add(RecycleViewFragment.newInstance());
        fragments.add(RecycleViewFragment.newInstance());

        FragmentsViewPagerAdapter adapter = new FragmentsViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
        pagerSlidingTabStrip.setOnPageChangeListener(mPageChangeListener);

    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };

    private boolean lastIsTopHidden;//记录上次是否悬浮

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
