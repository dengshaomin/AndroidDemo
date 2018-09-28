package com.code.view.touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.code.view.R;

public class TouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("code", this.getClass().getName() + " " + ev.getAction() + " " + "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("code", this.getClass().getName() + " " + event.getAction() + " " + "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
