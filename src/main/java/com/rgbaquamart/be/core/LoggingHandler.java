package com.rgbaquamart.be.core;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingHandler {
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    private void pointcutController() {}
	
	@Before("pointcutController()")
	public void logBeforeController(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String userAgent = request.getHeader("user-agent");
		String requestId = request.getHeader("x-request-id");
		String traceId = request.getHeader("x-b3-traceid");
		String spanId = request.getHeader("x-b3-spanid");
		String parentspanId = request.getHeader("x-b3-parentspanid");
		String sampled = request.getHeader("x-b3-sampled");
		String flags = request.getHeader("x-b3-flags");
		String spanContext = request.getHeader("x-ot-span-context");
		String apiName=request.getRequestURI();
		String userName=request.getHeader("username");
		DefaultRequestHeaders.getInstance().setHeaders(userAgent, requestId, traceId, spanId, parentspanId, sampled, flags, spanContext);
		Object[] methodeParams = joinPoint.getArgs();
		
		LogginAuthentcation.setUserName(userName);
	}
	@After("pointcutController()")
    public void logAfterController(JoinPoint joinPoint){
    }
	
	@Pointcut("execution(* com.rgbaquamart.be.service.*.*(..))")
    private void pointcutService() {}
	
	@Before("pointcutService()")
	public void logBeforeService(JoinPoint joinPoint) {
		Object[] methodeParams = joinPoint.getArgs();
	}
	@After("pointcutService()")
    public void logAfterService(JoinPoint joinPoint){
		
    }
}
