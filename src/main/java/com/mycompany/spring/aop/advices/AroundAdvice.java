package com.mycompany.spring.aop.advices;

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
@Component
@Aspect
public class AroundAdvice {

	private static Logger logger = LoggerFactory.getLogger(AroundAdvice.class);

	private boolean called = false;

	public void reset() {
		called = false;
	}

	public boolean isCalled() {
		return called;
	}

	@Around("execution(* *(..))")
	public Object logTrace(ProceedingJoinPoint pjp) throws Throwable {
		String methodInformation = pjp.getStaticPart().getSignature().toString();
		logger.info("Enter {}", methodInformation);
		called = true;
		try {
			return pjp.proceed();
		} catch (Throwable ex) {
			logger.error("Exception in : {}", methodInformation, ex);
			throw ex;
		} finally {
			logger.info("Exiting : {}", methodInformation);
		}
	}
}
