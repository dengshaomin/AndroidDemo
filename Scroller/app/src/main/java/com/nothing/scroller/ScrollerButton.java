package com.nothing.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.Scroller;

/**
 * Created by dengshaomin on 2017/10/10.
 */

public class ScrollerButton extends Button {
    private Scroller mScroller;

    public ScrollerButton(Context context) {
        this(context, null);
    }

    public ScrollerButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }


    //调用此方法滚动到目标位置
    public void smoothScrollTo(int fx, int fy, int duration) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy, duration);
    }

    //调用此方法设置滚动的相对偏移
    public void smoothScrollBy(int dx, int dy, int duration) {

        //设置mScroller的滚动偏移量
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy, duration);
        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll() {
        //先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {

            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            //必须调用该方法，否则不一定能看到滚动效果
            postInvalidate();
        }
    }
}
