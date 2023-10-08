package com.tcclibrary.aop;

import com.tcclibrary.domain.CacheManagerService;
import com.tcclibrary.domain.RedisDTO;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@org.aspectj.lang.annotation.Aspect
@Log4j2
public class AOP {
	CacheManagerService cacheManagerService;

	public AOP (CacheManagerService cacheManagerService) {
		this.cacheManagerService = cacheManagerService;
	}

	@Around("execution(* com.tcclibrary.api.service..*(..)))") // TODO: Como configurar isso dinamicamente ?
	public Object allMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		if (Arrays.stream(methodSignature.getMethod().getAnnotations()).noneMatch(annotation -> CacheManagerService.allAnnotations.contains(annotation.annotationType()))) {
			return proceedingJoinPoint.proceed();
		}

		Annotation annotation = Arrays.stream(methodSignature.getMethod().getAnnotations()).filter(a -> CacheManagerService.allAnnotations.contains(a.annotationType())).findFirst().orElse(null);

		String key = (String) this.getMethodValue("key", annotation);
		Long ttl = (Long) this.getMethodValue("ttl", annotation);

		return cacheManagerService.selectStrategy(new RedisDTO(key, ttl), proceedingJoinPoint, annotation.annotationType());
	}

	private Object getMethodValue(String methodName, Object object) {
		try {
			Method getMethod = object.getClass().getMethod(methodName);
			return getMethod.invoke(object);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}