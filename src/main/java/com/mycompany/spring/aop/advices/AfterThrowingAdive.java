package com.mycompany.spring.aop.advices;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterThrowingAdive {

	private static Logger logger = LoggerFactory.getLogger(AfterThrowingAdive.class);

	private boolean afterThrowingCalled = false;

	public void reset() {
		afterThrowingCalled = false;
	}

	public boolean isAfterThrowingCalled() {
		return afterThrowingCalled;
	}

	@AfterThrowing(pointcut = "execution(void throwsRuntimeException())", throwing = "re")
	public void logException(RuntimeException re) {
		afterThrowingCalled = true;
		logger.error("Exception : {}", re);
	}
}
