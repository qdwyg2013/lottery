package com.lottery.ssq.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lottery.common.utils.IpUtils;
import com.lottery.common.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LogAspect {

    public static final String START_TIME_Mills = "START_TIME_Mills";

    private static ThreadLocal<HttpServletRequest> requestThread = new ThreadLocal<>();

    /**
     * 定义切入点
     */
    @Pointcut("execution(public * com.lottery.ssq.controller..*.*(..))")
    public void requestLog() {
    }

    /**
     * 方法之前执行，日志打印请求信息
     *
     * @param joinPoint joinPoint
     */
    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        request.setAttribute(START_TIME_Mills, System.currentTimeMillis());
        requestThread.set(request);

        //打印当前的请求日志
        log.info("RequestLog: session->[{}];ip->[{}];url->[{}];params->[{}];",
                request.getSession().getId(),
                IpUtils.getIpAddress(request),
                request.getRequestURL().toString(),
                JSONObject.toJSONString(joinPoint.getArgs()));
    }

    /**
     * 方法返回之前执行，打印返回日志
     *
     * @param results 返回值
     */
    @AfterReturning(returning = "results", pointcut = "requestLog()")
    public void doAfterRunning(Object results) {
        HttpServletRequest request = requestThread.get();
        // 计算接口耗时
        Long startTime = (Long) request.getAttribute(LogUtils.START_TIME_Mills);
        long timeCost = System.currentTimeMillis() - startTime;

        //打印当前的返回日志
        log.info("ResultLog: session->[{}];ip->[{}];url->[{}];results->[{}];timeCost->[{}];",
                request.getSession().getId(),
                IpUtils.getIpAddress(request),
                request.getRequestURL().toString(),
                JSONObject.toJSONString(results),
                timeCost);
    }
}
