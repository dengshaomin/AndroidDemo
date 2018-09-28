package com.code.view.windowsmanager;

import java.util.HashMap;
import java.util.Map;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.Toast;

import com.code.view.R;

public class WindowsManagerActivity extends AppCompatActivity implements OnTouchListener {

    Button floatButton;

    WindowManager.LayoutParams mLayoutParams;


    Map<Integer, Boolean> ticks = new HashMap<>();

    Object mLocked = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_windows_manager);
        findViewById(R.id.window).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (floatButton == null) {
                    floatButton = new Button(WindowsManagerActivity.this);
                    floatButton.setBackgroundResource(R.drawable.man);
                    floatButton.setId(R.id.button_windowmanager);
                    mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSLUCENT);
                    mLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL | LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_SHOW_WHEN_LOCKED;
                    mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
                    mLayoutParams.x = 100;
                    mLayoutParams.y = 300;
                    mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
                    floatButton.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(WindowsManagerActivity.this, "oh,god", Toast.LENGTH_SHORT).show();
                        }
                    });
                    floatButton.setOnTouchListener(WindowsManagerActivity.this);
                }
                if (!floatButton.isAttachedToWindow()) {
                    getWindowManager().addView(floatButton, mLayoutParams);
                }
            }
        });
        findViewById(R.id.remove).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (floatButton != null && floatButton.isAttachedToWindow()) {
                    getWindowManager().removeViewImmediate(floatButton);
                }
            }
        });

        for (int i = 0; i < 20; i++) {
            ticks.put(i, false);
        }

        new TickBuyThread(1).start();
        new TickBuyThread(2).start();
    }

    class TickBuyThread extends Thread {

        private int index;

        TickBuyThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            while (hasTicks()) {
                for (Integer S : ticks.keySet()) {
                    synchronized (mLocked) {
                        if (!ticks.get(S)) {
                            ticks.put(S, true);
                            Log.e("code", index + "   get:" + S);
                            try {
                                sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                        } else {
                            continue;
                        }
                    }
                }
            }
            Log.e("code", index + "   finish");
        }

    }



    private boolean hasTicks() {
        boolean flag = false;
        for (Boolean sale : ticks.values()) {
            if (!sale) {
                flag = true;
            }
        }
        return flag;
    }

    int oldx, oldy;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mLayoutParams.x += (int) event.getRawX() - oldx;
                mLayoutParams.y += (int) event.getRawY() - oldy;
                getWindowManager().updateViewLayout(floatButton, mLayoutParams);
                break;
            default:
                break;
        }
        oldx = (int) event.getRawX();
        oldy = (int) event.getRawY();
        return false;
    }
}
