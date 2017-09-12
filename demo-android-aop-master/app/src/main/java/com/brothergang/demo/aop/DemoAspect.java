package com.brothergang.demo.aop;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by brothergang on 2017/3/22.
 */

@Aspect   //必须使用@AspectJ标注，这样class DemoAspect就等同于 aspect DemoAspect了
public class DemoAspect {
    static final String TAG = "DemoAspect";

    /*
    @Pointcut：pointcut也变成了一个注解，这个注解是针对一个函数的，比如此处的logForActivity()
    其实它代表了这个pointcut的名字。如果是带参数的pointcut，则把参数类型和名字放到
    代表pointcut名字的logForActivity中，然后在@Pointcut注解中使用参数名。
    基本和以前一样，只是写起来比较奇特一点。后面我们会介绍带参数的例子
    */
    @Pointcut("execution(* com.brothergang.demo.aop.TestActivity.onCreate(..)) ||"
            + "execution(* com.brothergang.demo.aop.TestActivity.onStart(..)) ||"
            + "execution(* com.brothergang.demo.aop.TestActivity.onResume(..)) ||"
            + "execution(* com.brothergang.demo.aop.TestActivity.onDestroy(..)) ||"
            + "execution(* com.brothergang.demo.aop.TestActivity.onPause(..))"
    )
    public void logForActivity() {
    }
    //注意，这个函数必须要有实现，否则Java编译器会报错

    /*
    @Before：这就是Before的advice，对于after，after -returning，和after-throwing。对于的注解格式为
    @After，@AfterReturning，@AfterThrowing。Before后面跟的是pointcut名字，然后其代码块由一个函数来实现。
            比如此处的log。
    */
    @Before("logForActivity()")
    public void log(JoinPoint joinPoint) {
        String tag = ((TestActivity) joinPoint.getTarget()).tag;
        Log.e(TAG, "AOP 埋点：" + joinPoint.toShortString() + "==" + tag);
    }

    /**
     * 精简写法
     */
    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    public void logClickEvent(ProceedingJoinPoint joinPoint) {
        Log.e(TAG, "AOP 埋点：" + joinPoint.toShortString());
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Around("execution(* debugLog(..))")
    public void debugLog(ProceedingJoinPoint joinPoint) {
//        joinPoint.getArgs()   //获取getargs 获取参数
        Log.e(TAG, "AOP 埋点：" + joinPoint.toShortString());
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Around("execution(* aopAnnatation(..))&&@annotation(params)")
    public void aopAnnatation(ProceedingJoinPoint joinPoint, MyAnnatation params) {
//        joinPoint.getArgs()   //获取getargs 获取参数
        Log.e(TAG, "AOP 埋点：" + joinPoint.toShortString() + "--" + params.value().toString());
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
