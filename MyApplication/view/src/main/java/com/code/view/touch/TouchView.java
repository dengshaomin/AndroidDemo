package com.code.view.touch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by dengshaomin on 2018/4/20.
 */

public class TouchView extends View {

    int mlastX, mlastY;

    Context mContext;

    public TouchView(Context context) {
        this(context, null);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    int lastX, lastY;

//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.e("code", this.getClass().getName() + " " + ev.getAction() + " " + "dispatchTouchEvent");
//        int x = (int) ev.getX();
//        int y = (int) ev.getY();
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                return super.dispatchTouchEvent(ev);
//            case MotionEvent.ACTION_MOVE:
//                if (Math.abs(x - lastX) <= Math.abs(y - lastY)) {
//                    return super.dispatchTouchEvent(ev);
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                return super.dispatchTouchEvent(ev);
//        }
//        lastX = x;
//        lastY = y;
//        lx = (int) ev.getRawX();
//        ly = (int) ev.getRawY();
//        return false;
//    }

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
                if (Math.abs(x - lx) < Math.abs(y - ly)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                ViewHelper.setTranslationY(this, ViewHelper.getTranslationY(this) + y - ly);

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
