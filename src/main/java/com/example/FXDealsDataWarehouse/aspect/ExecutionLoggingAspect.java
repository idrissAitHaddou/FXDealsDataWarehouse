package com.example.FXDealsDataWarehouse.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExecutionLoggingAspect {

    @Pointcut("execution(* com.example.fxdealsdatawarehouse.service.*.*(..))")
    public void serviceLayer() {}

    @Before("serviceLayer()")
    public void beforeService(JoinPoint joinPoint) {
        log.info("➡️ Entering: {} with args {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "serviceLayer()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info("✅ Completed: {} | Returned: {}", joinPoint.getSignature().toShortString(), result);
    }

    @AfterThrowing(pointcut = "serviceLayer()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        log.error("❌ Exception in {} | Message: {}", joinPoint.getSignature().toShortString(), ex.getMessage());
    }

    @After("serviceLayer()")
    public void afterFinally(JoinPoint joinPoint) {
        log.debug("↩️ Exiting: {}", joinPoint.getSignature().toShortString());
    }
}

