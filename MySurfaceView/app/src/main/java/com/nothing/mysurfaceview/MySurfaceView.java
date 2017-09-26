package com.nothing.mysurfaceview;

/**
 * 使用SurfaceView与Canvas的结合完成
 * 简单的动画效果
 */

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//继承SurfaceView父类并实现SurfaceView.Callback接口
public class MySurfaceView extends SurfaceView implements
        SurfaceHolder.Callback {
    //创建画笔对象
    private Paint paint = new Paint();
    //定义计时器Timer定时器工具，用来在一个后台线程计划执行指定任务。它可以计划执行一个任务一次或反复多次。
    private Timer timer;
    //TimerTask一个抽象类，它的子类代表一个可以被Timer计划的任务
    private TimerTask task;
    //定义并初始化要绘的图形的坐标
    private float x = 0;
    private float y = 0;
    private float speedx = 50;
    private float speedy = 50;
    //定义并初始化坐标移动的变化量
    private float addx = 2;
    private float addy = 2;

    /**
     * SurfaceView的构造方法
     * 在其中要初始化画笔的颜色
     *
     * @param context
     */
    public MySurfaceView(Context context) {
        super(context);
        paint.setColor(Color.BLUE);
        getHolder().addCallback(this);
    }

    /**
     * 绘图方法，在其中完成具体的过程
     */
    public void draw() {
        // 锁定画布
        Canvas canvas = getHolder().lockCanvas();
        // 初始化画布
        canvas.drawColor(Color.WHITE);
        //绘制图形，这里画个矩形
        canvas.drawRect(x, y, speedx + x, speedy + y, paint);
        x += addx;
        y += addy;
        //下面是矩形的移动路径
        if (x < 0) {
            //如果图形左边界坐标超出左屏幕则向右移动
            addx = Math.abs(addx);
        }
        if (x > getWidth() - speedx) {
            //如果图形右边界坐标超出屏幕的宽度则向左移动
            addx = -Math.abs(addx);
        }
        if (y < 0) {
            addy = Math.abs(addy);
        }
        if (y > getHeight() - speedy) {
            addy = -Math.abs(addy);
        }
        // 解锁画布
        getHolder().unlockCanvasAndPost(canvas);
    }

    /**
     * 启动定时器后台线程
     */
    public void startTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                //在定时器线程中调用绘图方法
                draw();
            }
        };
        //设置定时器每隔0.1秒启动这个task,实现动画效果
        timer.schedule(task, 0, 10);
    }

    /**
     * 停止定时器线程的方法
     */
    public void stopTimer() {
        timer.cancel();
    }

    /**
     * 下面是必须要重写的方法
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //一定要在SurfaceView创建之后启动线程
        startTimer();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //一定要在SurfaceView销毁之前结束线程
        stopTimer();
    }
}