package com.mycompany.spring.aop.aspect;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
public class BeanNameAspectTest {

	@Autowired
	private BeanNameAspect beanNameAspect;

	@Autowired
	private SimpleService simpleService;

	@Autowired
	private SimpleRepository simpleRepository;

	@Before
	public void setUp() {
		beanNameAspect.resetCalled();
	}

	@Test
	public void tracingOnServiceIsCalled() {
		assertThat(beanNameAspect.getCalled(), is(0));
		simpleService.doSomething();
		assertThat(beanNameAspect.getCalled(), is(1));
	}

	@Test
	public void tracingOnRepsositoryIsNotCalled() {
		assertThat(beanNameAspect.getCalled(), is(0));
		simpleRepository.doSomething();
		assertThat(beanNameAspect.getCalled(), is(0));
	}
}
