package com.code.view.surface;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Build.VERSION_CODES;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Runnable, Callback {

    private SurfaceHolder mHolder; // 用于控制SurfaceView

    private Thread drawT; // 声明一条线程

    private volatile boolean flag; // 线程运行的标识，用于控制线程

    private Canvas mCanvas; // 声明一张画布

    private Paint p; // 声明一支画笔

    float m_circle_r = 10;

    Context mContext;

    Thread bornDataT;

    Thread caDataT;

    List<RoundRect> mRoundRects;

    List<Bitmap> mBitmaps;

    int screenH, screenW;

    int duarationTime = 16;

    int radius = 5;

    int moveDistance = 10;

    Camera mCamera;

    Handler mHandler = new Handler() {
        @RequiresApi(api = VERSION_CODES.LOLLIPOP)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                caData();
            } else if (msg.what == 1) {
                int a = 1;
            }
        }
    };

    public MySurfaceView(Context context) {
        super(context);
        this.mContext = context;
        getScreentInfo();
        mHolder = getHolder(); // 获得SurfaceHolder对象
        mHolder.addCallback(this); // 为SurfaceView添加状态监听
        p = new Paint(); // 创建一个画笔对象
        p.setColor(Color.BLACK); // 设置画笔的颜色为白色
        setFocusable(true); // 设置焦点
        mCamera = new Camera();
        mMatrix = new Matrix();
    }

    void getScreentInfo() {
        Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
        screenH = display.getHeight();
        screenW = display.getWidth();
    }

    void bornData() {
        bornDataT = new Thread(new Runnable() {
            @Override
            public void run() {
                mRoundRects = new ArrayList<>();
                int xRang = screenW / 10;
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < 100; j++) {
                        float left = (float) ((Math.random() * xRang) * 10);
                        float bottom = (float) -(Math.random() * screenH + i * screenH);
                        float top = (float) (bottom - (screenH / 5) + Math.random() * 50);
                        float right = left + 10;
                        mRoundRects.add(new RoundRect(left, top, right, bottom));
                    }
                }
                mHandler.sendEmptyMessage(0);

            }
        });
        bornDataT.start();
    }

    Canvas caCanvas;

    Paint caPaint;

    Matrix mMatrix;

    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    void caData() {
        caDataT = new Thread(new Runnable() {
            //            @RequiresApi(api = VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                mBitmaps = new ArrayList<>();
                caPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                caPaint.setColor(Color.BLUE);
                caPaint.setStyle(Style.FILL);
//                mMatrix = new Matrix();
//                float[] src = {0, 0, 0, screenH, screenW, screenH, screenW, 0};
//                float[] dst = {0 + 800, 0, 0, screenH, screenW, screenH, screenW - 800, 0};
//                mMatrix.setPolyToPoly(src, 0, dst, 0, 4);
                boolean allFinish = false;
                while (!allFinish) {
                    if (mBitmaps.size() < 20) {
                        caCanvas = new Canvas();
                        Bitmap bitmap = Bitmap.createBitmap(screenW, screenH, Config.ARGB_8888);
                        bitmap.eraseColor(Color.parseColor("#ffffff"));
                        float minY = mRoundRects.get(0).top;
                        for (int i = 0; i < mRoundRects.size(); i++) {
                            RoundRect roundRect = mRoundRects.get(i);
                            roundRect.bottom += moveDistance;
                            roundRect.top += moveDistance;
                            if (roundRect.top < minY) {
                                minY = roundRect.top;
                            }
                            if (roundRect.top <= screenH && roundRect.bottom >= 0) {
                                caCanvas.setBitmap(bitmap);
                                caCanvas.drawRoundRect(roundRect.left, roundRect.top, roundRect.right, roundRect.bottom, radius, radius, caPaint);
                            }
                        }
                        mCamera = new Camera();
                        mMatrix.reset();
                        mCamera.save();
                        mCamera.rotateX(30);
                        mCamera.getMatrix(mMatrix);
                        mCamera.restore();
                        mMatrix.preTranslate(-bitmap.getWidth() / 2, -bitmap.getHeight());
                        mMatrix.postTranslate(bitmap.getWidth() / 2, bitmap.getHeight());
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mMatrix, true);
                        mBitmaps.add(bitmap);
                        if (minY > screenH) {
                            allFinish = true;
                        }
                    }
                }
            }
        });
        caDataT.start();
    }

    Object lock = new Object();

    /**
     * 当SurfaceView创建的时候，调用此函数
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawT = new Thread(this); // 创建一个线程对象
        flag = true; // 把线程运行的标识设置成true
        drawT.start(); // 启动线程
    }

    /**
     * 当SurfaceView的视图发生改变的时候，调用此函数
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
    }

    /**
     * 当SurfaceView销毁的时候，调用此函数
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false; // 把线程运行的标识设置成false
        mHolder.removeCallback(this);
    }

    /**
     * 当屏幕被触摸时调用
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            bornData();
        }
        return true;
    }

    /**
     * 当用户按键时调用
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        surfaceDestroyed(mHolder);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void run() {
        while (flag) {
            try {
                synchronized (mHolder) {
                    long time = System.currentTimeMillis();
                    Draw(); // 调用自定义画画方法
                    long diff = System.currentTimeMillis() - time;
                    Log.e("cdoe",diff+"");
                    if (duarationTime - diff > 0) {
                        Thread.sleep(20);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (mCanvas != null) {
                    // mHolder.unlockCanvasAndPost(mCanvas);//结束锁定画图，并提交改变。

                }
            }
        }
    }


    /**
     * 自定义一个方法，在画布上画一个圆
     */
    protected void Draw() {
        mCanvas = mHolder.lockCanvas(); // 获得画布对象，开始对画布画画
        if (mCanvas != null && mBitmaps != null && mBitmaps.size() > 0) {
//            mCanvas.drawRect(0, 0, screenW, screenH, p);
            mCanvas.drawBitmap(mBitmaps.get(0), 0, 0, p);
            Bitmap bitmap = mBitmaps.get(0);
            if (bitmap != null && !bitmap.isRecycled()) {
                mBitmaps.remove(bitmap);
                bitmap.recycle();
                bitmap = null;
            }
        }
        mHolder.unlockCanvasAndPost(mCanvas); // 完成画画，把画布显示在屏幕上
    }

    class RoundRect {

        public float left, top, right, bottom;

        public RoundRect(float left, float top, float right, float bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }

    }
}