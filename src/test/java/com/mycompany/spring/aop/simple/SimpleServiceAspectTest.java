package com.mycompany.spring.aop.simple;

import org.junit.runner.RunWith;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.spring.aop.simple.config.SimpleAspectConfiguration;

/**
 * @author mohanarao_sv
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleAspectConfiguration.class)
public class SimpleServiceAspectTest {

	@Autowired
	private TraceAspect traceAspect;

	@Autowired
	private SimpleService simpleService;

	@Test
	public void doSomething() {
		assertFalse(traceAspect.isEnteringEnabled());
		simpleService.doSomething();
		assertTrue(traceAspect.isEnteringEnabled());
	}

	@Test
	public void doSomethingElse() {
		assertFalse(traceAspect.isEnteringEnabled());
		simpleService.doSomethingElse();
		// Assert FALSE - aspect not matching with advice
		assertFalse(traceAspect.isEnteringEnabled());
	}
}
