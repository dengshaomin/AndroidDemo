package com.brothergang.demo.aop;

import java.lang.reflect.Field;
import java.util.HashMap;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class HookAspect {

    @Pointcut("execution(* android.widget.Toast+.*(..))")
    public void hookPointCut() {
    }

    @Around("hookPointCut()")
    public void doHookPointCut(ProceedingJoinPoint joinPoint) {
        try {
            Toast toast = (Toast) joinPoint.getTarget();
            Context context = null;
            context = (Context) getValue(toast, "mContext");
            //如果当前没有context意味着可能页面被回收，或者的版本在19以上且通知可用，执行系统的Toast方案交给系统处理
            if (context == null || Build.VERSION.SDK_INT >= 19 && NotificationManagerCompat.from(context).areNotificationsEnabled()) {
                joinPoint.proceed(joinPoint.getArgs());
            } else {//如果context存在，并且通知不可用，则使用自定义的Toast
                int mDuration = toast.getDuration();
                new IToast().show(context instanceof Application ? context : context.getApplicationContext(), "", mDuration);

            }
        } catch (Throwable exception) {
        }
    }

    public static Object getValue(Object object, String fieldName) throws Exception {
        if (object == null || TextUtils.isEmpty(fieldName)) {
            return null;
        }
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(object);
            } catch (Exception e) {
//ignore
            }
        }
        return null;
    }

    public class IToast {

        /**
         * 展示toast==LENGTH_SHORT
         */
        public void show(Context context, String msg) {
            show(context, msg, Toast.LENGTH_SHORT);
        }

        /**
         * 展示toast==LENGTH_LONG
         */
        public void showLong(Context context, String msg) {
            show(context, msg, Toast.LENGTH_LONG);
        }


        private void show(Context context, String massage, int show_length) {
            //使用布局加载器，将编写的toast_layout布局加载进来
            View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
            //获取ImageView
            ImageView image = (ImageView) view.findViewById(R.id.toast_iv);
            //设置图片
            image.setImageResource(R.mipmap.ic_launcher);
            //获取TextView
            TextView title = (TextView) view.findViewById(R.id.toast_tv);
            //设置显示的内容
            title.setText(massage);
            Toast toast = new Toast(context);
            //设置Toast要显示的位置，水平居中并在底部，X轴偏移0个单位，Y轴偏移70个单位，
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 70);
            //设置显示时间
            toast.setDuration(show_length);

            toast.setView(view);
            toast.show();
        }

    }
}
