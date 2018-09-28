package com.code.view;

import java.math.MathContext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.code.view.SPHelper.ContentValue;
import com.code.view.gesture.GestureActivity;

/**
 * Created by dengshaomin on 2018/6/25.
 */

public class StaticBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && !TextUtils.isEmpty(intent.getStringExtra("data"))) {
//            Log.e("code", intent.getStringExtra("data"));
//            Toast.makeText(context,intent.getStringExtra("data"),Toast.LENGTH_SHORT).show();
            SPHelper.getInstance(context).putValues(new ContentValue("static_broadcast", intent.getStringExtra("data")));
        }

        context.startActivity(new Intent(context, GestureActivity.class));
    }
}
