package com.mycompany.spring.aop.simple;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author mohanarao_sv
 *
 */
@Aspect
@Component
public class TraceAspect {

	private static Logger logger = LoggerFactory.getLogger(TraceAspect.class);

	private boolean enteringCalled;

	public boolean isEnteringEnabled() {
		return enteringCalled;
	}

	// first * indicates return type
	// place holder * indicates class name
	// Third star indicates method name
	// (..) indicates any parameter
	@Before("execution(void doSomething())")
	public void entering(JoinPoint joinPoint) {
		enteringCalled = true;
		logger.info("Entered {}", joinPoint.getStaticPart().getSignature().toString());
	}
}
