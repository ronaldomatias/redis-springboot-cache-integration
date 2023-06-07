package com.tcclibrary.aop;

import com.tcclibrary.annotation.Cacheable;
import com.tcclibrary.domain.CacheManagerService;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@org.aspectj.lang.annotation.Aspect
@Log4j2
public class CacheManagerAspect {

	@Autowired
	CacheManagerService cacheManager;

	@Around("execution(* com.tcclibrary.api.service..*(..)))")
	public Object allMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		if (!methodSignature.getMethod().isAnnotationPresent(Cacheable.class)){
			return proceedingJoinPoint.proceed();
		}

		Cacheable annotation = methodSignature.getMethod().getAnnotation(Cacheable.class);
		return cacheManager.cacheable(annotation, proceedingJoinPoint);
	}

}