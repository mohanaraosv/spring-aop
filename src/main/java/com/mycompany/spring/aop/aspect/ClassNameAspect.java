package com.mycompany.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author mohanarao_sv
 *
 */
@Aspect
@Component
public class ClassNameAspect {
	private static Logger logger = LoggerFactory.getLogger(ClassNameAspect.class);
	public int called = 0;

	public int getCalled() {
		return called;
	}

	public void resetCalled() {
		this.called = 0;
	}

	// looks in the specified package
	// @Around("execution(* com.mycompany..simple.*.*(..))")
	// @Around("execution(* *..*simple.*(..))")
	@Around("execution(* com.mycompany.spring.aop.simple.*.*(..))")
	public void trace(ProceedingJoinPoint pjp) throws Throwable {
		String methodInvocation = pjp.getSignature().toString();
		logger.info("Entering {} ", methodInvocation);
		called++;
		try {
			pjp.proceed();
		} catch (Throwable te) {
			logger.error("Exception in {}", methodInvocation, te);
			throw te;
		} finally {
			logger.info("Existing method {}", methodInvocation);
		}
	}
}
