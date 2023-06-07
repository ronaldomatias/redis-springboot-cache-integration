package com.tcclibrary.domain;

import com.tcclibrary.annotation.Cacheable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

@Service
public class CacheManagerService {
	RedisBase redisBase;

	public CacheManagerService(RedisBase redisBase) {
		this.redisBase = redisBase;
	}

	public Object cacheable(Cacheable annotation, ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object response;

		if (redisBase.existsKey(annotation.key())) {
			response = redisBase.get(annotation.key());
		} else {
			response = proceedingJoinPoint.proceed();
			redisBase.set(new RedisDTO(annotation.key(), response, annotation.ttl()));
		}

		return response;
	}
}
