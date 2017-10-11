package com.nothing.scroller;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    Notification mNotification;
    NotificationManager manager;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ScrollerButton scrollButton = (ScrollerButton) findViewById(R.id.scrollButton);
        final ScrollViewGroup scrollView = (ScrollViewGroup) findViewById(R.id.scrollView);
        scrollButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        scrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.smoothScrollBy(0, -100, 2000);
                manager.notify(1, mNotification);
            }
        });


        RemoteViews mRemoteViews = new RemoteViews(getPackageName(), R.layout.view_remote);

        //2.构建一个打开Activity的PendingIntent
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent mPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //3.创建一个Notification
        mNotification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(mPendingIntent)
                .setContent(mRemoteViews)
                .build();

        //4.获取NotificationManager
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    }
}
