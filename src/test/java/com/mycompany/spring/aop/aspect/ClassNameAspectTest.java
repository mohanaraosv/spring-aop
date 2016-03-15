package com.mycompany.spring.aop.aspect;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.spring.aop.aspect.config.SystemConfiguration;
import com.mycompany.spring.aop.aspect.repository.SimpleRepository;
import com.mycompany.spring.aop.simple.SimpleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SystemConfiguration.class)
public class ClassNameAspectTest {

	@Autowired
	private ClassNameAspect aroundTracingAspect;

	@Autowired
	private SimpleRepository simpleRepository;

	@Autowired
	private SimpleService simpleService;

	@Before
	public void setUp() {
		aroundTracingAspect.resetCalled();
	}

	@Test
	public void tracingOnServiceIsCalled() {
		assertThat(aroundTracingAspect.getCalled(), is(0));
		simpleService.doSomething();
		assertThat(aroundTracingAspect.getCalled(), is(1));
	}

	@Test
	public void tracingOnRepsositoryIsNotCalled() {
		assertThat(aroundTracingAspect.getCalled(), is(0));
		simpleRepository.doSomething();
		assertThat(aroundTracingAspect.getCalled(), is(0));
	}
}
