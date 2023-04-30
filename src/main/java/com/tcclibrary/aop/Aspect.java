package com.tcclibrary.aop;

import com.tcclibrary.annotation.Cacheable;
import com.tcclibrary.component.redis.RedisBase;
import com.tcclibrary.component.redis.RedisDTO;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@org.aspectj.lang.annotation.Aspect
@Log4j2
public class Aspect {
	@Autowired
	RedisBase redisBase;

	@Around("execution(* com.tcclibrary.service..*(..)))")
	public Object allMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		if (!methodSignature.getMethod().isAnnotationPresent(Cacheable.class)){
			return proceedingJoinPoint.proceed();
		}

		Cacheable annotation = methodSignature.getMethod().getAnnotation(Cacheable.class);

		Object response;
		if (redisBase.existsKey(annotation.key())) {
			response = redisBase.get(annotation.key());
		} else {
			response = proceedingJoinPoint.proceed();
			redisBase.set(new RedisDTO(annotation.key(), response, methodSignature.getReturnType(), annotation.ttl()));
		}

		return response;
	}

}