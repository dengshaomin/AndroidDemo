package com.gxz.stickynavlayout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.OverScroller;

import com.gxz.stickynavlayout.R;


/**
 * @author 顾修忠-guxiuzhong@youku.com/gfj19900401@163.com
 * @Title: MainActivity
 * @Package com.gxz.stickynavlayout.activity
 * @Description: MainActivity
 * @date 15/12/29
 * @time 下午12:17
 */
public class MainActivity extends AppCompatActivity {

    OverScroller overScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overScroller = new OverScroller(this);
    }

    public void NestStick(View view) {
        startActivity(new Intent(this, NestActivity.class));
    }

    public void SimpleStick(View view) {
        startActivity(new Intent(this, SimpleStickActivity.class));
    }

    public void StickPullRefresh(View view) {
        startActivity(new Intent(this, PullToRefreshStickActivity.class));
    }

    public void TopViewOverOne(View view) {
        startActivity(new Intent(this, TopViewOverOneScreenActivity.class));
    }

    public void SwipeRefresh(View view) {
        startActivity(new Intent(this, SwipeRefreshLayoutActivity.class));
    }

    public void onClickTopOperate(View view) {
        startActivity(new Intent(this, TopOperateActivity.class));
    }

}
