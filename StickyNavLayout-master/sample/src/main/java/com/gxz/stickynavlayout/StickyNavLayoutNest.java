package com.gxz.stickynavlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by dengshaomin on 2017/7/7.
 */

public class StickyNavLayoutNest extends LinearLayout implements NestedScrollingParent {
    private int mTopViewHeight;
    private View viewPager;
    private View mNav;
    private View mTop;
    private OverScroller mScroller;

    public StickyNavLayoutNest(Context context) {
        this(context, null);
    }

    public StickyNavLayoutNest(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickyNavLayoutNest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new OverScroller(context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fling(100, mTopViewHeight);
            }
        }, 3000);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mNav = findViewById(R.id.id_stickynavlayout_indicator);
        viewPager = findViewById(R.id.id_stickynavlayout_viewpager);
        mTop = findViewById(R.id.id_stickynavlayout_topview);
        RecyclerView nestedChild = (RecyclerView) findViewById(R.id.id_stickynavlayout_innerscrollview);
        if (!(viewPager instanceof ViewPager)) {
            throw new RuntimeException(" id_stickynavlayout_viewpager show used by ViewPager !");
        }

        if ((nestedChild instanceof NestedScrollingChild)) {
            throw new RuntimeException(" id_stickynavlayout_innerscrollview is not implements NestedScrollingChild!");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mNav.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    WindowManager wm = (WindowManager) getContext()
                            .getSystemService(Context.WINDOW_SERVICE);
                    int height = wm.getDefaultDisplay().getHeight();
                    ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
                    layoutParams.height = height - mNav.getMeasuredHeight();
                    mNav.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
            mTop.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    mTopViewHeight = mTop.getMeasuredHeight();
                    mTop.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        } else {

        }
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        boolean hiddenTop = dy > 0 && getScrollY() < mTopViewHeight;
        boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (velocityY > 0 && getScrollY() < mTopViewHeight) // 向上滑动, 且当前View还没滑到最顶部
        {
            fling((int) velocityY, mTopViewHeight);
            return true;
        } else if (velocityY < 0 && getScrollY() > 0) // 向下滑动, 且当前View部分在屏幕外
        {
            fling((int) velocityY, 0);
            return true;
        }
        return false;
    }


    public void fling(int velocityY, int maxY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, maxY);
        invalidate();
    }

    @Override
    public void computeScroll() {
        // 先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {
            // 这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 必须调用该方法，否则不一定能看到滚动效果
            invalidate();
        }
//        super.computeScroll(); //调用super... fling 失效
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mTopViewHeight) {
            y = mTopViewHeight;
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }
}
