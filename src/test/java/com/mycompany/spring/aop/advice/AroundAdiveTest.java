package com.mycompany.spring.aop.advice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.spring.aop.advices.AroundAdvice;
import com.mycompany.spring.aop.simple.SimpleService;
import com.mycompany.spring.aop.simple.config.SimpleAspectConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleAspectConfiguration.class)
public class AroundAdiveTest {

	@Autowired
	private AroundAdvice aroundAdvice;

	@Autowired
	private SimpleService simpleService;

	@Before
	public void reset() {
		aroundAdvice.reset();
	}

	@Test
	public void aroundAdviceIsCalledForSimpleMethod() {
		assertFalse(aroundAdvice.isCalled());
		simpleService.doSomething();
		assertTrue(aroundAdvice.isCalled());
	}

	@Test(expected = RuntimeException.class)
	public void aroundAdviceIsCalledIfMethodThrowsRuntimeException() {
		assertFalse(aroundAdvice.isCalled());
		try {
			simpleService.throwsRuntimeException();
		} finally {
			assertTrue(aroundAdvice.isCalled());
		}
	}

	@Test
	public void aroundAdviceIsCalledIfValueIsReturned() {
		assertFalse(aroundAdvice.isCalled());
		assertThat(simpleService.returnString(), equalTo("String"));
		assertTrue(aroundAdvice.isCalled());
	}
}
