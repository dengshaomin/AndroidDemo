package com.code.view.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by dengshaomin on 2018/4/20.
 */

public class TouchViewGroup extends LinearLayout {

    int mlastX, mlastY;

    Context mContext;

    public TouchViewGroup(Context context) {
        this(context, null);
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("code", this.getClass().getName() + " " + ev.getAction() + " " + "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    int lastX, lastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        Log.e("code", this.getClass().getName() + " " + ev.getAction() + " " + "onInterceptTouchEvent");
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - lastX) > Math.abs(y - lastY)) {
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = x;
        lastY = y;
        lx = (int) ev.getRawX();
        ly = (int) ev.getRawY();
        return intercept;
    }

    int lx, ly;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("code", this.getClass().getName() + " " + ev.getAction() + " " + "onTouchEvent");
        int x = (int) ev.getRawX();
        int y = (int) ev.getRawY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
//                if (Math.abs(x - lastX) > Math.abs(y - lastY)) {
//                    intercept = true;
//                }

                ViewHelper.setTranslationX(this, ViewHelper.getTranslationX(this) + x - lx);

                break;
            case MotionEvent.ACTION_UP:
                lx = 0;
                ly = 0;
                break;
        }
        lx = x;
        ly = y;
        return true;
    }
}
