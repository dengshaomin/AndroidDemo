package com.code.view.move;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by dengshaomin on 2018/4/19.
 */

public class MoveView extends View {



    int mlastY, mlastX;

    float scale = 1f;

    boolean resert = false;

    public MoveView(Context context) {
        this(context, null);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int deltax = x - mlastX;
                int deltay = y - mlastY;
                int translateX = (int) (ViewHelper.getTranslationX(this) + deltax);
                int translateY = (int) (ViewHelper.getTranslationY(this) + deltay);
                ViewHelper.setTranslationX(this, translateX);
                ViewHelper.setTranslationY(this, translateY);
                if (resert) {
                    scale += 0.01;
                    if (scale >= 1f) {
                        resert = false;
                    }
                } else {
                    scale -= 0.01;
                    if (scale <= 0.5f) {
                        resert = true;
                    }
                }
                ViewHelper.setScaleX(this, scale);
                ViewHelper.setScaleY(this, scale);
                break;
            default:
                break;
        }
        mlastX = x;
        mlastY = y;
        return true;
    }
}
