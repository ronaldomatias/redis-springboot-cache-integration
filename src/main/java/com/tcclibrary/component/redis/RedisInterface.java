package com.tcclibrary.component.redis;

import com.tcclibrary.interceptor.CacheInterceptor;

public interface RedisInterface {
	void set(String key, CacheInterceptor.RedisDTO value, Long ttl);

	Object get(String key);

	boolean expiredTTL(CacheInterceptor.RedisDTO key);

	boolean existsKey(String key);

	Long getTtl(String key);
}
