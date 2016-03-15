package com.mycompany.spring.aop.advices;

import org.aspectj.lang.annotation.AfterReturning;
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
public class AfterReturningAdive {

	private static Logger logger = LoggerFactory.getLogger(AfterReturningAdive.class);

	private boolean afterReturnCalled = false;

	public void reset() {
		afterReturnCalled = false;
	}

	public boolean isAfterReturnCalled() {
		return afterReturnCalled;
	}

	@AfterReturning(pointcut = "execution(* *(..))", returning = "string")
	public void logResult(String string) {
		afterReturnCalled = true;
		logger.info("Result : {}", string);
	}
}
