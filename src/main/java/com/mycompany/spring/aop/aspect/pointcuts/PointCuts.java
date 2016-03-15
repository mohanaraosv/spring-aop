package com.mycompany.spring.aop.aspect.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

/**
 * It's reusable points define all the points cuts here reuse then
 * 
 * @author mohanarao_sv
 *
 */
public class PointCuts {

	// @Pointcut("bean(*)") wild card
	@Pointcut("bean(*Service)")
	public void beanNamePointcut() {
	}
}
