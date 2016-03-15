package com.mycompany.spring.aop.advices;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterAdvice {

	private static Logger logger = LoggerFactory.getLogger(AfterAdvice.class);

	private boolean afterCalled = false;

	public void reset() {
		afterCalled = false;
	}

	public boolean isAfterCalled() {
		return afterCalled;
	}

	@After("execution (* *(..))")
	public void exiting(JoinPoint jp) {
		afterCalled = true;
		logger.info("Exiting {}", jp.getSignature());
		for (Object arg : jp.getArgs()) {
			logger.info("Arguments : {}", arg);
		}
	}
}
