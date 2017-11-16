package com.example.task1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tkaczenko on 25.01.17.
 */
@Aspect
public class MethodLogger {
    @Around("execution(* *(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Logger logger = LoggerFactory.getLogger(point.getSignature().getDeclaringType());
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        logger.info("#{}({}): {} in {} ms",
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
                point.getArgs(),
                result,
                System.currentTimeMillis() - start
        );
        return result;
    }

    @Before("execution(* *(..))")
    public void logBefore(JoinPoint point) {
        Logger logger = LoggerFactory.getLogger(point.getSignature().getDeclaringType());
        logger.info("Before " + point.getSignature().getName());
    }

    @After("execution(* *(..))")
    public void logAfter(JoinPoint point) {
        Logger logger = LoggerFactory.getLogger(point.getSignature().getDeclaringType());
        logger.info("After " + point.getSignature().getName());
    }
}
