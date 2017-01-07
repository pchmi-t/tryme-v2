package org.fmi.tryme.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {
	
	@Around("execution(* org.fmi.tryme.test.TestController+.*(..))")
	public Object logRestEntry(ProceedingJoinPoint joinPoint) throws Throwable {

		final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());
		String signature = joinPoint.getSignature().getName();

		logger.info("Calling [{}] with arguments [{}]", signature, joinPoint.getArgs());
		Object output = joinPoint.proceed();

		logger.info("Returning from [{}] with output [{}]", signature, output);
		return output;

	}
}
