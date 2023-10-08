package com.tcclibrary.domain;

import com.tcclibrary.annotation.Cacheable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.List;

@Service
public class CacheManagerService {
	public static final List<Class<? extends Annotation>> allAnnotations = List.of(Cacheable.class);

	RedisBase redisBase;

	public CacheManagerService(RedisBase redisBase) {
		this.redisBase = redisBase;
	}

	public Object cacheable(RedisDTO redisDTO, ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object response;

		if (redisBase.existsKey(redisDTO.getKey())) {
			response = redisBase.get(redisDTO.getKey());
		} else {
			response = proceedingJoinPoint.proceed();
			redisDTO.setValue(response);
			redisBase.set(redisDTO);
		}

		return response;
	}

	// TODO: Aplicar o padrão Factory.
	public Object selectStrategy(RedisDTO redisDTO, ProceedingJoinPoint proceedingJoinPoint, Class<? extends Annotation> aClass) throws Throwable {
		if (aClass.equals(Cacheable.class)) {
			return cacheable(redisDTO, proceedingJoinPoint);
		}

		return null;
	}

}
