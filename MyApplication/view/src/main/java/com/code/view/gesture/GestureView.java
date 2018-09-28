package com.code.view.gesture;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

/**
 * Created by dengshaomin on 2018/6/25.
 */

public class GestureView extends LinearLayout implements OnTouchListener {

    View rootView;

    private GestureDetector mGestureDetector;

    public GestureView(Context context) {
        this(context, null);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.parseColor("#ffee00"));
        mGestureDetector = new GestureDetector(new SimpleGestureListener());
        setOnTouchListener(this);
        setFocusable(true);
        setClickable(true);
        setLongClickable(true);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener {

        public boolean onSingleTapUp(MotionEvent e) {
            L.e("onSingleTapUp");
            return false;
        }

        public void onLongPress(MotionEvent e) {
            L.e("onLongPress");
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                float distanceX, float distanceY) {
            L.e("onScroll");
            return false;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                float velocityY) {
            L.e("onFling");
            return false;
        }

        public void onShowPress(MotionEvent e) {
            L.e("onShowPress");
        }

        public boolean onDown(MotionEvent e) {
            L.e("onDown");
            return false;
        }

        public boolean onDoubleTap(MotionEvent e) {
            L.e("onDoubleTap");
            return false;
        }

        public boolean onDoubleTapEvent(MotionEvent e) {
            L.e("onDoubleTapEvent");
            return false;
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            L.e("onSingleTapConfirmed");
            return false;
        }

        public boolean onContextClick(MotionEvent e) {
            L.e("onContextClick");
            return false;
        }
    }

    static class L {

        static final String Tag = "code";

        public static void e(String msg) {
            if (TextUtils.isEmpty(msg)) {
                return;
            }
            Log.e(Tag, msg);
        }
    }
}
