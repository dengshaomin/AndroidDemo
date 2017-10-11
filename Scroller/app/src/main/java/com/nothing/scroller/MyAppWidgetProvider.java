package com.nothing.scroller;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by dengshaomin on 2017/10/11.
 */

public class MyAppWidgetProvider extends AppWidgetProvider {
    public static final String TAG = "MyAppWidgetProvider";
    public static final String CLICK_ACTION = "com.nothing.scroller.action_CLICK";

    public MyAppWidgetProvider() {
        super();
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(CLICK_ACTION)) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    Bitmap srbBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.timg);

                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                    for (int i = 0; i < 37; i++) {
                        float degress = (i * 10) % 360;
                        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout
                                .view_widget);
                        remoteView.setImageViewBitmap(R.id.image, roateBitmap(context, srbBitmap, degress));

                        Intent intentClick = new Intent();
                        intentClick.setAction(CLICK_ACTION);

                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);

                        remoteView.setOnClickPendingIntent(R.id.image, pendingIntent);
                        appWidgetManager.updateAppWidget(new ComponentName(context, MyAppWidgetProvider.class), remoteView);

                        SystemClock.sleep(30);
                    }

                }
            }).start();
        }
    }


    private Bitmap roateBitmap(Context context, Bitmap srbBitmap, float degress) {
        Matrix matrix = new Matrix();
        //选择角度 饶(0,0)点选择 正数顺时针 负数逆时针 中心旋转
        matrix.setRotate(degress, srbBitmap.getWidth() / 2, srbBitmap.getHeight() / 2);
        Bitmap createBmp = Bitmap.createBitmap(srbBitmap.getWidth(), srbBitmap.getHeight(), srbBitmap.getConfig());
        Canvas canvas = new Canvas(createBmp);
        Paint paint = new Paint();
        canvas.drawBitmap(srbBitmap, matrix, paint);
        return createBmp;
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

//
        for (int i = 0; i < appWidgetIds.length; i++) {
            int appwidgetid = appWidgetIds[i];
            onWidgetUpdate(context, appWidgetManager, appwidgetid);
        }
    }

    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appwidgetid) {


        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.view_widget);

        Intent intentClick = new Intent();
        intentClick.setAction(CLICK_ACTION);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);

        remoteViews.setOnClickPendingIntent(R.id.image, pendingIntent);
        appWidgetManager.updateAppWidget(appwidgetid, remoteViews);
    }
}
