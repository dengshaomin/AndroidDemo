package com.example.dengshaomin.mydraw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by dengshaomin on 2017/8/8.
 */

public class MyView extends View {

    Rect dst = null;
    Paint paint = null;
    int deg = 0;
    Bitmap bitmap = null;
    int startX = 500;
    int hSpace = 300;
    int space = 20;
    int index = 0;
    int delta = 6;
    int color;
    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        LayoutInflater.from(getContext()).inflate(R.layout.my_view, this);
//        findViewById(R.id.invalidate).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                invalidate();
//            }
//        });
        paint = new Paint();
        if (dst == null) dst = new Rect(0, 0, 200, 200);
        if (paint == null) paint = new Paint();
        if (bitmap == null) bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        color = getResources().getColor(R.color.colorPrimary);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.reset();
//        while (true) {
        canvas.save();
        canvas.translate(400, 400);
        if (deg >= 360) {
            deg = 0;
        }
        deg += 1;
        canvas.rotate(deg, 100, 100);
        canvas.drawBitmap(bitmap, null, dst, paint);
        canvas.restore();
        canvas.save();
        canvas.rotate(-deg, 100, 100);
        canvas.drawBitmap(bitmap, null, dst, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 800);
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3f);
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        float sw = this.getMeasuredWidth();
        if (startX >= sw + (hSpace + space) - (sw % (hSpace + space))) {
            startX = 0;
        } else {
            startX += delta;
        }
        float start = startX;
        // draw latter parse
        while (start < sw) {
//            canvas.drawLine(start, 5, start + hSpace, 5, mPaint);
            canvas.drawRect(start, 5, start + hSpace, 30, paint);
            start += (hSpace + space);
        }

        start = startX - space - hSpace;

        // draw front parse
        while (start >= -hSpace) {
//            canvas.drawLine(start, 5, start + hSpace, 5, mPaint);
            canvas.drawRect(start, 5, start + hSpace, 30, paint);
            start -= (hSpace + space);
        }
        if (index >= 700000) {
            index = 0;
        }
        canvas.restore();
        paint.reset();
        paint.setStrokeWidth(10f);
        paint.setTextSize(50f);
        canvas.drawText("进度条", 0, 1000, paint);
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        canvas.drawRoundRect(10, 1100, 600, 1150, 50, 50, paint);

        paint.reset();
        paint.setStrokeWidth(5f);
        paint.setColor(color);
        canvas.drawRoundRect(10, 1100, 100, 1150, 50, 50, paint);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        }, 10);

//        }

    }

    private int width, height;

//    @Override
//    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
//        if (width == 0 || height == 0) {
//            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
//            height = layoutParams.height = (int) (layoutParams.height / 0.7f);
//            width = layoutParams.width = (int) (layoutParams.width / 0.7f);
//            child.setLayoutParams(layoutParams);
//        }
//        return super.drawChild(canvas, child, drawingTime);
//    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
