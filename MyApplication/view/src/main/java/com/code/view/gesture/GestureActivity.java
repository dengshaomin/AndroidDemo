package com.code.view.gesture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.code.view.R;
import com.code.view.SPHelper;
import com.code.view.SPHelper.ContentValue;

public class GestureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        String ms = SPHelper.getInstance(this).getString("static_broadcast");
        if (!TextUtils.isEmpty(ms)) {
            Log.e("code", ms);
        }
    }
}
