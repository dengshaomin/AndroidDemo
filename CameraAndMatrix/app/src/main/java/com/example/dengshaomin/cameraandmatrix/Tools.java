package com.example.dengshaomin.cameraandmatrix;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by dengshaomin on 2017/8/30.
 */

public class Tools {
    public static int getScreenWidth(Context context){
        WindowManager wm = ((Activity)context).getWindowManager();
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context){
        WindowManager wm = ((Activity)context).getWindowManager();
        return wm.getDefaultDisplay().getHeight();
    }
}
