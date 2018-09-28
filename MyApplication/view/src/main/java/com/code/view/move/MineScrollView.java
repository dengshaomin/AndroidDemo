package com.code.view.move;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by dengshaomin on 2018/4/19.
 */

public class MineScrollView extends LinearLayout {

    Scroller mScroller;

    public MineScrollView(Context context) {
        this(context, null);
    }

    public MineScrollView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MineScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    public void smoothScrollTo(int x, int y) {
        int scrollx = getScrollX();
        int detalX = x - scrollx;
        int dealY = y - getScrollY();
        mScroller.startScroll(scrollx, 0, detalX, dealY, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
