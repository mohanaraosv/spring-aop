package com.mycompany.spring.aop.advice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.spring.aop.advices.AfterReturningAdive;
import com.mycompany.spring.aop.simple.SimpleService;
import com.mycompany.spring.aop.simple.config.SimpleAspectConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleAspectConfiguration.class)
public class AfterReturningAdiveTest {

	@Autowired
	private AfterReturningAdive afterReturningAdive;

	@Autowired
	private SimpleService simpleService;

	@Before
	public void reset() {
		afterReturningAdive.reset();
	}

	@Test
	public void afterReturningIsNotCalledIfReturnTypeDoesntMatch() {
		assertFalse(afterReturningAdive.isAfterReturnCalled());
		simpleService.returnsInt();
		assertFalse(afterReturningAdive.isAfterReturnCalled());
	}

	@Test(expected = RuntimeException.class)
	public void afterReturningIsNotCalledIfExceptionIsThrown() {
		assertFalse(afterReturningAdive.isAfterReturnCalled());
		try {
			simpleService.throwsRuntimeException();
		} finally {
			assertFalse(afterReturningAdive.isAfterReturnCalled());
		}
	}

	@Test
	public void afterReturningIsCalledIfReturnTypeMatches() {
		assertFalse(afterReturningAdive.isAfterReturnCalled());
		simpleService.returnString();
		assertTrue(afterReturningAdive.isAfterReturnCalled());
	}
}
