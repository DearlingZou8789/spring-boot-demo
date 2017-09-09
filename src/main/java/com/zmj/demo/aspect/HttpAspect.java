package com.zmj.demo.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

//  切面可用于日志
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    //  切面（CustomerController中所有方法调用作为切入点
    @Pointcut("execution (public * com.zmj.demo.controller.CustomerController.*(..))")
    public void log(){

    }

    //  方法调用前
    @Before("log()")
    public void doBefore(JoinPoint joinpoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //  url
        logger.info("url={}", request.getRequestURL());

        //  method
        logger.info("method={}", request.getMethod());

        //  ip
        logger.info("ip={}", request.getRemoteAddr());

        //  类方法
        logger.info("class_method={}", joinpoint.getSignature().getDeclaringTypeName() + "." + joinpoint.getSignature().getName());

        //  参数
        logger.info("args={}", joinpoint.getArgs());
    }

    //  方法调用后
    @After("log()")
    public void doAfter(){
        logger.info("After Inject");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("response={}", object.toString());
    }

}
