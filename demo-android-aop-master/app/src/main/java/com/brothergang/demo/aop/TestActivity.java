package com.brothergang.demo.aop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = TestActivity.class.getSimpleName();

    public String tag = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        debugLog(" === onCreate ===");
    }

    @Override
    protected void onStart() {
        super.onStart();
        debugLog(" === onStart ===");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        debugLog(" === onRestart ===");
    }

    @Override
    protected void onResume() {
        super.onResume();
        debugLog(" === onResume ===");
    }

    @Override
    protected void onPause() {
        super.onPause();
        debugLog(" === onPause ===");
    }

    @Override
    protected void onStop() {
        super.onStop();
        debugLog(" === onStop ===");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        debugLog(" === onDestroy ===");
    }

    private void debugLog(String loginfo) {
        LogUtils.e(loginfo);
    }

    @MyAnnatation(value = "ourself")
    private void aopAnnatation() {
        LogUtils.e("11111111111");
    }
}
