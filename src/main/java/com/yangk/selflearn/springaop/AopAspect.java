package com.yangk.selflearn.springaop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * @Description spring的aop使用
 * @Author yangkun
 * @Date 2019/8/5
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class AopAspect {

    @Pointcut("execution(public String com.yangk.selflearn.base.controller.*.*())")
    public void cut1() {
    }

    @Before("cut1()")
    public void joinBefore(JoinPoint joinPoint) {
        // 获取连接点方法运行时的入参列表
        Object[] args = joinPoint.getArgs();

        // 获取连接点的方法签名对象
        Signature signature = joinPoint.getSignature();
        Method method = ((MethodSignature) signature).getMethod();
        RequestMapping annotation = method.getAnnotation(RequestMapping.class);
        Controller annotation1 = method.getAnnotation(Controller.class);
        if (annotation1 == null) {
            log.info("joinBefore this method donot have Controller annotation");
        }

        // 获取连接点所在的目标对象
        Object target = joinPoint.getTarget();

        // 获取代理对象本身
        Object aThis = joinPoint.getThis();

        String kind = joinPoint.getKind();

        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();

        SourceLocation sourceLocation = joinPoint.getSourceLocation();
    }

    @Around(value = "cut1()")
    public String joinAround(ProceedingJoinPoint joinPoint) {
        String proceed = "";
        try {
            proceed = (String) joinPoint.proceed();
            log.info("----------joinAround proceed is {}", proceed);
        } catch (Throwable throwable) {
            log.error("----------hello error!", throwable.getMessage());
        }
        proceed = proceed + "good bye";
        return proceed;
    }


}
