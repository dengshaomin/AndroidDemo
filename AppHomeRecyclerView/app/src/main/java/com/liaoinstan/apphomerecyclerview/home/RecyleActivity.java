package com.liaoinstan.apphomerecyclerview.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.liaoinstan.apphomerecyclerview.R;
import com.liaoinstan.apphomerecyclerview.nestrecycle.RecycleFragment;
import com.liaoinstan.apphomerecyclerview.onerecycle.OneRecycleFragment;
import com.liaoinstan.apphomerecyclerview.twowayrecycle.TwowayRecycleFragment;

import java.util.LinkedList;
import java.util.List;

public class RecyleActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyle);

        initBase();
    }

    private void initBase() {
        //actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("liaoinstan");
        setSupportActionBar(toolbar);

        //viewpager & tabs
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        final MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecycleFragment().newInstance(), "nestrecycle");
        adapter.addFragment(new OneRecycleFragment().newInstance(), "onerecycle");
        adapter.addFragment(new TwowayRecycleFragment().newInstance(), "twowayrecycle");
        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments = new LinkedList<>();
        private List<String> mTitles = new LinkedList<>();

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
