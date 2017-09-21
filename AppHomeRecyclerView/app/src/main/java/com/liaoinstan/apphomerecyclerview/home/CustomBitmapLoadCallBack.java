package com.liaoinstan.apphomerecyclerview.home;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;

import org.xutils.common.Callback;

public class CustomBitmapLoadCallBack implements Callback.ProgressCallback<Drawable>{
    private final ImageView mImg;
    public CustomBitmapLoadCallBack(ImageView mImg) {
        this.mImg = mImg;
    }

    @Override
    public void onWaiting() {

    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {

    }

    @Override
    public void onSuccess(Drawable result) {
        final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(mImg.getResources().getColor(android.R.color.transparent));

        final TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[] { TRANSPARENT_DRAWABLE, result });
        mImg.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(500);
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
