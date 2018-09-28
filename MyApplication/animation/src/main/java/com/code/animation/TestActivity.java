package com.code.animation;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by dengshaomin on 2018/7/11.
 */

public class TestActivity extends Dialog {

    public TestActivity(@NonNull Context context) {
        super(context,R.style.Dialog_Fullscreen);
        setContentView(R.layout.dialog_test);
    }
}
