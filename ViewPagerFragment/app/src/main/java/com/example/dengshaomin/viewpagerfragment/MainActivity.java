package com.example.dengshaomin.viewpagerfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITabSegment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private QMUITabSegment mTabSegment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        mTabSegment = (QMUITabSegment) findViewById(R.id.tabSegment);
        mTabSegment.reset();
        mTabSegment.setHasIndicator(true);
        mTabSegment.setIndicatorPosition(false);
        mTabSegment.setIndicatorWidthAdjustContent(false);
        mTabSegment.addTab(new QMUITabSegment.Tab("item1"));
        mTabSegment.addTab(new QMUITabSegment.Tab("item2"));
        mTabSegment.setupWithViewPager(viewPager, false);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        mTabSegment.setDefaultSelectedColor(getResources().getColor(R.color.qmui_config_color_red));
        mTabSegment.setDefaultNormalColor(getResources().getColor(R.color.btn_filled_blue_bg_normal));
        mTabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                mTabSegment.hideSignCountView(index);
                CLog.e("onTabSelected--" + index);
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {
                mTabSegment.hideSignCountView(index);
                CLog.e("onTabReselected--" + index);
            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
        mTabSegment.notifyDataChanged();
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new Fragment1();
            } else {
                return new Fragment2();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
