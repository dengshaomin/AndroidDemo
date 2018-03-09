package com.brothergang.demo.aop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mBtnIntent, btnClick;

    @MyAnnatation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            String s = null;
            s.substring(0, 10);
        } catch (Exception e) {
            LogUtils.e("exception");
        }

        exceptionTest();
        setContentView(R.layout.activity_main);
        LogUtils.e("onCreate");
        mBtnIntent = (Button) findViewById(R.id.btn_intent);
        mBtnIntent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        btnClick = (Button) findViewById(R.id.btn_click);
        btnClick.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onClick(View view) {
        LogUtils.e("click test");
    }

    public void exceptionTest() {
        try {
            String s = "123";
            s.substring(0, 10);
        } catch (Exception e) {
            LogUtils.e("exception");
        }
    }
}
