package com.mycompany.spring.aop.simple;

import org.springframework.stereotype.Service;

import com.mycompany.spring.aop.aspect.annotation.Trace;

@Service
public class SimpleService {

	public void doSomething() {

	}

	public void doSomethingElse() {

	}

	public int returnsInt() {
		return 40;
	}

	public String returnString() {
		return "String";
	}

	public void throwsRuntimeException() {
		throw new RuntimeException("RunTime Excpetion");
	}

	@Trace
	public void annotated() {
	}
}
