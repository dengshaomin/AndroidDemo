package com.brothergang.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class DemoAspect {

    static final String TAG = "DemoAspect";

    /**
     * 完整写法
     */
//    @Pointcut("execution(* com.brothergang.demo.aop.MainActivity.onCreate(..))")
//    public void pointCutOncreate() {
//    }
//
//    @Before("pointCutOncreate()")
//    public void beforePointCutOncreate(JoinPoint joinPoint) {
//        LogUtils.e("AOP 埋点：" + joinPoint.toShortString() + "==before");
//    }
//
//    @After("pointCutOncreate()")
//    public void afterPointCutOncreate(JoinPoint joinPoint) {
//        LogUtils.e("AOP 埋点：" + joinPoint.toShortString() + "==after");
//    }
//
//    @Around("pointCutOncreate()")
//    public void aroundPointCutOncreate(ProceedingJoinPoint joinPoint) {
//        LogUtils.e("AOP 埋点：" + joinPoint.toShortString() + "==around");
//        try {
//            joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }

//    //* 号使用,activity 下所有子类方法
//    @Before("execution(* .onCreate(..))")
//    public void allPointCut(JoinPoint joinPoint) {
//        LogUtils.e("AOP 埋点：" + joinPoint.toShortString() + "== +++");
//    }
//
//    //+ 号使用,activity 下所有子类方法
//    @Before("execution(* android.app.Activity+.*(..))")
//    public void addPointCut(JoinPoint joinPoint) {
//        LogUtils.e("AOP 埋点：" + joinPoint.toShortString() + "== +++");
//    }
//
//    //  || 同时切入mainactivity的两个方法
//    @Before("execution(* com.brothergang.demo.aop.MainActivity.onCreate(..)) ||"
//            + "execution(* com.brothergang.demo.aop.MainActivity.onStart(..)) ||")
//    public void contactPointCut(JoinPoint joinPoint) {
//        LogUtils.e("AOP 埋点：" + joinPoint.toShortString() + "== +++");
//    }
//
//    // .. 参数
//    @Before("execution(* com.brothergang.demo.aop.MainActivity.onClick(..))")
//    public void paramsPointCut(JoinPoint joinPoint) {
//        Object[] objects = joinPoint.getArgs();
//        LogUtils.e("AOP 埋点：" + joinPoint.toShortString() + "== +++");
//    }


//    @Around("execution(* aopAnnatation(..))&&@annotation(params)")
//    public void aopAnnatation(ProceedingJoinPoint joinPoint, MyAnnatation params) {
////        joinPoint.getArgs()   //获取getargs 获取参数
//        LogUtils.e("AOP 埋点：" + joinPoint.toShortString() + "--" + params.value().toString());
//        try {
//            joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }

}
