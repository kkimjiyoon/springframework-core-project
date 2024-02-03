package com.nhnacademy.edu.springframework.project.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ExecutionTimeLogger {
    @Around("@annotation(com.nhnacademy.edu.springframework.project.annotation.ExecutionTime)")
    public Object printExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch("ExecutionLogger");
        stopWatch.start();

        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            stopWatch.stop();
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();
            String logMessage = String.format("%s.%s %s", className, methodName, stopWatch.getTotalTimeMillis() + "ms");

            System.out.println(logMessage);
        }
    }
}
