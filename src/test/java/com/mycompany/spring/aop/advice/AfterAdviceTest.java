package com.mycompany.spring.aop.advice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.spring.aop.advices.AfterAdvice;
import com.mycompany.spring.aop.simple.SimpleService;
import com.mycompany.spring.aop.simple.config.SimpleAspectConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleAspectConfiguration.class)
public class AfterAdviceTest {

	@Autowired
	private AfterAdvice afterAdvice;

	@Autowired
	private SimpleService simpleService;

	@Before
	public void reset() {
		afterAdvice.reset();
	}

	@Test
	public void afterAspectIsCalledIfMethodReturnsSuccessfully() {
		assertFalse(afterAdvice.isAfterCalled());
		simpleService.doSomething();
		assertTrue(afterAdvice.isAfterCalled());
	}
}
