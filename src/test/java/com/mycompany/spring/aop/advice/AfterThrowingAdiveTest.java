package com.mycompany.spring.aop.advice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.spring.aop.advices.AfterThrowingAdive;
import com.mycompany.spring.aop.simple.SimpleService;
import com.mycompany.spring.aop.simple.config.SimpleAspectConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleAspectConfiguration.class)
public class AfterThrowingAdiveTest {

	@Autowired
	private AfterThrowingAdive afterThrowingAdvice;

	@Autowired
	private SimpleService simpleService;

	@Before
	public void reset() {
		afterThrowingAdvice.reset();
	}

	@Test
	public void afterThrowingIsCalledIfMethodReturnsSuccessfully() {
		assertFalse(afterThrowingAdvice.isAfterThrowingCalled());
		simpleService.doSomething();
		assertFalse(afterThrowingAdvice.isAfterThrowingCalled());
	}

	@Test(expected = RuntimeException.class)
	public void afterThrowingIsCalledIfMethodThrowsRuntimeException() {
		assertFalse(afterThrowingAdvice.isAfterThrowingCalled());
		try {
			simpleService.throwsRuntimeException();
		} finally {
			assertTrue(afterThrowingAdvice.isAfterThrowingCalled());
		}

	}
}
