package com.brothergang.demo.aop;

import java.util.HashMap;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class TimeAspect {

    HashMap<Object, TimeWatch> records = new HashMap<>();


    @Before("execution(* *..Activity+.onCreate(..)) ||"
            + "execution(* *..Activity+.onStop(..)) "
    )
    public void contactPointCut(JoinPoint joinPoint) {
        if (joinPoint == null) {
            return;
        }
        if (records.get(joinPoint.getTarget()) == null) {
            TimeWatch timeWatch = new TimeWatch();
            timeWatch.start();
            records.put(joinPoint.getTarget(), timeWatch);
        } else {
            TimeWatch timeWatch = records.get(joinPoint.getTarget());
            timeWatch.end();
            LogUtils.e(joinPoint.getTarget().toString() + "==" + timeWatch.getTotalTime());
        }
    }

    class TimeWatch {

        long startTime = 0;

        long endTime = 0;

        public void start() {
            startTime = System.currentTimeMillis();
            LogUtils.e(startTime + "==");
        }

        public void end() {
            endTime = System.currentTimeMillis();
            LogUtils.e(endTime + "==");
        }

        public long getTotalTime() {
            return endTime - startTime;
        }
    }


}
