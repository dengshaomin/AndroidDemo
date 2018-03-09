package com.brothergang.demo.aop;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class ExceptionAspect {

    /**
     * 截获所有exception
     */
//    @Pointcut("handler(java.lang.Exception+)&&args(e)")
//    public void handle(Exception e) {
//
//    }
//
//    @Before("handle(Exception)")
//    public void doException(JoinPoint joinPoint) {
//        Object[] exception = joinPoint.getArgs();
//        String s = "";
//        if (exception != null && exception.length > 0) {
//            s += ((Exception) exception[0]).getMessage();
//        }
//        LogUtils.e("AOP 埋点：" + joinPoint.toShortString() + "==" + s);
//    }
}
