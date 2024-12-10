//package com.zy.zhangyue001.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class PerformanceAspect {
//
//    @Around("execution(com.zy.zhangyue001.service.UserService.getUsers())")
//    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        //调用方法的开始时间
//        long startTime = System.nanoTime();
//        //调用目标方法
//        Object result = joinPoint.proceed();
//        //方法执行完成时间
//        long endTime = System.nanoTime();
//
//        String name = joinPoint.getSignature().getName();
//
//        System.out.println("查询用户方法 " + name + " :: " + ((endTime - startTime)/1e6) + " ms");
//
//        return result;
//
//    }
//}
